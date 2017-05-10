package com.huaxia.framework.security.rabc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the rabc_session database table.
 * 
 */
@Entity
@Table(name="rabc_session")
@NamedQuery(name="RabcSession.findAll", query="SELECT r FROM RabcSession r")
public class RabcSession implements Serializable {
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

	@Column(name="party_no")
	private BigInteger partyNo;

	private String remark;

	@Column(name="sort_no")
	private BigInteger sortNo;

	private String status;

	private BigInteger updater;

	private Timestamp version;

	public RabcSession() {
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

	public BigInteger getPartyNo() {
		return this.partyNo;
	}

	public void setPartyNo(BigInteger partyNo) {
		this.partyNo = partyNo;
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