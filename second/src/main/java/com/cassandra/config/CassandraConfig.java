package com.cassandra.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Objects;

@Configuration
@PropertySource("classpath:application.properties")
@EnableCassandraRepositories(basePackages = "com.cassandra.entity")
public class CassandraConfig extends AbstractCassandraConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConfig.class);

    @Value("${spring.data.cassandra.port}")
    private Integer port;
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keySpace;
    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoint;


    @Bean
    @Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactPoint);
        cluster.setPort(port);
        return cluster;
    }

    @Bean
    @Override
    public CassandraSessionFactoryBean session() {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(Objects.requireNonNull(cluster().getObject()));
        session.setKeyspaceName(getKeyspaceName());
        session.setConverter(new MappingCassandraConverter(new CassandraMappingContext()));
        session.setSchemaAction(SchemaAction.CREATE_IF_NOT_EXISTS);
        return session;
    }

    @Bean
    @Override
    public CassandraAdminTemplate cassandraTemplate() {
        return new CassandraAdminTemplate(Objects.requireNonNull(session().getObject()),
                new MappingCassandraConverter(new CassandraMappingContext()));
    }

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
}
