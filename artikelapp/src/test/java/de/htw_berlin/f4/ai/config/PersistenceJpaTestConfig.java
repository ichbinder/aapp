package de.htw_berlin.f4.ai.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/* fuer autowiring */
@ComponentScan("de.htw_berlin.f4.ai.kbe")
/* erlaubt @Transactional */
@EnableTransactionManagement

/* externe properties file koennen verwendet werden.*/
@PropertySource("classpath:database-test.properties")

/* spring data repositories */
@EnableJpaRepositories("de.htw_berlin.f4.ai.kbe.springdatarepository")

@Configuration
public class PersistenceJpaTestConfig {

	/* haelt zugang zu properties key-value paaren*/
	@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
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
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());	 
		localContainerEntityManagerFactoryBean.
			setPackagesToScan("de.htw_berlin.f4.ai.kbe.model");
		return localContainerEntityManagerFactoryBean;
	}
	 
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		JpaTransactionManager jpaTransactionManager = 
			new JpaTransactionManager(entityManagerFactory().getObject());
		return jpaTransactionManager;
	} 
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean 
	public HibernateExceptionTranslator hibernateExceptionTranslator(){
		return new HibernateExceptionTranslator();
	}
}
