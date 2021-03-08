package com.tweetapp.tweet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class DbConfiguration {

    @Value( "${url}" )
    private String url;

    @Value("${driverClassName}")
    private String driver;

    @Value("${usernameDb}")
    private String username;

    @Value("${password}")
    private String password;

    /**
     * Setting the data source
     * @return
     */

    @Bean
    public DataSource getDataSource() {
        System.out.println(url+" "+driver+" "+username+" "+password);
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(url+"?createDatabaseIfNotExist=true");
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

}
