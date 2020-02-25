package com.demo.epidemic;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Author eddie
 * @Date 2020-02-25 15:17
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = "com.demo.epidemic.mapper")
public class MyBatisConfig {

    private static Logger logger = Logger.getLogger(MyBatisConfig.class);

    /**
     * @return 配置数据源 dataSource
     */
    @Bean(name = "dataSource", destroyMethod = "close")
    public BasicDataSource basicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/epidemic");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        dataSource.setInitialSize(3);
        dataSource.setMaxActive(50);
        //最多空闲
        dataSource.setMaxIdle(1);
        //最大等待时间
        dataSource.setMaxWait(4000);
        //是否自动提交事务
        dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }

    /**
     * @return 配置SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //设置要使用的数据源
        factoryBean.setDataSource(dataSource);

        SqlSessionFactory factory = null;
        try {
            //设置映射xml文件的路径
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:com/demo/epidemic/mapper/*Mapper.xml");
            factoryBean.setMapperLocations(resources);
            factory = factoryBean.getObject();
        } catch (Exception e) {
            logger.error("解析映射xml文件时异常：" + e.getMessage());
        }
        //设置xml文件中的类所在的包
        factoryBean.setTypeAliasesPackage("com.demo.epidemic.beans");

        //为了让mybatis自动将下划线分隔的列名转换为驼峰表示的属性名
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        return factory;
    }

}
