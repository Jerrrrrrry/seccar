package com.lhcy.core.bo;

public class HtmlRender {
	
	public static String toHtml(String context){
		
		String result = "";
		if (context == null || context.length() == 0){
			return result;
		}
		
		result = context;
		result = result.replaceAll("&", "&amp;");
		result = result.replaceAll("<", "&lt;");
		result = result.replaceAll(">", "&gt;");
		result = result.replaceAll("\"", "&quot;");
		result = result.replaceAll("\'", "&#39;");

		return result;
	}
}
