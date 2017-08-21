package com.ayouris.tawassol.rest;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEncryptableProperties
@SpringBootApplication(scanBasePackages = "com.ayouris.tawassol")
@EnableJpaRepositories(basePackages = {"com.ayouris.tawassol.repository","com.ayouris.tawassol.security.repository"})
@EntityScan(basePackages = {"com.ayouris.tawassol.common.model","com.ayouris.tawassol.security.model"})
public class TawassolRest {
    public static final String JASYPT_ENCRYPTOR_PASSWORD = "jasypt.encryptor.password";
    public static final String JASYPT_MASTER_PASSWORD = "ayowork";

    public static void main(String[] args) {
        System.setProperty(JASYPT_ENCRYPTOR_PASSWORD, JASYPT_MASTER_PASSWORD);
        SpringApplication.run(TawassolRest.class, args);
    }
}
