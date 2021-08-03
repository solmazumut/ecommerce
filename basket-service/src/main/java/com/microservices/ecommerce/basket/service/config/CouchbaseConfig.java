package com.microservices.ecommerce.basket.service.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
@ConfigurationProperties(prefix = "couchbase-config")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {
    private String connectionString;
    private String userName;
    private String password;
    private String bucketName;

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
