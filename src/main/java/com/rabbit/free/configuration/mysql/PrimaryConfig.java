package com.rabbit.free.configuration.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.sql.DataSource;


/**
 * Created by wei.liu on 2017/3/3.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages = {"com.rabbit.free.repository"}) //设置Repository所在位置
public class PrimaryConfig {
  /**
   * primary datasource.
   */
  @Autowired
  @Qualifier("primaryDataSource")
  private DataSource primaryDataSource;

  /**
   * initialize primary EntityManager.
   * @param builder EntityManagerFactoryBuilder
   * @return EntityManager
   */
  @Primary
  @Bean(name = "entityManagerPrimary")
  public EntityManager entityManager(final EntityManagerFactoryBuilder builder) {
    return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
  }

  /**
   * initialize LocalContainerEntityManagerFactoryBean.
   * @param builder EntityManagerFactoryBuilder
   * @return LocalContainerEntityManagerFactoryBean
   */
  @Primary
  @Bean(name = "entityManagerFactoryPrimary")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(
          EntityManagerFactoryBuilder builder) {
    return builder
            .dataSource(primaryDataSource)
            .properties(getVendorProperties(primaryDataSource))
            .packages("com.rabbit.free.entity") //设置实体类所在位置
            .persistenceUnit("primaryPersistenceUnit")
            .build();
  }

  @Autowired
  private JpaProperties jpaProperties;

  private Map<String, String> getVendorProperties(DataSource dataSource) {
    return jpaProperties.getHibernateProperties(dataSource);
  }

  @Primary
  @Bean(name = "transactionManagerPrimary")
  public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
  }
}
