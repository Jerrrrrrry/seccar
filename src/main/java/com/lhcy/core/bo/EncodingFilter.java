package com.lhcy.core.bo;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;


public class EncodingFilter extends HttpServlet implements Filter {
	private static final String ENCODING = "encoding";
	private FilterConfig filterConfig;
	private String encoding;

	public EncodingFilter() {
		encoding = null;
	}

	public void init(FilterConfig filterconfig) {
		filterConfig = filterconfig;
		encoding = filterconfig.getInitParameter("encoding");
	}

	public void setFilterConfig(String s) {
	}

	public void setFilterConfig(FilterConfig filterconfig) {

		encoding = filterConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain) {
		try {
			servletrequest.setCharacterEncoding(encoding);
			filterchain.doFilter(servletrequest, servletresponse);
		} catch (Exception exception) {
			exception.printStackTrace();
			filterConfig.getServletContext().log(exception.getMessage());
		}
	}

	public void destroy() {
	}
}
