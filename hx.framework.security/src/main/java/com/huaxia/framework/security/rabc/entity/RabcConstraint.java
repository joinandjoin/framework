package com.huaxia.framework.security.rabc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the rabc_constraint database table.
 * 
 */
@Entity
@Table(name="rabc_constraint")
@NamedQuery(name="RabcConstraint.findAll", query="SELECT r FROM RabcConstraint r")
public class RabcConstraint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="serial_no")
	private String serialNo;

	private String code;

	@Column(name="constraint_exp")
	private String constraintExp;

	@Column(name="create_time")
	private Timestamp createTime;

	private BigInteger creator;

	private String name;

	private String remark;

	@Column(name="sort_no")
	private BigInteger sortNo;

	private String status;

	private BigInteger type;

	private BigInteger updater;

	private Timestamp version;

	public RabcConstraint() {
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

	public String getConstraintExp() {
		return this.constraintExp;
	}

	public void setConstraintExp(String constraintExp) {
		this.constraintExp = constraintExp;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public BigInteger getType() {
		return this.type;
	}

	public void setType(BigInteger type) {
		this.type = type;
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