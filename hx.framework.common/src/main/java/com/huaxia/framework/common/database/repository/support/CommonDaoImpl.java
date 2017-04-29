package com.huaxia.framework.common.database.repository.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.JdbcUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huaxia.framework.common.database.mapper.BeanPropertyMappingSqlQuery;
import com.huaxia.framework.common.database.mapper.ColumnMapMappingSqlQuery;
import com.huaxia.framework.common.database.repository.ICommonDAO;
import com.huaxia.framework.common.exception.OrmDataAccessException;
import com.huaxia.framework.common.utils.CommonUtils;
import com.huaxia.framework.common.utils.DateUtils;

public class CommonDaoImpl extends AbstractBaseDao implements ICommonDAO {
	
	private static final Log logger = LogFactory.getLog(CommonDaoImpl.class);
	
	@Override
	public void updateByParams(String sqlWithParams, SqlParameterValue... params) throws OrmDataAccessException {
		try {
			SqlUpdate updater =null;
			updater = new SqlUpdate(this.getDataSource(), sqlWithParams);
			List<Object> paramValues = null;
			if (CommonUtils.isNotNullEmpty(params)){
				paramValues = Lists.newArrayList();
				for (SqlParameterValue param : params){
					updater.declareParameter(param);
					paramValues.add(param.getValue());
				}
			}
			updater.compile();			
			updater.update(paramValues);
		} catch (Exception e) {
			throw new OrmDataAccessException("执行updateByParameters 出现异常",e);
		}
	}
	
	@Override
	public void updateByNamedParams(String sqlWithNamedParams,Map<String,SqlParameterValue> paramMap)throws OrmDataAccessException {
		try {
			SqlUpdate updater =null;
			updater= new SqlUpdate(this.getDataSource(), sqlWithNamedParams);
			Map<String,Object> params = null;
			if (CommonUtils.isNotNullEmpty(paramMap)){
				params = Maps.newHashMap();
				for (Map.Entry<String, SqlParameterValue> paramEntry : paramMap.entrySet()){
					String paramName = paramEntry.getKey();
					SqlParameterValue sqlParamValue = paramEntry.getValue();
					Object paramValue = sqlParamValue.getValue();
					updater.declareParameter(sqlParamValue);
					params.put(paramName, paramValue);
				}
			}
			updater.compile();			
			updater.updateByNamedParam(params);
		} catch (Exception e) {
			throw new OrmDataAccessException("执行updateByParameters 出现异常",e);
		}
	}
	
	@Override
	public <T> List<T> findBean(String sql,SqlParameterValue[] parameters,Class<T> t) throws OrmDataAccessException {
		try {
			BeanPropertyMappingSqlQuery<T> query = new BeanPropertyMappingSqlQuery<T>(t);
			query.setDataSource(this.getDataSource());
			query.setSql(sql);
			List<Object> paramValues = null;
			if (CommonUtils.isNotNullEmpty(parameters)){
				paramValues = Lists.newArrayList();
				query.setParameters(parameters);
				for (SqlParameterValue param : parameters){
					 Object value = param.getValue();
					 paramValues.add(value);
				}
			}
			if (paramValues != null){
				return query.execute(paramValues.toArray(new Object[0]));
			} else {
				return query.execute();
			}
		} catch (Exception e) {
			throw new OrmDataAccessException("执行findBean 出现异常",e);
		}
	}
	
	@Override
	public List<Map<String,Object>> find(String sql,SqlParameterValue[] parameters) throws OrmDataAccessException {
		try {
			ColumnMapMappingSqlQuery query = new ColumnMapMappingSqlQuery();
			query.setDataSource(this.getDataSource());
			query.setSql(sql);
			List<Object> paramValues = null;
			if (CommonUtils.isNotNullEmpty(parameters)){
				paramValues = Lists.newArrayList();
				query.setParameters(parameters);
				for (SqlParameterValue param : parameters){
					 Object value = param.getValue();
					 paramValues.add(value);
				}
			}
			if (paramValues != null){
				return query.execute(paramValues.toArray(new Object[0]));
			} else {
				return query.execute();
			}
		} catch (Exception e) {
			throw new OrmDataAccessException("执行find 出现异常",e);
		}
	}
	
	@Override
	public String insert(String tableName,Map<String,?> params) throws OrmDataAccessException {
		SimpleJdbcInsert insertor;
		try {
			insertor = null;
			if (CommonUtils.isNullEmpty(tableName)){
				throw new Exception("param tableName can not be null");
			}
			
			if (CommonUtils.isNullEmpty(params)){
				logger.error("插入表时【"+tableName+"】未指定任何列！！");
				throw new Exception("插入表时【"+tableName+"】未指定任何列！！");
			}
			
			insertor = new SimpleJdbcInsert(getDataSource())
					.withTableName(tableName)
					.usingColumns(params.keySet().toArray(new String[0]));
			insertor.compile();
			
			Number key = insertor.executeAndReturnKey(params);
			return key.longValue()+"";
		} catch (Exception e) {
			throw new OrmDataAccessException("执行insert 出现异常",e);
		}
	}
	
