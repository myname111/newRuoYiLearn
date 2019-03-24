package com.ruoyi.common.utils.file;

import javax.servlet.ServletOutputStream;
import java.io.*;

/**
 * 文件工具类
 */
public class FileUtils {
    /**
     * 文件删除
     * @param filePath
     */
    public static boolean deleteFile(String filePath) {
          File file = new File(filePath);
          boolean flag=false;
          if (file.isFile()&&file.exists()){
              file.delete();
              flag=true;
          }
          return flag;
    }

    /**
     * 文件写入写出
     * @param filePath
     * @param outputStream
     * @throws IOException
     */
    public static void writeBytes(String filePath, OutputStream outputStream)throws IOException {
        FileInputStream in=null;
        try{
            File file = new File(filePath);
            if (!file.exists()){
                throw new FileNotFoundException(filePath);
            }
            in = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int length;
            while((length=in.read(bytes))>0){
                outputStream.write(bytes,0,length);
            }
        }catch(IOException e){
            throw  e;
        }finally {
              if (outputStream!=null){
                  outputStream.close();
              }
              if (in!=null){
                  in.close();
              }
        }
    }
}
