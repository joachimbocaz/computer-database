package excilys.formation.java.cbd.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages="excilys.formation.java.cbd")
public class SpringConfigurationContext extends AbstractContextLoaderInitializer{

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfigurationContext.class);
		return rootContext;
	}
	
	@Bean
	public DataSource hikariDataSource() {
		return new HikariDataSource(new HikariConfig("/datasource.properties"));
	}
	
	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource hikariDataSource){
		return new NamedParameterJdbcTemplate(hikariDataSource);
	}
	
	 @Bean
	 public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		  LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		  entityManagerFactoryBean.setDataSource(hikariDataSource());
		  entityManagerFactoryBean.setPackagesToScan(new String[] { "excilys.formation.java.cbd.model" });
		  JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		  entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		  entityManagerFactoryBean.setJpaProperties(additionalProperties());
 
      return entityManagerFactoryBean;
   }
	 
	@Bean
	public PlatformTransactionManager transactionManager() {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	    return transactionManager;
	}
	 
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	    return new PersistenceExceptionTranslationPostProcessor();
	}
	 
	Properties additionalProperties() {
	    Properties properties = new Properties();
//	    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "false");
	       
	    return properties;
	}

}
