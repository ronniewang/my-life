package com.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import com.test.db.entity.EventTypeEntity;

@Configuration
public class RestConfiguration extends RepositoryRestConfigurerAdapter {
 
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(EventTypeEntity.class);
        config.setSortParamName("s");
    }
}