package config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan (basePackages = {"dao", "controller", "service"})
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() throws SQLException {
       PGSimpleDataSource ds = new PGSimpleDataSource();
       ds.setUser(environment.getProperty("datasource.user"));
       ds.setPassword(environment.getProperty("datasource.password"));
       ds.setURL(environment.getProperty("datasource.url"));
        return ds.unwrap(DataSource.class);
    }
}
