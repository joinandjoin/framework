package com.huaxia.framework.security.rabc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the rabc_permission database table.
 * 
 */
@Entity
@Table(name="rabc_permission")
@NamedQuery(name="RabcPermission.findAll", query="SELECT r FROM RabcPermission r")
public class RabcPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="serial_no")
	private String serialNo;

	private String code;

	@Column(name="create_time")
	private Timestamp createTime;

	private BigInteger creator;

	private String name;

	@Column(name="operation_no")
	private BigInteger operationNo;

	@Column(name="permisson_exp")
	private String permissonExp;

	private String remark;

	@Column(name="resource_no")
	private BigInteger resourceNo;

	@Column(name="sort_no")
	private BigInteger sortNo;

	private String status;

	private BigInteger updater;

	private Timestamp version;

	public RabcPermission() {
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public BigInteger getCreator() {
		return this.creator;
	}

	public void setCreator(BigInteger creator) {
		this.creator = creator;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getOperationNo() {
		return this.operationNo;
	}

	public void setOperationNo(BigInteger operationNo) {
		this.operationNo = operationNo;
	}

	public String getPermissonExp() {
		return this.permissonExp;
	}

	public void setPermissonExp(String permissonExp) {
		this.permissonExp = permissonExp;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigInteger getResourceNo() {
		return this.resourceNo;
	}

	public void setResourceNo(BigInteger resourceNo) {
		this.resourceNo = resourceNo;
	}

	public BigInteger getSortNo() {
		return this.sortNo;
	}

	public void setSortNo(BigInteger sortNo) {
		this.sortNo = sortNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigInteger getUpdater() {
		return this.updater;
	}

	public void setUpdater(BigInteger updater) {
		this.updater = updater;
	}

	public Timestamp getVersion() {
		return this.version;
	}

	public void setVersion(Timestamp version) {
		this.version = version;
	}

}