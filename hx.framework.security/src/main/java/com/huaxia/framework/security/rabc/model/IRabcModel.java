package com.huaxia.framework.security.rabc.model;

import java.sql.Timestamp;

import com.huaxia.framework.common.enums.privilege.RabcModelType;

/**
 * 权限对象抽象结构
 * 
 * @author shilei
 *
 */
public interface IRabcModel {

	RabcModelType rabcModeType(RabcModelType rabcModelType);
	
	long modelSerialNo(long modelSerialNo);
	
	String modelName(String modelName);
	
	String modelCode(String modelCode);
	
	long modelCate(long modelCate);
	
	String modelCateName();
	
	String modelCateName(long modelCate);
	
	int modelSortNo(int sortNo);
	
	String modelStatus(String status);
	
	Timestamp modelVersion(Timestamp version);
	
	IRabcModel relatRightModel(IRabcModel rightModel);
	
	IRabcModel relatLeftModel(IRabcModel leftModel);
}
