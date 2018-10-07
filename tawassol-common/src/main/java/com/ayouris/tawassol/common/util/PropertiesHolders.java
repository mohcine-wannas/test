package com.ayouris.tawassol.common.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import com.ayouris.tawassol.common.enums.Env;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Getter
@Configuration
@Profile({"dev", "prod"})
@Component
public class PropertiesHolders {
//
//  //----------- flyway configuration --------
//	@Value("${flyway.init-method}")
//    private String initMethod;
//
//	//@Value("${flyway.init-on-migrate}")
//    //private Boolean initOnMigrate;
//
//	@Value("${flyway.baseline-on-migrate}")
//    private Boolean baselineOnMigrate;
//
//	@Value("${flyway.validate-on-migrate}")
//    private Boolean validateOnMigrate;
//
//	@Value("${flyway.baseline-version}")
//    private String baselineVersion;
//
//	@Value("${flyway.encoding}")
//    private String encoding;
//
//	@Value("${flyway.locations}")
//    private String locations;
//
//	//---------  datasource configuration ------
//	@Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//	@Value("${spring.datasource.url}")
//    private String url;
//
//	@Value("${spring.datasource.username}")
//    private String username;
//
//	@Value("${spring.datasource.password}")
//    private String password;
//
//	//@Value("${spring.datasource.type}")
//    //private String type;
//
//	@Value("${spring.datasource.hikari.maximum-pool-size}")
//    private int maximumPoolSize;
//
//	@Value("${spring.datasource.hikari.idle-timeout}")
//    private long idleTimeout;
//
//
//  //-------- JPA Configuration -------------------------------
//	//@Value("${spring.jpa.database}")
//    //private String database;
//
//	@Value("${spring.jpa.database-platform}")
//    private String databasePlatform;
//
//	@Value("${spring.jpa.generate-ddl}")
//    private Boolean generateDdl;
//
//	@Value("${spring.jpa.show-sql}")
//    private Boolean showSql;
//
//	@Value("${spring.jpa.hibernate.ddl-auto}")
//    private String ddlAuto;
//
//	@Value("${spring.jpa.hibernate.id.new_generator_mappings}")
//    private Boolean newGeneratorMappings;
//
//	@Value("${spring.jpa.hibernate.naming.implicit-strategy}")
//    private String implicitStrategy;
//
//
//   //-----------------------------------------------------
//    @Value("${tawassol.deployement.env}")
//    private String envDeployement;
//
//    @Value("${tawassol.default.lang}")
//    private String defaultLang;
//
//    @Value("${tawassol.mail.adress.sender}")
//    protected String fromMail;
//
//    @Value("${tawassol.mail.adress.recepient}")
//    protected String defaultRecepientMail;
//
//    @Value("${tawassol.application.dns}")
//    protected String tawassolDns;
//
//    @Value("${tawassol.mail.adress.copy}")
//    private String copyAddress;

	@Value("${tawassol.upload.logo.path}")
	private String logoPath;

}
