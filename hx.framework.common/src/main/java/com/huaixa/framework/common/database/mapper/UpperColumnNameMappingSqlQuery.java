package com.huaixa.framework.common.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.object.MappingSqlQuery;

public class UpperColumnNameMappingSqlQuery extends MappingSqlQuery<Map<String,Object>> {

	private UpperColumnNameMapRowMapper rowMapper = new UpperColumnNameMapRowMapper();
	
	@Override
	protected Map<String, Object> mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		return rowMapper.mapRow(rs, rowNum);
	}
	
}
