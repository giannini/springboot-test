package com.gia.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "testEntityManagerFactory", transactionManagerRef = "testTransactionManager", basePackages = {
		"com.gia.repository" })
public class DataSourceConfig {

	@Autowired
	@Qualifier("testDataSource")
	private DataSource testDataSource;

	@Autowired
	private JpaProperties jpaProperties;

	@Primary
	@Bean(name = "testEntityManager")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
	}

	@Primary
	@Bean(name = "testEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(testDataSource).properties(getVendorProperties(testDataSource))
				.packages("com.gia.domain")
				.persistenceUnit("testPersistenceUnit").build();
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

	@Primary
	@Bean(name = "testTransactionManager")
	public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
	}

}
