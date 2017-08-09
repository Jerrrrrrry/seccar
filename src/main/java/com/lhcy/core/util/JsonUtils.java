package com.lhcy.core.util;

import com.lhcy.core.bo.HttpActionResult;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletResponse;

public class JsonUtils {

    public static void printFromList(HttpServletResponse response, Object obj, int total) throws Exception{

        JSONArray jsonArray = JSONArray.fromObject(obj);
        StringBuilder json = new StringBuilder();
        json.append("{\"total\":" + total + ",\"rows\":");
        json.append(jsonArray.toString());
        json.append("}");
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(json);
    }

    public static void printFromList(HttpServletResponse response, Object obj, int total, String footer) throws Exception{

        JSONArray jsonArray = JSONArray.fromObject(obj);
        StringBuilder json = new StringBuilder();
        json.append("{\"total\":" + total + ",\"rows\":");
        json.append(jsonArray.toString());
        json.append(",\"footer\":[");
        json.append(footer);
        json.append("]}");
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(json);
    }

    public static String toString(Object obj) throws Exception{

        JSONArray jsonArray = JSONArray.fromObject(obj);
        String json = jsonArray.toString();
        return json;
    }

    public static void printFromObject(HttpServletResponse response, Object obj) throws Exception{

        JSONArray jsonArray = JSONArray.fromObject(obj);
        String json = jsonArray.toString();
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(json.toString());
    }

    public static void printActionResultOK(HttpServletResponse response) throws Exception{

        HttpActionResult ar = new HttpActionResult("ok", "", null);
        JSONArray jsonArray = JSONArray.fromObject(ar);
        String json = jsonArray.toString();
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(json.toString());
    }

    public static void printActionResultFromObject(HttpServletResponse response, Object obj) throws Exception{

        HttpActionResult ar = new HttpActionResult("ok", "", obj);
        JSONArray jsonArray = JSONArray.fromObject(ar);
        String json = jsonArray.toString();
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(json.toString());
    }

    public static void printActionResultFromException(HttpServletResponse response, Exception e) throws Exception{

        HttpActionResult ar = new HttpActionResult("error", e.toString(), null);

        JSONArray jsonArray = JSONArray.fromObject(ar);
        String json = jsonArray.toString();
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(json.toString());
    }
}
