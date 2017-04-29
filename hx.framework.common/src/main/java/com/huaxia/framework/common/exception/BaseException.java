package com.huaxia.framework.common.exception;

import com.huaxia.framework.common.spring.SpringBeanUtils;
import com.huaxia.framework.common.utils.CommonUtils;

/**
 * 
 * Title: framework.common BaseException.java <br>
 * Description: 框架基础异常类，支持从资源文件中取错误信息<br>
 * Date: 2016年9月12日 <br>
 * Copyright (c) 2016 Joinandjoin <br>
 * 
 * @author shilei
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -4931109943400451987L;

    @Override
    public String getMessage() {
        String message = super.getMessage();
        if (CommonUtils.isEmpty(message)) {
            message = "";
        }
        if (CommonUtils.isEmpty(this.msgCode)) {
            return SpringBeanUtils.getMessage(this.msgCode, message);
        }
        return message + SpringBeanUtils.getMessage(this.msgCode, this.msgParams);
    }

    private String msgCode;

    private Object[] msgParams;

    public BaseException() {
        super();
    }

    /**
     * 给定一个错误信息构造异常实例
     * 
     * @param message
     *            String 错误信息
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * 给定一个错误信息和已有异常构造异常实例
     * 
     * @param message
     *            String 错误信息
     * @param throwable
     *            Throwable 已有异常
     */
    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * 通过给定的消息编码和消息参数从资源文件中获取错误信息，构造异常实例
     * 
     * @param msgCode
     *            String 资源文件中消息的编码
     * @param msgParams
     *            Object[] 消息所需的参数可以为空。
     */
    public BaseException(String msgCode, Object[] msgParams) {
        this.msgCode = msgCode;
        this.msgParams = msgParams;
    }

    /**
     * 通过给定的消息编码和消息参数从资源文件中获取错误信息，再结合已有的异常再构造异常实例
     * 
     * @param msgCode
     *            String 资源文件中消息的编码
     * @param msgParams
     *            Object[] 消息所需的参数可以为空。
     * @param throwable
     *            Throwable 已有异常
     */
    public BaseException(String msgCode, Object[] msgParams, Throwable throwable) {
        super(throwable);
        this.msgCode = msgCode;
        this.msgParams = msgParams;
    }

    /**
     * 获取消息编码
     * 
     * @return String 消息编码
     * @author shilei
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * 设置消息编码
     * 
     * @param msgCode
     *            String 消息编码
     * @author shilei
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    /**
     * 获取消息参数
     * 
     * @return Object[] 消息参数
     * @author shilei
     */
    public Object[] getMsgParams() {
        return msgParams;
    }

    /**
     * 设置消息参数
     * 
     * @param msgParams
     *            Object[] 消息参数
     * @author shilei
     */
    public void setMsgParams(Object[] msgParams) {
        this.msgParams = msgParams;
    }
}
