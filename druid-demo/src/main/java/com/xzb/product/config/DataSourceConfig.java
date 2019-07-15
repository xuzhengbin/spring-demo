package com.xzb.product.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xzb
 * @date: 2019/7/5
 * @description:
 * 数据源配置，默认使用report数据源
 */
@Configuration
@Slf4j
public class DataSourceConfig {
    @Primary
    @Bean(name = "reportDataSource")
    @ConfigurationProperties("spring.datasource.report")
    public DataSource reportDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "sourceDataSource")
    @ConfigurationProperties("spring.datasource.report-source")
    public DataSource sourceDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("reportTemplate")
    public JdbcTemplate reportJdbcTemplate() {
        return new JdbcTemplate(reportDataSource());
    }

    @Bean
    @Qualifier("sourceTemplate")
    public JdbcTemplate sourceJdbcTemplate() {
        return new JdbcTemplate(sourceDataSource());
    }

    /**
     * 配合mybatis使用，暂时用不到
     * @param reportDataSource
     * @param sourceDataSource
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("reportDataSource") DataSource reportDataSource, @Qualifier("sourceDataSource") DataSource sourceDataSource) {
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put("report", reportDataSource);
        dsMap.put("source", sourceDataSource);
        dataSource.setTargetDataSources(dsMap);
        dataSource.setDefaultTargetDataSource(reportDataSource);

        return dataSource;
    }
}
