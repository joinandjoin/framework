package com.huaxia.framework.security.rabc.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

interface IRabcEntity {

	String getSerialNo();

	void setSerialNo(String serialNo);

	String getCode();

	void setCode(String code);

	String getName();

	void setName(String name);
	
	BigInteger getSortNo();

	void setSortNo(BigInteger sortNo);

	String getStatus();

	void setStatus(String status);
	
	BigInteger getCreator();

	void setCreator(BigInteger creator);

	BigInteger getUpdater();

	void setUpdater(BigInteger updater);
	
	String getRemark();

	void setRemark(String remark);

	Timestamp getCreateTime();

	void setCreateTime(Timestamp createTime);

	Timestamp getVersion();

	void setVersion(Timestamp version);
	
	//--------------------------------------
	default String getConstraintExp() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setConstraintExp(String constraintExp) throws Exception {
		throw new Exception("no support method!!");
	};
	
	default BigInteger getRelateGroupNo() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setRelateGroupNo(BigInteger relateGroupNo) throws Exception {
		throw new Exception("no support method!!");
	};
	
	default BigInteger getPartyNo() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setPartyNo(BigInteger partyNo) throws Exception {
		throw new Exception("no support method!!");
	};
	
	default BigInteger getType() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setType(BigInteger type) throws Exception {
		throw new Exception("no support method!!");
	};
	
	default BigInteger getConstraintNo() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setConstraintNo(BigInteger constraintNo) throws Exception {
		throw new Exception("no support method!!");
	};
	
	default BigInteger getGroupNo() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setGroupNo(BigInteger groupNo) throws Exception {
		throw new Exception("no support method!!");
	};
	
	default BigInteger getRoleNo() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setRoleNo(BigInteger roleNo) throws Exception {
		throw new Exception("no support method!!");
	};
	
	default BigInteger getOperationNo() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setOperationNo(BigInteger operationNo) throws Exception {
		throw new Exception("no support method!!");
	};

	default String getPermissonExp() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setPermissonExp(String permissonExp) throws Exception {
		throw new Exception("no support method!!");
	};
	
	default BigInteger getResourceNo() throws Exception {
		throw new Exception("no support method!!");
	};

	default void setResourceNo(BigInteger resourceNo) throws Exception {
		throw new Exception("no support method!!");
	};
	
	default public BigInteger getPermisNo() throws Exception {
		throw new Exception("no support method!!");
	};

	default public void setPermisNo(BigInteger permisNo) throws Exception {
		throw new Exception("no support method!!");
	};
}