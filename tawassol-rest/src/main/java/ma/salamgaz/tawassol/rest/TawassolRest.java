package ma.salamgaz.tawassol.rest;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import ma.salamgaz.tawassol.common.repository.impl.CommonRepositoryFactoryBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEncryptableProperties
@SpringBootApplication(scanBasePackages = "ma.salamgaz.tawassol")
@EnableJpaRepositories(basePackages = {"ma.salamgaz.tawassol.repository","ma.salamgaz.tawassol.security.repository"}, repositoryFactoryBeanClass = CommonRepositoryFactoryBean.class)
@EntityScan(basePackages = {"ma.salamgaz.tawassol.common.model","ma.salamgaz.tawassol.security.model", "ma.salamgaz.tawassol.admin.model"})
public class TawassolRest {
    
    public static void main(String[] args) {
        SpringApplication.run(TawassolRest.class, args);
    }
   
}
