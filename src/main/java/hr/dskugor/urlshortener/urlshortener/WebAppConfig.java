package hr.dskugor.urlshortener.urlshortener;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  
public class WebAppConfig implements WebMvcConfigurer {  

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/statistics/*").setViewName("statistics");
        registry.addViewController("/help").setViewName("help");
    }
}