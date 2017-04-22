package com.huaixa.framework.common.database.mapper;

import org.springframework.jdbc.core.ColumnMapRowMapper;

import com.huaixa.framework.common.utils.CommonUtils;

public class UpperColumnNameMapRowMapper extends ColumnMapRowMapper {

	@Override
	protected String getColumnKey(String columnName) {
		// TODO Auto-generated method stub
		String oriColumnName = super.getColumnKey(columnName);
		if (CommonUtils.isEmpty(oriColumnName)){
			return oriColumnName;
		}
		return oriColumnName.toUpperCase();
	}
}
