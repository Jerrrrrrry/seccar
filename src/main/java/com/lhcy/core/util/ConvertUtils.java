package com.lhcy.core.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ConvertUtils {

    /***********************************************/
    // 日期时间 -> 字符串
    /***********************************************/
    public static String datetimeToString(Date date){

        if (date == null){
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /***********************************************/
    // 日期 -> 字符串
    /***********************************************/
    public static String dateToString(Date date){

        if (date == null){
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /***********************************************/
    // 日期时间 -> 字符串（自定义格式）
    /***********************************************/
    public static String dateToString(Date date, String format){

        if (date == null){
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /***********************************************/
    // SQL日期时间 -> 字符串
    /***********************************************/
    public static String timestampToString(Timestamp date){

        if (date == null){
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /***********************************************/
    // SQL日期 -> 字符串
    /***********************************************/
    public static String dateToString(java.sql.Date date){

        if (date == null){
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /***********************************************/
    // 字符串 -> 日期
    /***********************************************/
    public static Date stringToDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;

        try{
            result = sdf.parse(date);
        }catch(Exception ex){
            result = null;
        }
        return result;
    }

    /***********************************************/
    // 字符串 -> 日期 (自定义格式)
    /***********************************************/
    public static Date stringToDate(String date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date result = null;

        try{
            result = sdf.parse(date);
        }catch(Exception ex){
            result = null;
        }
        return result;
    }

    /***********************************************/
    // 字符串 -> Double
    /***********************************************/
    public static double stringToDouble(String val){

        double result = 0;

        if (val == null || val.length() == 0)
            return result;

        try{
            result = Double.parseDouble(val.replaceAll(",",""));
        }catch (Exception e){
            result = 0;
        }

        return result;
    }

    /***********************************************/
    // 字符串 -> Int
    /***********************************************/
    public static int stringToInt(String val){

        int result = 0;

        if (val == null || val.length() == 0)
            return result;

        try{
            result = Integer.parseInt(val);
        }catch (Exception e){
            result = 0;
        }

        return result;
    }

    /***********************************************/
    // Double -> 字符串（带格式)
    /***********************************************/
    public static String doubleToString(Double val, int decimal){

        String result = "";
        String format = "#,##0.";
        for (int i = 0; i < decimal; i ++){
            format += "0";
        }
        DecimalFormat df = new DecimalFormat(format);

        if (val == null)
            return result;

        try{
            result = df.format(val);
        }catch (Exception e){
            result = "";
        }

        return result;
    }

    /***********************************************/
    // Double -> 人民币格式
    /***********************************************/
    public static String doubleToRmbString(Double val, int decimal){

        String result = "";
        String format = "#,##0.";
        for (int i = 0; i < decimal; i ++){
            format += "0";
        }
        DecimalFormat df = new DecimalFormat(format);

        if (val == null)
            return result;

        try{
            result = "¥" + df.format(val);
        }catch (Exception e){
            result = "";
        }

        return result;
    }

    /***********************************************/
    // 空字符串 -> null
    /***********************************************/
    public static String emptyToNull(String val){

        if (val == null || val.length() == 0){
            return null;
        }else{
            return val;
        }
    }

    /***********************************************/
    // 0 -> null
    /***********************************************/
    public static Integer zeroToNull(int val){

        if (val == 0){
            return null;
        }else{
            return val;
        }
    }

    /***********************************************/
    // 0 -> null
    /***********************************************/
    public static Long zeroToNull(long val){

        if (val == 0){
            return null;
        }else{
            return val;
        }
    }

    /***********************************************/
    // 0 -> null
    /***********************************************/
    public static Float zeroToNull(float val){

        if (val == 0){
            return null;
        }else{
            return val;
        }
    }

    /***********************************************/
    // 0 -> null
    /***********************************************/
    public static Double zeroToNull(double val){

        if (val == 0){
            return null;
        }else{
            return val;
        }
    }
}
