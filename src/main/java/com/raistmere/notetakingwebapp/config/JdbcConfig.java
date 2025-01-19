package com.raistmere.notetakingwebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories
public class JdbcConfig extends AbstractJdbcConfiguration {

    private Environment env;

    @Autowired
    public JdbcConfig(Environment env) {

        this.env = env;
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(env.getProperty("DATASOURCE_URL"));
        dataSource.setUsername(env.getProperty("DATASOURCE_USERNAME"));
        dataSource.setPassword(env.getProperty("DATASOURCE_PASSWORD"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {

        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {

        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    TransactionManager transactionTemplate(DataSource dataSource){

        return new DataSourceTransactionManager(dataSource);
    }
}
