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
//        Configuration configuration = new Configuration();//??????Configuration??????Mybatis
//        configuration.setMapUnderscoreToCamelCase(true);
        return sessionFactoryBean;
    }

    //??????Mapper????????????MapperFactoryBean
//    @Bean
    public MapperFactoryBean<SpitterMapper> spitterMapper(SqlSessionFactory sessionFactory){
        MapperFactoryBean<SpitterMapper> spitterMapperFactoryBean = new MapperFactoryBean<>();
        spitterMapperFactoryBean.setMapperInterface(SpitterMapper.class);
        spitterMapperFactoryBean.setSqlSessionFactory(sessionFactory);
        //MapperFactoryBean????????????SqlSessionFactory??? SqlSessionTemplate???????????????????????????????????????SqlSessionFactory????????????
        //        spitterMapperFactoryBean.setSqlSessionTemplate();
        return spitterMapperFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    //SqlSessionTemplate?????????SqlSession
    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        //????????????????????????Mapper???????????????MapperScannerConfigurer?????????????????????????????????????????????????????????????????????????????????????????????
        //????????????????????????????????????Spring????????????????????????????????????????????????????????????@Component???@Named?????????????????????????????????????????????
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.springinaction.mybatis");
        return mapperScannerConfigurer;
    }
}
