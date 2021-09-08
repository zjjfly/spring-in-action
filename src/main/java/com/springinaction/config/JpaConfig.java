package com.springinaction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by zjjfly on 2017/2/11.
 */
//启用JpaRepository需要的配置
@Configuration
@EnableJpaRepositories(basePackages = "com.springinaction.data",
        transactionManagerRef = "txManager",
        entityManagerFactoryRef = "entityManagerFactory",
        repositoryImplementationPostfix = "Impl")
public class JpaConfig {
}
