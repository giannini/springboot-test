package com.gia.jdbctemplate.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * 主数据源配置
 * 
 * @author gianni
 *
 */
@Configuration
public class MasterDataSourceConfig {

	@Autowired
	private Environment env;

	@Bean(name = "masterDataSource")
	@Qualifier("masterDataSource")
	public DataSource masterDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.primary.jdbc.url"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.primary.jdbc.driverClassName"));
		dataSource.setUsername(env.getProperty("spring.datasource.primary.jdbc.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.primary.jdbc.password"));
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(20);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		// dataSource.setPoolPreparedStatements(false);
		return dataSource;
	}

	@Bean(name = "masterJdbcTemplate")
	public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
