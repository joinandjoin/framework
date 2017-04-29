package com.huaxia.framework.common.database.callback;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.MetaDataAccessException;

import com.huaxia.framework.common.exception.OrmDataAccessException;
import com.huaxia.framework.common.utils.CommonUtils;

public class DataBaseMetaDataCallback implements DatabaseMetaDataCallback {
	private static final Log logger = LogFactory.getLog(DataBaseMetaDataCallback.class);

	private int operType = 0;
	
	private String catalog=null;
	private String schema=null;
	private String table=null;
	
	/**
	 * 
	 * @param operType int 0:获取DatabaseMetaData，1:获取primkeys,2:获取UniqueColumns
	 * @param catalog 库名
	 * @param schema 用户名
	 * @param table 表名
	 */
	public DataBaseMetaDataCallback(int operType, String catalog, String schema, String table) {
		super();
		this.operType = operType;
		this.catalog = catalog;
		this.schema = schema;
		this.table = table;
	}
	
	public DataBaseMetaDataCallback() {
		super();
		this.operType = 0;
		this.catalog = null;
		this.schema = null;
		this.table = null;
	}

	@Override
	public Object processMetaData(DatabaseMetaData dbMetaData) throws SQLException,
			MetaDataAccessException {
		if (0==this.getOperType()){
			return dbMetaData;
		} else if (1==this.getOperType()){
			return getPrimkeyColumnNames(dbMetaData);
		} else if (2==this.getOperType()){
			return getUniqueColumns(dbMetaData);
		}
		return null;
	}
	
	private String[] getPrimkeyColumnNames(DatabaseMetaData dbmd) throws OrmDataAccessException {
		ResultSet primKeys = null;
		String pkName = null;
		List<String> pkNames = new ArrayList<String>();
		try {
			primKeys = dbmd.getPrimaryKeys(this.getCatalog(), this.getSchema(), this.getTable());
			while (primKeys != null && primKeys.next()){
				pkName = primKeys.getString("COLUMN_NAME");
				pkNames.add(pkName);
			}
		} catch (SQLException e) {
			String info = "取得Primkey错误："+CommonUtils.printException(e);
			logger.info(info);
			throw new OrmDataAccessException(info);
		}
		return pkNames.toArray(new String[0]);
	}
	
	private String[] getUniqueColumns(DatabaseMetaData dbmd) throws OrmDataAccessException {
		ResultSet uniques = null;
		String uniqueName = null;
		List<String> uniqueNames = new ArrayList<String>();
		try {
			uniques = dbmd.getIndexInfo(this.getCatalog(), this.getSchema(), this.getTable(), true, false);
			while (uniques != null && uniques.next()){
				uniqueName = uniques.getString("COLUMN_NAME");
				uniqueNames.add(uniqueName);
			}
		} catch (SQLException e) {
			String info = "取得unique错误："+CommonUtils.printException(e);
			logger.info(info);
			throw new OrmDataAccessException(info);
		}
		return uniqueNames.toArray(new String[0]);
	}

	/**
	 * operType 0:获取DatabaseMetaData，1:获取primkeys,2:获取UniqueColumns
	 * @return
	 */
	public int getOperType() {
		return operType;
	}

	public String getCatalog() {
		return catalog;
	}

	public String getSchema() {
		return schema;
	}

	public String getTable() {
		return table;
	}
}
