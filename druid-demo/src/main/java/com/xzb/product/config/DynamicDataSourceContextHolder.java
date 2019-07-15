package com.xzb.product.config;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xzb
 * @date: 2019/7/8
 * @description: 动态数据源上下文管理
 */
@Slf4j
public class DynamicDataSourceContextHolder {
    //存放当前线程使用的数据源类型信息
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    //存放数据源id
    public static List<String> dataSourceIds = new ArrayList<>();

    //设置数据源
    public static void set(String dataSourceType) {
        log.info("set dataSourceType $dataSourceType", dataSourceType);
        contextHolder.set(dataSourceType);
    }

    //获取数据源
    public static String get() {
        log.info("get dataSourceType");
        return contextHolder.get();
    }

    //清除数据源
    public static void clear() {
        log.info("clear dataSourceType");
        contextHolder.remove();
    }

    //判断当前数据源是否存在
    public static boolean isContainsDataSource(String dataSourceId) {
        log.info("exists:$dataSourceId", dataSourceId);
        return dataSourceIds.contains(dataSourceId);
    }
}
