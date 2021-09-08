package com.springinaction.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by zjjfly on 2017/3/8.
 */
public class TestDataSourceConfig {

    @Bean
    public DataSource dataSource(Environment environment) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?autoReconnect=true");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        return dataSource;
    }
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("dialect","mysql");
//        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPlugins(new Interceptor[]{pageHelper()});
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/springinaction/mybatis/mapper/*.xml");
        sessionFactoryBean.setMapperLocations(resources);
//        Configuration configuration = new Configuration();//通过Configuration配置Mybatis
//        configuration.setMapUnderscoreToCamelCase(true);
        return sessionFactoryBean;
    }

    //每个Mapper对应一个MapperFactoryBean
//    @Bean
    public MapperFactoryBean<SpitterMapper> spitterMapper(SqlSessionFactory sessionFactory){
        MapperFactoryBean<SpitterMapper> spitterMapperFactoryBean = new MapperFactoryBean<>();
        spitterMapperFactoryBean.setMapperInterface(SpitterMapper.class);
        spitterMapperFactoryBean.setSqlSessionFactory(sessionFactory);
        //MapperFactoryBean可以设置SqlSessionFactory或 SqlSessionTemplate属性，当两个属性都设置时，SqlSessionFactory会被忽略
        //        spitterMapperFactoryBean.setSqlSessionTemplate();
        return spitterMapperFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    //SqlSessionTemplate实现了SqlSession
    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        //不需要一个个配置Mapper，可以使用MapperScannerConfigurer，它可以扫描指定的包，默认在这些包中的接口都会被作为映射器加载
        //这些被扫描的接口会使用和Spring组件扫描一样的命名规则命名，如果接口使用@Component和@Named指定名称，那么就使用指定的名称
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.springinaction.mybatis");
        return mapperScannerConfigurer;
    }
}
