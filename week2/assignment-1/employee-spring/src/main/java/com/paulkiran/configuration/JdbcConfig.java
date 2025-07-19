package com.paulkiran.configuration;

import javax.sql.DataSource;

import com.paulkiran.dao.EmployeeDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JdbcConfig {

    @Bean
     public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:testdb");
        ds.setUsername("sa");
        ds.setPassword("password");
        return ds;
    }

    @Bean
    public EmployeeDao employeeDao(){
        return new EmployeeDao(dataSource());
    }
    
}
