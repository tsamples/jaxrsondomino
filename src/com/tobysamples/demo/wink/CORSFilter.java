package com.tobysamples.demo.wink;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CORSFilter implements Filter {

	public CORSFilter() { }

	public void init(FilterConfig fConfig) throws ServletException { }

	public void destroy() {	}

	public void doFilter(
		ServletRequest request, ServletResponse response, 
		FilterChain chain) throws IOException, ServletException {

		((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse)response).addHeader("Access-Control-Allow-Credentials", "true");
		((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "POST");
		((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "Content-Type");
		((HttpServletResponse)response).addHeader("Access-Control-Max-Age", "86400");
		
		chain.doFilter(request, response);
	}
}