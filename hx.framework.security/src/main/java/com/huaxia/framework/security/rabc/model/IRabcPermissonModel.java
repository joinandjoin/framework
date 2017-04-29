package com.huaxia.framework.security.rabc.model;

/**
 * 权限对象抽象结构
 * 
 * @author shilei
 *
 */
public interface IRabcPermissonModel extends IRabcModel {

	long operationNo(long operationNo);
	
	long resourceNo(long resourceNo);
	
	String permisson(String permisson);
	
}
