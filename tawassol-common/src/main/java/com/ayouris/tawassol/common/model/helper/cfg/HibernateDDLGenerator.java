package com.ayouris.tawassol.common.model.helper.cfg;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.hibernate.tool.hbm2ddl.Target;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chamakh on 16/10/2016.
 */
public class HibernateDDLGenerator {
    static final String basePackageName = "com.ayouris.tawassol.common.model.entity";

    private final DataSource dataSource;

    @Autowired
    public HibernateDDLGenerator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void generateDDL() throws Exception {

        Map<String, Object> properties = new HashMap<>();
        properties.put(Environment.DATASOURCE, dataSource);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.IMPLICIT_NAMING_STRATEGY, new CustomNamingStrategy(StrategyOptions.builder().build()));
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder(new BootstrapServiceRegistryBuilder().build());
        standardServiceRegistryBuilder.applySettings(properties);
        StandardServiceRegistry serviceRegistry = standardServiceRegistryBuilder.build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);

        EntityScanner.scanPackages(basePackageName).result().forEach(metadataSources::addAnnotatedClass);

        MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder(serviceRegistry);
        MetadataImplementor metadata = (MetadataImplementor) metadataBuilder.build();

        SchemaUpdate update = new SchemaUpdate(serviceRegistry, metadata);
        update.setHaltOnError(true);
        update.setOutputFile("./tawassol-common/src/main/resources/db/ddl_temp.sql");
        update.setDelimiter(";");
        update.setFormat(true);
        update.execute(Target.SCRIPT);

//        SchemaExport export = new SchemaExport(serviceRegistry, metadata);
//        export.setDelimiter(";");
//        export.setHaltOnError(true);
//        export.setFormat(true);
//        export.setOutputFile("D:\\temps\\ddl_test.sql");
//        export.execute(true, false, false, true);

        System.exit(0);
    }

//    public static DataSource dataSource() {
//        PoolProperties poolProperties = new PoolProperties();
//
//        poolProperties.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        poolProperties.setUrl("jdbc:mysql://localhost:3316/frmsaf_db?autoReconnect=true&useUnicode=yes");
//        poolProperties.setUsername("tifawin");
//        poolProperties.setPassword("tifwork");
//
//        DataSource dataSource = new DataSource();
//        dataSource.setPoolProperties(poolProperties);
//
//        return dataSource;
//    }
}

