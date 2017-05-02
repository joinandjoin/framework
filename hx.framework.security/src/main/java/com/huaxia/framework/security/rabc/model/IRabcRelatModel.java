package com.huaxia.framework.security.rabc.model;

/**
 * 权限对象抽象结构
 * 
 * @author shilei
 *
 */
public interface IRabcRelatModel extends IRabcModel {

	long leftModelNo(long... leftModelNo);
	
	long rightModelNo(long... rightModelNo);
	
	IRabcModel relatLeftModel(IRabcModel... leftModel);
	
	IRabcModel relatRightModel(IRabcModel... rightModel);

}
