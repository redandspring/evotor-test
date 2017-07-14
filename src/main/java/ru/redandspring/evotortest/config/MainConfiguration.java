package ru.redandspring.evotortest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Alexander Tretyakov
 */
@Configuration
@ComponentScan(basePackages = {
        "ru.redandspring.**.service",
        "ru.redandspring.**.repository"
})
@PropertySource(value="classpath:main.properties")
@Import(MySQLDataSource.class)
public class MainConfiguration{

}
