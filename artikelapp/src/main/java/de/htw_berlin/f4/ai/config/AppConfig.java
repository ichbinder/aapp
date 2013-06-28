package de.htw_berlin.f4.ai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ComponentScan("de.htw_berlin.f4.ai.kbe")
@Import(PersistenceJpaConfig.class)
@Configuration
public class AppConfig {

	
	@Autowired
	PersistenceJpaConfig dataConfiguration;
	
	
	 
	
}
