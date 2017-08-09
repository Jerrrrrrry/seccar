package com.lhcy.platform.web.action;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.hongchen.kis.db.ConfigDataInitializer;

public class DataInitializer extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2316593752955487434L;
	public void init(ServletConfig config)throws ServletException{  
		super.init(config);  
		//this.config=config;
//		String driverName=config.getInitParameter("driverName");
//		String username=config.getInitParameter("username");
//		String password=config.getInitParameter("password");
//		String dbName=config.getInitParameter("dbName");
		System.out.print("INitial ############################");
		 ConfigDataInitializer inil= new ConfigDataInitializer();
	        try {
				inil.initDB();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
	}  

}
