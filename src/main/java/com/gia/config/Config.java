package com.gia.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class Config {

	@Autowired
	private Environment env;

	@Bean(name = "testDataSource")
	@Qualifier("testDataSource")
	public DataSource testDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.primary.jdbc.url"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.primary.jdbc.driverClassName"));
		dataSource.setUsername(env.getProperty("spring.datasource.primary.jdbc.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.primary.jdbc.password"));
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(20);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		return dataSource;
	}
}
