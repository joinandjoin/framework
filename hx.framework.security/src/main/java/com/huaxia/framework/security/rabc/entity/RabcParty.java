package com.huaxia.framework.security.rabc.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the rabc_party database table.
 * 
 */
@Entity
@Table(name="rabc_party")
@NamedQuery(name="RabcParty.findAll", query="SELECT r FROM RabcParty r")
public class RabcParty implements Serializable, IRabcEntity {
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

	private String remark;

	@Column(name="sort_no")
	private BigInteger sortNo;

	private String status;

	private BigInteger type;

	private BigInteger updater;

	private Timestamp version;

	public RabcParty() {
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getSerialNo()
	 */
	@Override
	public String getSerialNo() {
		return this.serialNo;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setSerialNo(java.lang.String)
	 */
	@Override
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getCode()
	 */
	@Override
	public String getCode() {
		return this.code;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setCode(java.lang.String)
	 */
	@Override
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getCreateTime()
	 */
	@Override
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setCreateTime(java.sql.Timestamp)
	 */
	@Override
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getCreator()
	 */
	@Override
	public BigInteger getCreator() {
		return this.creator;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setCreator(java.math.BigInteger)
	 */
	@Override
	public void setCreator(BigInteger creator) {
		this.creator = creator;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getRemark()
	 */
	@Override
	public String getRemark() {
		return this.remark;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setRemark(java.lang.String)
	 */
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getSortNo()
	 */
	@Override
	public BigInteger getSortNo() {
		return this.sortNo;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setSortNo(java.math.BigInteger)
	 */
	@Override
	public void setSortNo(BigInteger sortNo) {
		this.sortNo = sortNo;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getStatus()
	 */
	@Override
	public String getStatus() {
		return this.status;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setStatus(java.lang.String)
	 */
	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getType()
	 */
	@Override
	public BigInteger getType() {
		return this.type;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setType(java.math.BigInteger)
	 */
	@Override
	public void setType(BigInteger type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getUpdater()
	 */
	@Override
	public BigInteger getUpdater() {
		return this.updater;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setUpdater(java.math.BigInteger)
	 */
	@Override
	public void setUpdater(BigInteger updater) {
		this.updater = updater;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#getVersion()
	 */
	@Override
	public Timestamp getVersion() {
		return this.version;
	}

	/* (non-Javadoc)
	 * @see com.huaxia.framework.security.rabc.entity.IRabcEntity#setVersion(java.sql.Timestamp)
	 */
	@Override
	public void setVersion(Timestamp version) {
		this.version = version;
	}

}