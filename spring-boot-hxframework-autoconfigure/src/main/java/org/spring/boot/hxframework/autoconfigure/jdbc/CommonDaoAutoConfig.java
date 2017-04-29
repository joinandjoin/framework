package org.spring.boot.hxframework.autoconfigure.jdbc;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.huaxia.framework.common.database.repository.ICommonDAO;
import com.huaxia.framework.common.database.repository.support.CommonDaoImpl;

@Configuration
@ConditionalOnClass({LocalContainerEntityManagerFactoryBean.class,SqlSessionFactory.class,JdbcTemplate.class,DataSource.class})
@AutoConfigureAfter({JdbcTemplateAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,MybatisAutoConfiguration.class})
public class CommonDaoAutoConfig {
	
	@Bean
	@ConditionalOnMissingBean
	public ICommonDAO setBaseCommonDaoByEnityFactory(LocalContainerEntityManagerFactoryBean bean){
		CommonDaoImpl dao = new CommonDaoImpl();
		dao.setEntityManagerFactoryBean(bean);
		return dao;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public ICommonDAO setBaseCommonDaoBySessionFactioy(SqlSessionFactory bean){
		CommonDaoImpl dao = new CommonDaoImpl();
		dao.setSqlSessionFactory(bean);
		return dao;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public ICommonDAO setBaseCommonDaoByJdbcTemplate(JdbcTemplate bean){
		CommonDaoImpl dao = new CommonDaoImpl();
		dao.setJdbcTemplate(bean);
		return dao;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public ICommonDAO setBaseCommonDaoByDataSource(DataSource bean){
		CommonDaoImpl dao = new CommonDaoImpl();
		dao.setDataSource(bean);
		return dao;
	}
}
