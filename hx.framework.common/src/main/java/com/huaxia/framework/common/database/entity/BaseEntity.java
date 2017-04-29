package com.huaxia.framework.common.database.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4960241273386126271L;
	
	private long serialNo;
	
	private String code;
	
	private String name;
	
	private long sortNo;
	
	private String remark;
	
	private String status;
	
	private long creator;
	
	private long updater;
	
	private Timestamp createTime;
	
	private Timestamp  version;
	
	
	public BaseEntity(long serialNo, String code, String name) {
		super();
		this.serialNo = serialNo;
		this.code = code;
		this.name = name;
		this.sortNo = -1;
		this.remark = "";
		this.status = "1";
		this.creator = 0;
		this.updater = 0;
	}

	public BaseEntity(long serialNo, String code) {
		super();
		this.serialNo = serialNo;
		this.code = code;
		this.name = "";
		this.sortNo = -1;
		this.remark = "";
		this.status = "1";
		this.creator = 0;
		this.updater = 0;
		
	}
	
	public BaseEntity(long serialNo) {
		super();
		this.serialNo = serialNo;
		this.code = "";
		this.name = "";
		this.sortNo = -1;
		this.remark = "";
		this.status = "1";
		this.creator = 0;
		this.updater = 0;
	}
	
	public BaseEntity() {
		super();
	}
	
	@Override
	public String toString() {
		StringBuilder sbd = new StringBuilder();
		sbd.append(super.toString()).append("=").append("\"entity info\":{")
		.append("'").append("serialNo").append("':").append("\"").append(this.serialNo).append("\"")
		.append("'").append("code").append("':").append("\"").append(this.code).append("\",")
		.append("'").append("name").append("':").append("\"").append(this.name).append("\",")
		.append("'").append("sortNo").append("':").append("\"").append(this.sortNo).append("\",")
		.append("'").append("remark").append("':").append("\"").append(this.remark).append("\",")
		.append("'").append("status").append("':").append("\"").append(this.status).append("\",")
		.append("'").append("creator").append("':").append("\"").append(this.creator).append("\",")
		.append("'").append("updater").append("':").append("\"").append(this.updater).append("\",")
		.append("'").append("createTime").append("':").append("\"").append(this.createTime).append("\",")
		.append("'").append("version").append("':").append("\"").append(this.version).append("\"")
		.append("}");
		
		return sbd.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new BaseEntity());

		System.out.println(new BaseEntity(8989989899l));
		

		System.out.println(new BaseEntity(8989989899l,"code1","name1"));
		

		System.out.println(new BaseEntity(8989989899l,"code1"));
	}
}
