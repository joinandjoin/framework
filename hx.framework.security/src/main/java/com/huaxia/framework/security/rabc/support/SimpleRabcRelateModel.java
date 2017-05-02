package com.huaxia.framework.security.rabc.support;

import java.sql.Timestamp;

import com.huaxia.framework.common.enums.privilege.RabcModelType;
import com.huaxia.framework.common.utils.CommonUtils;
import com.huaxia.framework.security.rabc.model.IRabcModel;
import com.huaxia.framework.security.rabc.model.IRabcRelatModel;

public class SimpleRabcRelateModel extends SimpleRabcModel implements IRabcRelatModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3690515818352494313L;
	private long leftModelNo = 0L;
	private long rightModelNo = 0L;
	
	private IRabcModel leftModel = null;
	private IRabcModel rightModel = null;
	

	public SimpleRabcRelateModel(RabcModelType rabcModelType, long serialNo, String name, String code, long cateNo,
			String cateName, int sortNo, String status, String value, Timestamp version,long leftModelNo,long rightModelNo) {
		super(rabcModelType, serialNo, name, code, cateNo, cateName, sortNo, status, value, version);
		this.leftModelNo = leftModelNo;
		this.rightModelNo = rightModelNo;
	}

	public SimpleRabcRelateModel(RabcModelType rabcModelType, long serialNo, String name, String code, long cateNo,
			String cateName, int sortNo, String status, String value, Timestamp version,IRabcModel leftModel,IRabcModel rightModel) {
		super(rabcModelType, serialNo, name, code, cateNo, cateName, sortNo, status, value, version);
		this.leftModelNo = leftModel.modelSerialNo(0l);
		this.rightModelNo = rightModel.modelSerialNo(0l);
		this.leftModel = leftModel;
		this.rightModel = rightModel;
	}

	
	public SimpleRabcRelateModel(RabcModelType rabcModelType) {
		super(rabcModelType);
	}
	
	@Override
	public long leftModelNo(long leftModelNo) {
		if (leftModelNo>0){
			this.leftModelNo = leftModelNo;
		}
		return this.leftModelNo;
	}

	@Override
	public long rightModelNo(long rightModelNo) {
		if (rightModelNo>0){
			this.rightModelNo = rightModelNo;
		}
		return this.rightModelNo;
	}

	@Override
	public IRabcModel relatLeftModel(IRabcModel leftModel) {
		if (CommonUtils.isNotNullEmpty(leftModel)){
			this.leftModel = leftModel;
		}
		return this.leftModel;
	}

	@Override
	public IRabcModel relatRightModel(IRabcModel rightModel) {
		if (CommonUtils.isNotNullEmpty(rightModel)){
			this.rightModel = rightModel;
		}
		return this.rightModel;
	}
	
	@Override
	public boolean supportType() {
		return !super.supportType();
	}

	@Override
	public String toString() {
		StringBuilder sbd = new StringBuilder(super.toString());
		sbd.append("{")
		.append("\"").append("leftModelNo").append("\":")
		.append("\"").append(this.leftModelNo).append("\"")
		.append(",")
		
		.append("\"").append("rightModelNo").append("\":")
		.append("\"").append(this.rightModelNo).append("\"")
		.append(",")
		
		.append("\"").append("leftModel").append("\":")
		.append("\"").append(this.leftModel).append("\"")
		.append(",")
		
		.append("\"").append("rightModel").append("\":")
		.append("\"").append(this.rightModel).append("\"")
		
		.append("}");
		return sbd.toString();
	}



}
