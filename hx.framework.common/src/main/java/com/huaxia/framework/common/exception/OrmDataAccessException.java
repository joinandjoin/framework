package com.huaxia.framework.common.exception;

import org.springframework.dao.DataAccessException;

import com.huaxia.framework.common.spring.SpringBeanUtils;
import com.huaxia.framework.common.utils.CommonUtils;
/**
 * 
 * Title:  framework.common OrmDataAccessException.java <br>
 * Description: jdbc操作异常基类，继承自spring的DataAccessException异常，凡是项目中jdbc操作异常都从使用该类封装抛出，或者从该类继承；提供了从资源文件获取消息的能力<br>
 * Date: 2016年9月12日 <br>
 * Copyright (c) 2016 Joinandjoin <br>
 * 
 * @author shilei
 */
public class OrmDataAccessException extends DataAccessException {

	private static final long serialVersionUID = 5866231430260973919L;
	
	private String msgCode;
	private Object[] msgParams;
	
	public OrmDataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public OrmDataAccessException(String msg) {
		super(msg);
	}
	
	public OrmDataAccessException(String msgCode,Object[] msgParams, Throwable throwable) {
		this(null,throwable);
		this.msgCode = msgCode;
		this.msgParams = msgParams;
	}
	
	public OrmDataAccessException(String msgCode,Object[] msgParams){
		this(null);
		this.msgCode = msgCode;
		this.msgParams = msgParams;
	}
	
	@Override 
	public String getMessage() {
		String message = super.getMessage();
		if (CommonUtils.isEmpty(message)){
			 message = "";
		}
		if (CommonUtils.isEmpty(this.msgCode)){
			return SpringBeanUtils.getMessage(this.msgCode, message);
		}
		return message + SpringBeanUtils.getMessage(this.msgCode, this.msgParams);
	}
	
	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public Object[] getMsgParams() {
		return msgParams;
	}

	public void setMsgParams(Object[] msgParams) {
		this.msgParams = msgParams;
	}
}
