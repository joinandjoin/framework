/**
 * 
 */
package com.huaxia.framework.common.database.utils;

import java.io.FileNotFoundException;
import java.net.URL;

import org.springframework.util.ResourceUtils;

import com.alibaba.druid.filter.FilterAdapter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.proxy.jdbc.DataSourceProxy;

/**
 * @author shilei
 * JdbcUrlFilter.java
 */
public class JdbcUrlFilter extends FilterAdapter {

	@Override
	public void init(DataSourceProxy dataSource) {
		super.init(dataSource);
		DruidDataSource druidDs = (DruidDataSource)dataSource;
		String jdbcUrl = druidDs.getUrl();
		try {
            URL url = ResourceUtils.getURL("classpath:");
            if (url != null){
                jdbcUrl = jdbcUrl.replaceAll("%currpath%", url.getFile());
                druidDs.setUrl(jdbcUrl);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}

}
