package de.htw_berlin.f4.ai.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
 
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@PropertySource("classpath:META-INF/database.properties")
@Configuration
public class PersistenceJpaConfig {

	@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}
	
	@Bean 
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = 
				new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("hibernate.show_sql")));
		hibernateJpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty("hibernate.generateDdl")));
		hibernateJpaVendorAdapter.setDatabasePlatform(env.getProperty("hibernate.dialect"));
		return hibernateJpaVendorAdapter;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());	 
		localContainerEntityManagerFactoryBean.
			setPackagesToScan(env.getProperty("persistence_context.packages_to_scan"));
		return localContainerEntityManagerFactoryBean.getObject();
	}
	
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		return jpaTransactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
}
