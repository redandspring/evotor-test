package ru.redandspring.evotortest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.redandspring.evotortest.config.security.SecurityWebApplicationInitializer;
import ru.redandspring.evotortest.config.security.WebSecurityConfig;

/**
 * @author Alexander Tretyakov
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "ru.redandspring.**.controller",
        "ru.redandspring.**.config.security",
})
@Import({WebSecurityConfig.class, SecurityWebApplicationInitializer.class, MainConfiguration.class})
public class WebConfiguration extends WebMvcConfigurerAdapter
{
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    // equivalents for <mvc:resources/> tags
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(600);
    }


}
