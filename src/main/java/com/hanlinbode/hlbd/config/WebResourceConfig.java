package com.hanlinbode.hlbd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebResourceConfig extends WebMvcConfigurerAdapter {
    @Value("${custom.resourceLocation}")
    private String resourceLocation;

    @Value("${custom.resourceLocationProtocol}")
    private String protocol;

    @Value("${custom.tmpLocation}")
    private String tmpLocation;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations(protocol+resourceLocation);
        super.addResourceHandlers(registry);
    }

}
