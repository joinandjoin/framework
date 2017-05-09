package com.huaxia.framework.security.rabc.support;

import java.io.Serializable;
import java.sql.Timestamp;

import com.huaxia.framework.common.enums.privilege.RabcModelType;
import com.huaxia.framework.common.utils.CommonUtils;
import com.huaxia.framework.security.rabc.model.IRabcModel;

public class SimpleRabcModel implements IRabcModel,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1971557234446715838L;
	private RabcModelType rabcModelType = null;
	private long serialNo = 0L;
	private String name = "";
	private String code = "";
	private long cateNo = 0L;
	private String cateName = "";
	private int sortNo = -1;
	private String status = "1";
	private String value = "";
	private String modelCateCode="";
	private Timestamp version = null;
	
	public SimpleRabcModel(RabcModelType rabcModelType, long serialNo, String name, String code, long cateNo,
			String cateName, int sortNo, String status, String value, Timestamp version) throws Exception {
		super();
		this.rabcModelType = rabcModelType;
		if (!this.supportType()) throw new Exception("not support RABC MODEL type");
		this.serialNo = serialNo;
		this.name = name;
		this.code = code;
		this.cateNo = cateNo;
		this.cateName = cateName;
		this.sortNo = sortNo;
		this.status = status;
		this.value = value;
		this.version = version;
	}

	public SimpleRabcModel(RabcModelType rabcModelType, long serialNo, String name, String code, long cateNo, String value) throws Exception {
		super();
		this.rabcModelType = rabcModelType;
		if (!this.supportType()) throw new Exception("not support RABC MODEL type");
		this.serialNo = serialNo;
		this.name = name;
		this.code = code;
		this.cateNo = cateNo;
		this.cateName = "";
		this.sortNo = -1;
		this.status = "1";
		this.value = value;
		this.version = null;
	}

	public SimpleRabcModel(RabcModelType rabcModelType, long serialNo, String name, String code, String cateCode, String value) throws Exception {
		super();
		this.rabcModelType = rabcModelType;
		if (!this.supportType()) throw new Exception("not support RABC MODEL type");
		this.serialNo = serialNo;
		this.name = name;
		this.code = code;
		this.modelCateCode = cateCode;
		this.cateName = "";
		this.sortNo = -1;
		this.status = "1";
		this.value = value;
		this.version = null;
	}
	
	public SimpleRabcModel(RabcModelType rabcModelType, long serialNo, String name, String code, long cateNo) throws Exception {
		super();
		this.rabcModelType = rabcModelType;
		if (!this.supportType()) throw new Exception("not support RABC MODEL type");
		this.serialNo = serialNo;
		this.name = name;
		this.code = code;
		this.cateNo = cateNo;
		this.cateName = "";
		this.sortNo = -1;
		this.status = "1";
		this.value = "";
		this.version = null;
	}
	
	public SimpleRabcModel(RabcModelType rabcModelType, long serialNo, String name, String code, String cateCode) throws Exception {
		super();
		this.rabcModelType = rabcModelType;
		if (!this.supportType()) throw new Exception("not support RABC MODEL type");
		this.serialNo = serialNo;
		this.name = name;
		this.code = code;
		this.modelCateCode = cateCode;
		this.cateName = "";
		this.sortNo = -1;
		this.status = "1";
		this.value = "";
		this.version = null;
	}
	
	public SimpleRabcModel(RabcModelType rabcModelType, long serialNo, String name, String code) throws Exception {
		super();
		this.rabcModelType = rabcModelType;
		if (!this.supportType()) throw new Exception("not support RABC MODEL type");
		this.serialNo = serialNo;
		this.name = name;
		this.code = code;
		this.cateNo = 0;
		this.cateName = "";
		this.sortNo = -1;
		this.status = "1";
		this.value = "";
		this.version = null;
	}
	
	public SimpleRabcModel(RabcModelType rabcModelType) throws Exception {
		super();
		this.rabcModelType = rabcModelType;
		if (!this.supportType()) throw new Exception("not support RABC MODEL type");
	}
	
	@Override
	public RabcModelType rabcModeType(RabcModelType rabcModelType) throws Exception {
		if (null != rabcModelType){
			this.rabcModelType = rabcModelType;
			if (!this.supportType()) throw new Exception("not support RABC MODEL type");
		}
		return this.rabcModelType;
	}

	@Override
	public long modelSerialNo(long... modelSerialNo) {
		if (CommonUtils.isNotNullEmpty(modelSerialNo) && modelSerialNo[0]>0){
			this.serialNo = modelSerialNo[0];
		}
		return this.serialNo;
	}

	@Override
	public String modelName(String... modelName) {
		if (CommonUtils.isNotNullEmpty(modelName) && CommonUtils.isNotNullEmpty(modelName[0])){
			this.name = modelName[0];
		}
		return this.name;
	}

	@Override
	public String modelCode(String... modelCode) {
		if (CommonUtils.isNotNullEmpty(modelCode) && CommonUtils.isNotNullEmpty(modelCode[0])){
			this.code = modelCode[0];
		}
		return this.code;
	}

	@Override
	public long modelCateNo(long... modelCateNo) {
		if (CommonUtils.isNotNullEmpty(modelCateNo) && modelCateNo[0]>0){
			this.cateNo = modelCateNo[0];
		}
		return this.cateNo;
	}

	@Override
	public String modelCateName() {
		return this.cateName;
	}

	@Override
	public String modelCateName(long modelCate) {
		return null;
	}

	@Override
	public String modelCateCode(String... modelCateCode) {
		if (CommonUtils.isNotNullEmpty(modelCateCode) && CommonUtils.isNotNullEmpty(modelCateCode[0])){
			this.modelCateCode = modelCateCode[0];
		}
		return this.modelCateCode;
	}
	
	@Override
	public int modelSortNo(int... sortNo) {
		if (CommonUtils.isNotNullEmpty(sortNo) && sortNo[0]>0){
			this.sortNo = sortNo[0];
		}
		return this.sortNo;
	}

	@Override
	public String modelStatus(String... status) {
		if (CommonUtils.isNotNullEmpty(status) && CommonUtils.isNotNullEmpty(status[0])){
			this.status = status[0];
		}
		return this.status;
	}

	@Override
	public Timestamp modelVersion(Timestamp... version) {
		if (CommonUtils.isNotNullEmpty(version) && CommonUtils.isNotNullEmpty(version[0])){
			this.version = version[0];
		}
		return this.version;
	}

	@Override
	public String modelValue(String... modelValue) {
		if (CommonUtils.isNotNullEmpty(modelValue) && CommonUtils.isNotNullEmpty(modelValue[0])){
			this.value = modelValue[0];
		}
		return this.value;
	}

	@Override
	public String toString() {
		StringBuilder sbd = new StringBuilder(super.toString());
		sbd.append("{")
		.append("\"").append("rabcModelType").append("\":")
		.append("\"").append(this.rabcModelType).append("\"")
		.append(",")
		
		.append("\"").append("serialNo").append("\":")
		.append("\"").append(this.serialNo).append("\"")
		.append(",")
		
		.append("\"").append("name").append("\":")
		.append("\"").append(this.name).append("\"")
		.append(",")
		
		.append("\"").append("code").append("\":")
		.append("\"").append(this.code).append("\"")
		.append(",")
		
		.append("\"").append("cateNo").append("\":")
		.append("\"").append(this.cateNo).append("\"")
		.append(",")
		
		.append("\"").append("cateName").append("\":")
		.append("\"").append(this.cateName).append("\"")
		.append(",")
		
		.append("\"").append("sortNo").append("\":")
		.append("\"").append(this.sortNo).append("\"")
		.append(",")
		
		.append("\"").append("status").append("\":")
		.append("\"").append(this.status).append("\"")
		.append(",")
		
		.append("\"").append("value").append("\":")
		.append("\"").append(this.value).append("\"")
		.append(",")
		
		.append("\"").append("version").append("\":")
		.append("\"").append(this.version).append("\"")
		.append("}");
		return sbd.toString();
	}

	@Override
	public boolean supportType() {
		if (this.rabcModelType != null
			&&( this.rabcModelType.equals(RabcModelType.PARTY)
			|| this.rabcModelType.equals(RabcModelType.ROLE)
			|| this.rabcModelType.equals(RabcModelType.OPERATION)
			|| this.rabcModelType.equals(RabcModelType.RESOURCE)
			|| this.rabcModelType.equals(RabcModelType.GROUP)
			|| this.rabcModelType.equals(RabcModelType.CONSTRAINT)
			|| this.rabcModelType.equals(RabcModelType.SESSION))){
			return true;
		}
		return false;
	}
}
