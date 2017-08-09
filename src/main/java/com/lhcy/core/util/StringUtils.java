package com.lhcy.core.util;

import org.apache.log4j.Logger;

import java.net.URLDecoder;
import java.util.List;

public class StringUtils {

    private static Logger logger = Logger.getLogger(StringUtils.class);

    public static String decode(String str){
        if (str == null || str.length() == 0){
            return null;
        }

        String result = null;
        try {
            result = URLDecoder.decode(str, "UTF-8");
        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }

        return result;
    }

    public static String listToCommaString(List<String> list){

        if (list == null || list.size() == 0){
            return "''";
        }

        String result = "";
        for (int i = 0; i < list.size(); i ++){
            result += ",'" + list.get(i) + "' ";
        }

        result = result.substring(1,result.length());

        return result;
    }

    public static String intListToCommaString(List<Integer> list){

        if (list == null || list.size() == 0){
            return "";
        }

        String result = "";
        for (int i = 0; i < list.size(); i ++){
            result += "," + list.get(i) + " ";
        }

        result = result.substring(1,result.length());

        return result;
    }

    public static String listToCommaString(String[] list){

        if (list == null || list.length == 0){
            return "''";
        }

        String result = "";
        for (int i = 0; i < list.length; i ++){
            result += ",'" + list[i] + "' ";
        }

        result = result.substring(1,result.length());
        return result;
    }

    // SQL占位符
    public static String getSqlPlaceholder(int count){
        String result = "";
        for (int i = 0; i < count; i ++){
            result += ",?";
        }

        result = result.substring(1,result.length());
        return result;
    }
}
