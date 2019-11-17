package com.hhm.jspdemo.config.datasources;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/11/17 16:11
 */
public final class MybatisDataSourceConfigHelper {

    private static final Logger logger = LoggerFactory.getLogger(MybatisDataSourceConfigHelper.class);
    private static String validation_query = "SELECT 1 FROM DUAL";

    public static DataSource createDruidDataSource(String url, String username, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMinIdle(10);
        dataSource.setMaxActive(50);
        dataSource.setMaxWait(30000L);
        dataSource.setInitialSize(10);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(280);
        dataSource.setTimeBetweenEvictionRunsMillis(60000L);
        dataSource.setMinEvictableIdleTimeMillis(300000L);
        dataSource.setValidationQuery(validation_query);
        dataSource.setTestOnBorrow(true);
        dataSource.setTimeBetweenLogStatsMillis(60000L);
        return dataSource;
    }


    public static DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource);
        return txManager;
    }

    public static SqlSessionFactory getSqlSessionFactoryData(String configPath, DataSource dataSource) {
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            ResourcePatternResolver reslover = new PathMatchingResourcePatternResolver();
            bean.setConfigLocation(reslover.getResource(configPath));

            bean.setDataSource(dataSource);
            return bean.getObject();
        } catch (Exception var8) {
            logger.error("failed to create data sql session", var8);
            throw new RuntimeException("failed to create data sql session", var8);
        }
    }
}
