package com.huaxia.framework.security.rabc.model;

/**
 * 权限对象抽象结构
 * 
 * @author shilei
 *
 */
public interface IRabcRelatModel extends IRabcModel {

	long rightModelNo(long rightModelNo);
	
	long leftModelNo(long leftModelNo);
	
	IRabcModel relatRightModel(IRabcModel rightModel);
	
	IRabcModel relatLeftModel(IRabcModel leftModel);
}
