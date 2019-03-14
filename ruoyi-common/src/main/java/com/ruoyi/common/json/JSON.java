package com.ruoyi.common.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * json解析处理
 */
public class JSON {
    private static ObjectMapper objectMapper=new ObjectMapper();
    //把json串转换成对象
    public static <T> T unmarshal(String str,Class<T> valueType) throws Exception{
        try {
            return objectMapper.readValue(str,valueType);
        } catch (JsonParseException e) {
           throw new Exception();
        }catch (JsonMappingException e){
            throw new Exception();
        }catch (IOException e){
            throw new Exception();
        }
    }
}
