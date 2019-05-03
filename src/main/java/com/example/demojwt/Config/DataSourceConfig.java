package com.example.demojwt.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource getDataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:mysql://localhost:3306/user_security?createDatabaseIfNotExist=true");
        builder.username("root");
        builder.password("");
        return builder.build();
    }
}
