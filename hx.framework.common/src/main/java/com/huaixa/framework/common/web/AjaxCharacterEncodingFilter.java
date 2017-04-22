package com.huaixa.framework.common.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 
 * Title:  framework.common AjaxCharacterEncodingFilter.java <br>
 * Description: 兼容ajax请求的字符编码过滤器继承自spring的OncePerRequestFilter，用于web项目的字符过滤<br>
 * Date: 2016年9月12日 <br>
 * Copyright (c) 2016 Joinandjoin <br>
 * 
 * @author shilei
 */
public class AjaxCharacterEncodingFilter extends OncePerRequestFilter {
	
	private String encoding;

	private boolean forceEncoding;
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public void setForceEncoding(boolean forceEncoding) {
		this.forceEncoding = forceEncoding;
	}
	
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (this.forceEncoding || request.getCharacterEncoding() == null) {
			if (isAjax(request)){
				request.setCharacterEncoding("UTF-8");
			} else {
				request.setCharacterEncoding(this.encoding);
			}
		}
		filterChain.doFilter(request, response);
		
	}
	
	private static boolean isAjax(HttpServletRequest request) {
		if (request != null
				&& "XMLHttpRequest".equalsIgnoreCase(request
						.getHeader("X-Requested-With")))
			return true;
		return false;
	}
}
