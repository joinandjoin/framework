package com.huaxia.framework.common.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.object.MappingSqlQuery;

public class ColumnMapMappingSqlQuery extends MappingSqlQuery<Map<String,Object>> {

	private ColumnMapRowMapper rowMapper = new ColumnMapRowMapper();
	
	@Override
	protected Map<String, Object> mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		return rowMapper.mapRow(rs, rowNum);
	}
	
}
