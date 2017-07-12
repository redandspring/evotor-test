package ru.redandspring.springdemo.config;

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

/**
 * @author Alexander Tretyakov
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "ru.redandspring.**.controller"
})
@Import(MainConfiguration.class)
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
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/").setCachePeriod(600);
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/").setCachePeriod(600);
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/").setCachePeriod(600);
    }


}
