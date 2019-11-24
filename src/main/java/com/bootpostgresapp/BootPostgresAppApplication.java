package com.bootpostgresapp;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.bootpostgresapp"})
@EnableJpaRepositories
@EnableTransactionManagement
public class BootPostgresAppApplication implements CommandLineRunner{

    @Autowired
    private ApplicationContext appContext;
    
	public static void main(String[] args) {
		SpringApplication.run(BootPostgresAppApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		/*
		 * String[] beans = appContext.getBeanDefinitionNames(); Arrays.sort(beans); for
		 * (String bean : beans) { System.out.println(bean); }
		 * 
		 * //service.fetchData(1);
		 * 
		 */}
	
	/*
	 * @Autowired private Environment env;
	 * 
	 * @Autowired private PlatformTransactionManager platformTransactionManager;
	 * 
	 * @PostConstruct public void configureJpaTransactionManager() {
	 * ((JpaTransactionManager) this.platformTransactionManager).setDefaultTimeout(
	 * Integer.parseInt(env.getProperty("transaction.timeout", "30"))); }
	 */

}
