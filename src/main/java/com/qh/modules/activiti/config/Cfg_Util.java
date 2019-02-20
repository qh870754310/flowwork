package com.qh.modules.activiti.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 一些工具Bean
 *
 * Created by Administrator on 2018/4/24.
 */
@Configuration
public class Cfg_Util {

    /**
     * jackson xml util
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
