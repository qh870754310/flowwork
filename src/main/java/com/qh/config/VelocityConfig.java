package com.qh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import java.util.HashMap;
import java.util.Map;


/**
 * 类VelocityConfig的功能描述:
 * Velocity配置
 * 主要配置多视图实现的视图解析器相关bean实例
 * 其实关键点在于两个：
 * 1、配置order属性
 * 2、配置viewnames属性
 *
 * 注意：
 * return new ModelAndView("jsps/index");//或者return "jsps/index"
 * 对应 /WEB-INF/jsps/index.jsp
 * ==========================
 * 同理：
 * return "thymeleaf/index";//或者return “thymeleaf/index”
 * 对应 /WEB-INF/thymeleaf/index.html
 *
 * Created by Administrator on 2018/4/19.
 */
@Configuration
public class VelocityConfig {
   /* @Bean
    public VelocityViewResolver velocityViewResolver(){
        VelocityViewResolver viewResolver = new VelocityViewResolver();
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setViewNames("*.html");
        viewResolver.setSuffix("");
        viewResolver.setDateToolAttribute("date");
        viewResolver.setNumberToolAttribute("number");
        viewResolver.setToolboxConfigLocation("/WEB-INF/velocity-toolbox.xml");
        viewResolver.setRequestContextAttribute("rc");
        viewResolver.setOrder(0);
        return viewResolver;
    }

    @Bean
    public VelocityConfigurer velocityConfigurer() {
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("classpath:/views/");
        Map<String,Object> map = new HashMap<>();
        map.put("input.encoding","UTF-8");
        map.put("output.encoding","UTF-8");
        map.put("contentType","text/html;charset=UTF-8");
        velocityConfigurer.setVelocityPropertiesMap(map);
        return velocityConfigurer;
    }
*/


    /*@Bean
    public SpringResourceTemplateResolver thymeleafViewResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("WEB-INF/jsp/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafViewResolver());
        return templateEngine;
    }*/

   /* @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setOrder(1);
        return viewResolver;
    }*/

    /*@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewNames("*");
        resolver.setOrder(2);
        return resolver;
    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // templateEngine
        return templateEngine;
    }*/

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
      //  UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        InternalResourceViewResolver urlBasedViewResolver = new InternalResourceViewResolver();
        urlBasedViewResolver.setOrder(1);
        urlBasedViewResolver.setViewClass(JstlView.class);
        urlBasedViewResolver.setPrefix("/WEB-INF/jsp/");
        urlBasedViewResolver.setSuffix(".jsp");
        return urlBasedViewResolver;
    }


    /**
     * velocity视图解析器
     *
     * @return
     */
    @Bean
    public VelocityViewResolver velocityViewResolver() {
        //这里只需要在velocityConfig 里设置文件位置即可
        VelocityViewResolver viewResolver = new VelocityViewResolver();
        viewResolver.setOrder(0);
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setCache(false);
        //通过ViewNames属性来实现，通过请求中返回的视图名称匹配其采用哪个对应的视图解析器处理，从而找到对应prefix下的页面
        viewResolver.setViewNames("*.html");
        viewResolver.setSuffix("");
        viewResolver.setDateToolAttribute("date");
        viewResolver.setNumberToolAttribute("number");
        viewResolver.setToolboxConfigLocation("WEB-INF/velocity-toolbox.xml");
        viewResolver.setRequestContextAttribute("rc");  //request属性引用名称
        return viewResolver;
    }


    /**
     * velocity环境配置
     *
     * @return
     */
    @Bean
    public VelocityConfigurer velocityConfigurer() {
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        //velocity模板根路径
        velocityConfigurer.setResourceLoaderPath("classpath:/views/");
        //添加velocity引擎参数
        Map<String,Object> map = new HashMap<>();
        map.put("input.encoding","UTF-8");
        map.put("output.encoding","UTF-8");
        map.put("contentType","text/html;charset=UTF-8");
        //设置Velocity引擎参数
        velocityConfigurer.setVelocityPropertiesMap(map);
        return velocityConfigurer;
    }
}