	@Override
	public String insert(String tableName, Map<String, ?> params, String keyColumnName) throws OrmDataAccessException {
		SimpleJdbcInsert insertor = null;
		try {
			if (CommonUtils.isNullEmpty(tableName)) {
				throw new Exception("param tableName can not be null");
			}

			if (CommonUtils.isNullEmpty(params)) {
				logger.error("插入表时【" + tableName + "】未指定任何列！！");
				throw new Exception("插入表时【" + tableName + "】未指定任何列！！");
			}

			insertor = new SimpleJdbcInsert(getDataSource()).withTableName(tableName)
					.usingColumns((String[]) params.keySet().toArray(new String[0]))
					.usingGeneratedKeyColumns(new String[] { keyColumnName });
			insertor.compile();

			Number key = insertor.executeAndReturnKey(params);
			return key.longValue() + "";
			
		} catch (Exception e) {
			throw new OrmDataAccessException("执行insert 出现异常", e);
		}
	}
	
	@Override
	public Boolean execSQL(String strSQL) throws OrmDataAccessException {
		boolean flag = false;
		try {
			this.getJdbcTemplate().execute(strSQL);
			flag = true;
		} catch (Exception e) {
			throw new OrmDataAccessException("执行execSQL 出现异常",e);
		}
		return flag;
	}
	
	@Override
	public Boolean execSQLWithParams(String sql, Object... args) throws OrmDataAccessException {
		boolean flag = false;
		try {
			int count = this.getJdbcTemplate().update(sql, args);
			if (count>0){
				flag = true;
			}
		} catch (Exception e) {
			throw new OrmDataAccessException("执行execSQLWithParams 出现异常",e);
		}
		return flag;
	}
	
	@Override
	public List<Map<String, Object>> execQuery(String strSQL) throws OrmDataAccessException {
		List<Map<String, Object>> result = null;
		try {
			result = this.getJdbcTemplate().queryForList(strSQL);
		} catch (Exception e) {
			throw new OrmDataAccessException("执行execQuery 出现异常", e);
		}
		return result;
	}
	
	@Override
	public Boolean execSQLBatch(String[] strSQLs) throws OrmDataAccessException {
		boolean flag = false;
		try {
			this.getJdbcTemplate().batchUpdate(strSQLs);
			flag = true;
		} catch (Exception e) {
			throw new OrmDataAccessException("执行execSQLBatch 出现异常", e);
		}
		return flag;
	}
	
	@Override
	public Boolean checkIDExist(String targetTable, String targetColumn, String checkValue)
			throws OrmDataAccessException {
		boolean flag = true;
		try {
			List<Map<String, Object>> resData = this.getJdbcTemplate().queryForList(
					" select * from " + targetTable + " where " + targetColumn + "='" + checkValue + "' ");
			if (resData.size() <= 0) {
				flag = false;
			}
		} catch (Exception e) {
			throw new OrmDataAccessException("执行checkIDExist 出现异常", e);
		}
		return flag;

	}	
	
	@Override
	public synchronized Timestamp getSysTimestamp() throws OrmDataAccessException {
		if (this.getSysDate() == -1){
			return null;
		}
		return new Timestamp(this.getSysDate());
	}
	
	@Override
	public synchronized long getSysDate() throws OrmDataAccessException {
		long rtn;
		PreparedStatement ptmt;
		ResultSet rs;
		rtn = 0L;
		ptmt = null;
		rs = null;
		Connection conn = null;
		
		try {
			conn = super.getConnection();
			
			ptmt = conn.prepareStatement(
							"select date_format(now(), '%Y-%m-%d %H:%i:%s') as D");
			DataSourceUtils.applyTransactionTimeout(ptmt, getDataSource());
			rs = ptmt.executeQuery();
			int i = 1;
			if (rs.next()) {
				String dateTimeChars = rs.getString("D");
				rtn = DateUtils.getTimestamp(dateTimeChars).getTime();
				if (i > 1)
					throw new OrmDataAccessException("ERROR_CODE_QUERY_SYSDATE_0002");
				i++;
			}
		} catch (Exception ex) {
			throw new OrmDataAccessException("ERROR_CODE_QUERY_SYSDATE_0001",ex);
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ptmt);
			super.releaseConnection(conn);
		}
		return rtn;
	}
}
