package com.huaixa.framework.common.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.object.MappingSqlQuery;

public class BeanPropertyMappingSqlQuery<T> extends MappingSqlQuery<T> {

	BeanPropertyRowMapper<T> rowMapper = new BeanPropertyRowMapper<T>();
	
	public BeanPropertyMappingSqlQuery() {
		super();
	}
	
	public BeanPropertyMappingSqlQuery(Class<T> clazz) {
		super();
		rowMapper.setMappedClass(clazz);
	}

	public BeanPropertyMappingSqlQuery(DataSource ds, String sql,Class<T> clazz) {
		super(ds, sql);
		rowMapper.setMappedClass(clazz);
	}

	public void setMappedClass(Class<T> mappedClass){
		this.rowMapper.setMappedClass(mappedClass);
	}
	
	@Override
	protected T mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		return rowMapper.mapRow(rs, rowNum);
	}
	
}
