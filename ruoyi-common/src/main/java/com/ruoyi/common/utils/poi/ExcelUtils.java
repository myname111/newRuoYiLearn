package com.ruoyi.common.utils.poi;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.*;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static com.ruoyi.common.base.AjaxResult.success;

public class ExcelUtils<T> {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);
    /**
     * 实体对象
     */
    private Class<T> clazz;
    /**
     * 导入导出数据列表
     */
    private List<T> list;
    /**
     * 表单名称
     */
    private String sheetName;
    /**
     * 导出类型(EXPORT:导出数据;IMPORT:导入模板)
     */
    private Type type;
    /**
     * 工作表单
     */
    private Sheet sheet;
    /**
     * 工作簿
     */
    private Workbook workbook;
    /**
     *字段
     */
    private List<Field> fields;
    /**
     * 一个表单所能容纳的最大行数
     */
    private static final int sheetSize=65536;
    public ExcelUtils(Class<T> clazz){
        this.clazz=clazz;
    }

    /**
     * 导出数据
     * @param list
     * @param sheetName
     * @return
     */
    public  AjaxResult exportExcel(List<T> list, String sheetName) {
          this.init(list,sheetName, Type.EXPORT);
          return exportExcel();
    }

    /**
     * 将list数据源中的数据导入到excel表单中
     * @return
     */
    private AjaxResult exportExcel() {
          OutputStream out=null;
          try{
             double sheetNo =  Math.ceil(this.list.size()/sheetSize);
             for (int index=0;index<=sheetNo;index++){
                 createSheet(sheetNo,index);
                 Row row = sheet.createRow(0);
                 Cell cell = null;
                 //写标题
                 for (int i=0;i<fields.size();i++){
                     Field field = fields.get(i);
                     Excel annotation = field.getAnnotation(Excel.class);
                     cell = row.createCell(i);
                     //设置列中写入的内容是String类型
                     cell.setCellType(CellType.STRING);
                     CellStyle cellStyle = workbook.createCellStyle();
                     //水平对齐
                     cellStyle.setAlignment(HorizontalAlignment.CENTER);
                     //垂直对齐
                     cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

                     if (annotation.name().indexOf("注:")>-1){
                         Font font = workbook.createFont();
                         //字体颜色
                         font.setColor(HSSFFont.COLOR_RED);
                         cellStyle.setFont(font);
                         //单元格设置背景色
                         cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
                         sheet.setColumnWidth(i,6000);
                     }else{
                         Font font = workbook.createFont();
                         //设置粗体
                         font.setBold(true);
                         cellStyle.setFont(font);
                         cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_YELLOW.getIndex());
                         sheet.setColumnWidth(i,(int)((annotation.width()+0.72)*256));
                          row.setHeight((short) (annotation.height()*20));
                     }
                     cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                     cellStyle.setWrapText(true);
                     cell.setCellStyle(cellStyle);
                     cell.setCellValue(annotation.name());
                     //提示信息
                     if (StringUtils.isNotEmpty(annotation.prompt())){
                         setHSSFPrompt(sheet,"",annotation.prompt(),1,100,i,i);
                     }
                     //设置该列只能选择不能输入
                     if (annotation.combo().length>0){
                         setHSSFValidation(sheet,annotation.combo(),1,100,i,i);
                     }
                 }
                 //导出数据
                 if (Type.EXPORT.equals(type)){
                        fillExcelData(index,row,cell);
                 }
             }
              String filename=encodingFilename(sheetName);
              out = new FileOutputStream(getAbsoluteFile(filename));
              workbook.write(out);
              return success(filename);
          }catch(Exception e){
              e.printStackTrace();
              log.error("导出Excel异常{}",e.getMessage());
              throw new BusinessException("导出Excel失败,请联系网站管理员");
          }finally {
              if (workbook!=null){
                  try {
                      workbook.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
              if (out!=null){
                  try {
                      out.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
    }

    /**
     * 填充excel数据
     * @param index
     * @param row
     * @param cell
     */
    private void fillExcelData(int index, Row row, Cell cell) {
          int startNo=index*sheetSize;
          int endNo = Math.min(startNo+sheetSize,list.size());
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        for (int i =startNo;i<endNo;i++){
              row = sheet.createRow(i+1-startNo);
              T t = list.get(i);
              if (t==null){
                  continue;
              }
              for(int j=0;j<fields.size();j++){
                  Field field = fields.get(j);
                  field.setAccessible(true);
                  Excel annotation = field.getAnnotation(Excel.class);
                  try{
                      row.setHeight((short)(annotation.height()*20));
                      if(annotation.isExport()){
                          cell = row.createCell(j);
                          cell.setCellStyle(cellStyle);
                          Object value = getTargetValue(t,field,annotation);
                          String dateFormat = annotation.dateFormat();
                          String convertExp= annotation.readConvertExp();
                          if (StringUtils.isNotEmpty(dateFormat)){
                              cell.setCellValue(DateUtils.parseDateToStr(dateFormat,(Date)value));
                          }else if (StringUtils.isNotEmpty(convertExp)){
                              cell.setCellValue(convertByExp(String.valueOf(value),convertExp));
                          }else{
                              cell.setCellType(CellType.STRING);
                              cell.setCellValue(StringUtils.isNotNull(value)?value+annotation.suffix():annotation.defaultValue());
                          }
                      }
                  }catch(Exception e){
                      log.error("excel导出失败{}",e.getMessage());
                  }

              }
        }
    }

    private Object getTargetValue(T t, Field field, Excel annotation) throws Exception{
        Object o=null;
        try {
            o = field.get(t);
            if (StringUtils.isNotEmpty(annotation.targetAttr())){
                String targetAttr = annotation.targetAttr();
                if (targetAttr.indexOf(".")>-1){
                    String[] split = targetAttr.split("[.]");
                    for (String str:split){
                        o=getValue(o,str);
                    }
                }else{
                    o=getValue(o,targetAttr);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return o;
    }

    /**
     * 根据get方法获取属性值
     * @param o
     * @param targetAttr
     * @return
     * @throws Exception
     */
    private Object getValue(Object o, String targetAttr)throws Exception {
        if (StringUtils.isNotEmpty(targetAttr)){
            String methoName="get"+targetAttr.substring(0,1).toUpperCase()+targetAttr.substring(1);
            Method method = o.getClass().getMethod(methoName);
            o = method.invoke(o);
        }
        return o;
    }

    /**
     * 解析导出值.如,导出值为0,解析为男
     * @param s
     * @param convertExp
     * @return
     * @throws Exception
     */
    private String convertByExp(String s, String convertExp) throws Exception {
        if (StringUtils.isNotEmpty(s)){
            try{
                String[] split = StringUtils.split(convertExp, ",");
                for (String str:split) {
                    String[] strings = StringUtils.split(str, "=");
                    if (StringUtils.equals(s,strings[0])){
                        return strings[1];
                    }
                }
            }catch(Exception e){
                throw e;
            }
        }
        return s;
    }

    /**
     * 设置某些列的值只能输入预定的数据,显示下拉框
     * @param sheet
     * @param combo
     * @param firstRow
     * @param endRow
     * @param firstCell
     * @param endCell
     * @return
     */
    private Sheet setHSSFValidation(Sheet sheet, String[] combo, int firstRow, int endRow, int firstCell, int endCell) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        //加载下拉框内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(combo);
        //区域
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow,firstCell,endCell);
        //数据有效性
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        //处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation){
            //是否显示下拉箭头
            dataValidation.setSuppressDropDownArrow(true);
            //是否提示错误
            dataValidation.setShowErrorBox(true);
        }else{
            dataValidation.setSuppressDropDownArrow(false);
        }
        sheet.addValidationData(dataValidation);
        return sheet;
    }

    /**
     * 设置单元格上的提示
     * @param sheet
     * @param promptTitle
     * @param promptContent
     * @param beginRow
     * @param endRow
     * @param beginCell
     * @param endCell
     * @return
     */
    private Sheet setHSSFPrompt(Sheet sheet, String promptTitle, String promptContent, int beginRow, int endRow, int beginCell, int endCell) {
        //约束
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("DD1");
        //区域
        CellRangeAddressList regions = new CellRangeAddressList(beginRow,endRow,beginCell,endCell);
        //数据有效性
        HSSFDataValidation dataValidation = new HSSFDataValidation(regions,constraint);
        //设置提示弹窗
        dataValidation.createPromptBox(promptTitle,promptContent);
        sheet.addValidationData(dataValidation);
        return sheet;
    }

    /**
     * 获取下载路径
     */
    private String getAbsoluteFile(String filename) {
        String downloadPath  = Global.getDownloadPath()+filename;
        File file = new File(downloadPath);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
     * 编码文件名
     * @param filename
     * @return
     */
    private String encodingFilename(String filename) {
        filename = UUID.randomUUID().toString()+"_"+filename+".xlsx";
        return filename;
    }

    /**
     * 创建表单
     * @param sheetNo
     * @param index
     */
    private void createSheet(double sheetNo, int index) {
        this.sheet = workbook.createSheet();
        if (sheetNo==0){
            workbook.setSheetName(index,sheetName);
        }else{
            workbook.setSheetName(index,sheetName+index);
        }
    }

    /**
     * 初始化Excel工具类
     * @param list
     * @param sheetName
     * @param type
     */
    private void init(List<T> list, String sheetName, Type type) {
        if (list==null){
            list=new ArrayList<T>();
        }
        this.list=list;
        this.sheetName=sheetName;
        this.type=type;
        createExcelField();
        createWorkbook();
    }

    /**
     * 得到所有定义字段
     */
    private void createExcelField() {
        this.fields=new ArrayList<>();
        List<Field> tempList = new ArrayList<>();
        Field[] fieldArr = this.clazz.getDeclaredFields();
        tempList.addAll(Arrays.asList(fieldArr));
        Class<?> tempClass = clazz;
        while(tempClass!=null){
            tempClass = tempClass.getSuperclass();
            if (tempClass!=null){
                tempList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            }
        }
        putToFields(tempList);
    }

    /**
     * 把符合要求的字段放到字段集合中
     * @param fields
     */
    private void putToFields(List<Field> fields) {
        for (Field field:fields) {
            Excel annotation = field.getAnnotation(Excel.class);
            //标有@Excel注解,且导出类型为ALL或者EXPORT或者IMPORT的字段符合要求
            if (annotation!=null&&(annotation.type()==Type.ALL||annotation.type()==type)){
                this.fields.add(field);
            }
        }
    }

    /**
     * 创建新的工作簿
     */
    private void createWorkbook() {
        this.workbook=new SXSSFWorkbook(500);
    }
}
