package com.lung.application.config;

import org.springframework.context.annotation.Configuration;

/**
 * seata-Config
 *
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2020-01-07 11:02
 */
@Configuration
public class SeataConfig {
    /*// 关键步骤，配置 Seata 的代理数据源，代理之前配置的 accountDataSource
    <bean id="accountDataSourceProxy" class="io.seata.rm.datasource.DataSourceProxy">
        <constructor-arg ref="accountDataSource" />
    </bean>

    // 配置 applicationId 和 txServiceGroup，这主要是来标识应用和服务端集群的
    <bean class="io.seata.spring.annotation.GlobalTransactionScanner">
        <constructor-arg value="dubbo-demo-account-service"/>
        <constructor-arg value="my_test_tx_group"/>
    </bean>*/

    /*@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }*/

    /*@Primary
    @Bean("dataSourceProxy")
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }*/


}