package com.qh.modules.activiti.config;

import org.activiti.rest.common.application.ContentTypeResolver;
import org.activiti.rest.common.application.DefaultContentTypeResolver;
import org.activiti.rest.service.api.RestResponseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2018/4/24.
 */

@Configuration
public class RestConfiguration {

    @Bean()
    public RestResponseFactory restResponseFactory() {
        RestResponseFactory restResponseFactory = new RestResponseFactory();
        return restResponseFactory;
    }

    @Bean()
    public ContentTypeResolver contentTypeResolver() {
        ContentTypeResolver contentTypeResolver = new DefaultContentTypeResolver();
        return contentTypeResolver;
    }
}
