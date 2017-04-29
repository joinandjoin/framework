package com.huaxia.framework.common.enums.database;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;

import com.google.common.collect.Maps;

public enum DatabaseType
{
  DERBY("Apache Derby"), 
  DB2("DB2"), 
  DB2ZOS("DB2ZOS"), 
  HSQL("HSQL Database Engine"), 
  SQLSERVER("Microsoft SQL Server"), 
  MYSQL("MySQL"), 
  ORACLE("Oracle"), 
  POSTGRES("PostgreSQL"), 
  SYBASE("Sybase"), 
  H2("H2");

  private static final Map<String, DatabaseType> nameMap;
  private final String productName;

  private DatabaseType(String productName)
  {
    this.productName = productName;
  }

  public String getProductName() {
    return this.productName;
  }

  public static DatabaseType fromProductName(String productName)
  {
    if (!nameMap.containsKey(productName)) {
      throw new IllegalArgumentException("DatabaseType not found for product name: [" + productName + "]");
    }

    return (DatabaseType)nameMap.get(productName);
  }

  public static DatabaseType fromMetaData(DataSource dataSource)
    throws MetaDataAccessException
  {
    String databaseProductName = JdbcUtils.extractDatabaseMetaData(dataSource, "getDatabaseProductName").toString();

    if ("DB2".equals(databaseProductName)) {
      String databaseProductVersion = JdbcUtils.extractDatabaseMetaData(dataSource, "getDatabaseProductVersion").toString();

      if (!databaseProductVersion.startsWith("SQL")) {
        databaseProductName = "DB2ZOS";
      }
      else
        databaseProductName = JdbcUtils.commonDatabaseName(databaseProductName);
    }
    else
    {
      databaseProductName = JdbcUtils.commonDatabaseName(databaseProductName);
    }
    return fromProductName(databaseProductName);
  }

  static
  {
    nameMap = Maps.newHashMap();
    for (DatabaseType type : values())
      nameMap.put(type.getProductName(), type);
  }
}