package com.huaxia.framework.security.rabc.model;

import java.sql.Timestamp;

import com.huaxia.framework.common.enums.privilege.RabcModelType;

/**
 * RABC模型抽象结构
 * 
 * @author shilei
 *
 */
public interface IRabcModel {

	RabcModelType rabcModeType(RabcModelType rabcModelType) throws Exception;
	
	long modelSerialNo(long... modelSerialNo);
	
	String modelName(String... modelName);
	
	String modelCode(String... modelCode);
	
	long modelCateNo(long... modelCateNo);
	
	String modelCateCode(String... modelCateCode);
	
	String modelCateName();
	
	String modelCateName(long modelCate);
	
	int modelSortNo(int... sortNo);
	
	String modelStatus(String... status);
	
	Timestamp modelVersion(Timestamp... version);
	
	String modelValue(String... modelValue);
	
	boolean supportType();
}
