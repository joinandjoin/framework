package com.huaxia.framework.common.database.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameterValue;

import com.huaxia.framework.common.exception.OrmDataAccessException;


public interface ICommonDAO  {
	
	public String insert(String tableName,Map<String,?> params) throws OrmDataAccessException;
	
	public String insert(String paramString1, Map<String, ?> paramMap, String paramString2) throws OrmDataAccessException;
	
	public void updateByParams(String sqlWithParams, SqlParameterValue... params) throws OrmDataAccessException;
	
	public void updateByNamedParams(String sqlWithNamedParams,Map<String,SqlParameterValue> paramMap) throws OrmDataAccessException;
	
	public <T> List<T> findBean(String sql,SqlParameterValue[] parameters,Class<T> t) throws OrmDataAccessException;
	
	public List<Map<String,Object>> find(String sql,SqlParameterValue[] parameters) throws OrmDataAccessException;
	
    public Boolean execSQL(String strSQL) throws OrmDataAccessException;
	
	public Boolean execSQLBatch(String[] strSQLs) throws OrmDataAccessException;	
	
	public List<Map<String,Object>> execQuery(String strSQL) throws OrmDataAccessException;
	
	public Boolean checkIDExist(String targetTable,String targetColumn,String checkValue) throws OrmDataAccessException;

    public Boolean execSQLWithParams(String sql, Object... args) throws OrmDataAccessException;
    
    public Timestamp getSysTimestamp() throws OrmDataAccessException;
    
    public long getSysDate() throws OrmDataAccessException;
}
