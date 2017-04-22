package com.huaixa.framework.common.database.utils;

import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

import com.huaixa.framework.common.database.callback.DataBaseMetaDataCallback;
import com.huaixa.framework.common.exception.OrmDataAccessException;
import com.huaixa.framework.common.utils.CommonUtils;

public class DatabaseMetaDataUtils {
	private static final Log logger = LogFactory.getLog(DatabaseMetaDataUtils.class);
	
	/**
	 * 获取当前数据库信息。
	 * @param dataSource 数据源
	 * @return 获取到的DatabaseMetaData
	 * @throws OrmDataAccessException 抛出异常，
	 */
	public static DatabaseMetaData getDatabaseMetaData(DataSource dataSource) throws OrmDataAccessException {
		DatabaseMetaData dbmd = null;
		DatabaseMetaDataCallback callBack = new DataBaseMetaDataCallback();
		try {
			dbmd = (DatabaseMetaData)JdbcUtils.extractDatabaseMetaData(dataSource, callBack);
		} catch (MetaDataAccessException e) {
			String info = "取得数据库MetaData错误："+CommonUtils.printException(e);
			logger.info(info);
			throw new OrmDataAccessException(info);
		}
		return dbmd;
	}
	
	public static String[] getPrimkeyColumnNames(DataSource dataSource,String table) throws OrmDataAccessException {		
		return getPrimkeyColumnNames(dataSource,null,null,table);
	}
	
	public static String[] getPrimkeyColumnNames(DataSource dataSource, String schema,String table) throws OrmDataAccessException {
		return getPrimkeyColumnNames(dataSource,null,schema,table);
	}

	public static String[] getPrimkeyColumnNames(DataSource dataSource,String catalog, String schema,String table) throws OrmDataAccessException {
		String[] pkNames = null;
		DatabaseMetaDataCallback callBack = new DataBaseMetaDataCallback(1,catalog,schema,table);
		try {
			pkNames = (String[])JdbcUtils.extractDatabaseMetaData(dataSource, callBack);
		} catch (MetaDataAccessException e) {
			String info = "取得数据库MetaData错误："+CommonUtils.printException(e);
			logger.info(info);
			throw new OrmDataAccessException(info);
		}
		return pkNames;
	}
	
	public static String getPrimkeyColumnName(DataSource dataSource,String table) throws OrmDataAccessException {
		String[] columnNames = getPrimkeyColumnNames(dataSource,null,null,table);
		if (CommonUtils.isNotEmpty(columnNames)){
			return columnNames[0];
		}
		return null;
	}
	
	public static String getPrimkeyColumnName(DataSource dataSource, String schema,String table) throws OrmDataAccessException {
		String[] columnNames = getPrimkeyColumnNames(dataSource,null,schema,table);
		if (CommonUtils.isNotEmpty(columnNames)){
			return columnNames[0];
		}
		return null;
	}
	
	public static String getPrimkeyColumnName(DataSource dataSource,String catalog, String schema,String table) throws OrmDataAccessException {
		String[] columnNames = getPrimkeyColumnNames(dataSource,catalog,schema,table);
		if (CommonUtils.isNotEmpty(columnNames)){
			return columnNames[0];
		}
		return null;
	}
	
	public static String[] getUniqueColumns(DataSource dataSource,String catalog, String schema,String table) throws OrmDataAccessException {
		String[] uniNames = null;
		DatabaseMetaDataCallback callBack = new DataBaseMetaDataCallback(1,catalog,schema,table);
		try {
			uniNames = (String[])JdbcUtils.extractDatabaseMetaData(dataSource, callBack);
		} catch (MetaDataAccessException e) {
			String info = "取得数据库MetaData错误："+CommonUtils.printException(e);
			logger.info(info);
			throw new OrmDataAccessException(info);
		}
		return uniNames;
	}
}
