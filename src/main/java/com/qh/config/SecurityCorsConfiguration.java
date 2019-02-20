package com.qh.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 基于Filter的CORS配置：
 * 处理ajax请求跨源资源的问题，因为需要所有 Web API 都需要支持跨源资源共享（CORS），所以需要进行全局设置。
 * Spring Boot 可以全局配置 CORS。这样就不用每个Controller都进行配置了，
 * 解决前后端分离调用时跨域问题.注意安全风险,更细粒度的控制,可在方法上 @CrossOrigin(origins = "url")
 * CORS配置类必须继承WebMvcConfigurerAdapter @Override addCorsMappings方法
 * addMapping：可以被跨域的路径，”/**”表示无限制。
 * allowedMethods：允许跨域访问资源服务器的请求方式，如：POST、GET、PUT、DELETE等，“*”表示无限制。
 * allowedOrigins：”*”允许所有的请求域名访问跨域资源，当设置具体URL时只有被设置的url可以跨域访问。
 * 例如：allowedOrigins(“https://www.baidu.com”),则只有https://www.baidu.com能访问跨域资源。
 * Created by Administrator on 2018/7/27.
 */

@Configuration
public class SecurityCorsConfiguration {

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置你要允许的网站域名，如果全允许则设为 *
  //      config.addAllowedOrigin("http://183.195.133.44:80");
  //      config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin(CorsConfiguration.ALL);
        // 如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        // 这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
