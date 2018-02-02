package ma.salamgaz.tawassol.repository.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by chamakh on 07/01/2017.
 */
@Configuration
@Profile({"dev", "prod"})
@ConfigurationProperties(prefix = "spring.datasource")
public class PooledDatasourceConfig extends HikariConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        DataSource dataSource = new HikariDataSource(this);

        return dataSource;
    }
}
