package it.gov.mef.cloudify.servlet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.gov.mef.cloudify.model.Ente;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackageClasses = Ente.class)
public class NoiPAServletInitializer extends SpringBootServletInitializer {
 
	
    @Value("${spring.datasource.jndi-name}")
	private String primaryJndiName;
    
    @Value("${spring.datasource.driver-class}")
    private String driverClass;
    
    @Primary
    @Bean(destroyMethod = "", name="dataSource")
    public DataSource jndiDataSource() {
    	JndiDataSourceLookup lookup = new JndiDataSourceLookup();
    	return lookup.getDataSource(primaryJndiName);  	
    }
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NoiPAServletInitializer.class);
    }
 
    public static void main(String[] args) throws Exception {
        SpringApplication.run(NoiPAServletInitializer.class, args);
    }
 
}