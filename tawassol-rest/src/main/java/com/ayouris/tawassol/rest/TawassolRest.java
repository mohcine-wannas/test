package com.ayouris.tawassol.rest;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ayouris.tawassol.common.repository.impl.CommonRepositoryFactoryBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEncryptableProperties
@SpringBootApplication(scanBasePackages = "com.ayouris.tawassol")
@EnableJpaRepositories(basePackages = {"com.ayouris.tawassol.repository","com.ayouris.tawassol.security.repository"}, repositoryFactoryBeanClass = CommonRepositoryFactoryBean.class)
@EntityScan(basePackages = {"com.ayouris.tawassol.common.model","com.ayouris.tawassol.security.model", "com.ayouris.tawassol.admin.model"})
public class TawassolRest {
    
    public static void main(String[] args) {
        SpringApplication.run(TawassolRest.class, args);
    }
   
}
