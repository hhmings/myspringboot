package com.hhm.jspdemo.config.datasources;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/11/17 16:00
 */
@Configuration
@Slf4j
@EnableTransactionManagement
@MapperScan(basePackages = DataSourceTestDbConfig.PACKAGE_PATH, sqlSessionFactoryRef = DataSourceTestDbConfig.SQL_SESSION_FACTORY)
public class DataSourceTestDbConfig {

    public static final String PACKAGE_PATH = "mapper";
    public static final String SQL_SESSION_FACTORY = "sqlTestDbSessionFactory";
    public static final String DATA_SOURCE_NAME = "test_db";
    public static final String TX_MANAGER = "testDbTransactionManager";

    @Value("${mysql.test_db.url}")
    private String url;
    @Value("${mysql.test_db.username}")
    private String username;
    @Value("${mysql.test_db.password}")
    private String password;

    @Value("${mybatis.config.path}")
    private String MYBATIS_CONFIG_PATH;


    @Bean(name = DATA_SOURCE_NAME)
    public DataSource getDataSource() {
        return MybatisDataSourceConfigHelper.createDruidDataSource(url, username, password);
    }

    @Bean(name = TX_MANAGER)
    public DataSourceTransactionManager getTransactionManager(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) {
        return MybatisDataSourceConfigHelper.getTransactionManager(dataSource);
    }

    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory getSqlSessionFactoryData(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) {
        return MybatisDataSourceConfigHelper.getSqlSessionFactoryData(MYBATIS_CONFIG_PATH, dataSource);
    }

}
