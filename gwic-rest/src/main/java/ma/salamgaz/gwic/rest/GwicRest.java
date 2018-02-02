package ma.salamgaz.gwic.rest;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import ma.salamgaz.gwic.common.repository.impl.CommonRepositoryFactoryBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEncryptableProperties
@SpringBootApplication(scanBasePackages = "ma.salamgaz.gwic")
@EnableJpaRepositories(basePackages = {"ma.salamgaz.gwic.repository","ma.salamgaz.gwic.security.repository"}, repositoryFactoryBeanClass = CommonRepositoryFactoryBean.class)
@EntityScan(basePackages = {"ma.salamgaz.gwic.common.model","ma.salamgaz.gwic.security.model", "ma.salamgaz.gwic.admin.model"})
public class GwicRest {
    
    public static void main(String[] args) {
        SpringApplication.run(GwicRest.class, args);
    }
   
}
