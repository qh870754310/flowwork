package com.qh.config;

import com.qh.component.redis.CachingShiroSessionDao;
import com.qh.component.redis.ShiroSessionFactory;
import com.qh.component.redis.ShiroSessionListener;
import com.qh.component.shiro.MyRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * 类ShiroConfig的功能描述:
 * Shiro配置
 * Shiro通过Redis管理会话实现集群
 * Session超时的两种情况：
 * shiro在管理session后，在session超时会进行跳转，这里有两种情况需要考虑，一种是ajax方式的请求超时，一种页面跳转请求的超时；
 * 解决问题的思路：通过定义过滤器来检查是否Session过期问题，当前是否session超时，超时判定是否是ajax请求，如果是ajax请求，则在response头部设置session-status值，返回到前端读取到相应值后进行处理；
 *
 * Created by Administrator on 2018/4/19.
 */
@Configuration
public class ShiroConfig {

    /**
     * 定义Shiro安全管理配置
     * 配置SecurityManager并指定Realm
     * 安全管理器:即所有与安全有关的操作都会与securityManager交互；且它管理着所有Subject；
     * @param sessionManager
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(MyRealm myRealm, SessionManager sessionManager/*, EhCacheManager ehCacheManager*/) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //该句引入了我们的第三个配置文件，即MyRealm 文件，改文件又引出了我们的一个概念，
        // Realm：域Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，
        // 那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证
        // 用户是否能进行操作；可以把Realm看成DataSource，即安全数据源。
        //设置realm.
 ///       securityManager.setRealm(myRealm(ehCacheManager));
        securityManager.setRealm(myRealm);
        // 自定义缓存实现 使用redis
        //可选项 最好使用,SessionDao中 doReadSession 读取过于频繁了
        securityManager.setCacheManager(shiroEhcacheManager()); //注入缓存管理器
        // 自定义session管理 使用redis
        //可选项 默认使用ServletContainerSessionManager，直接使用容器的HttpSession，可以通过配置sessionManager，使用DefaultWebSessionManager来替代
        /*将session托管给redis进行管理，便于搭建集群系统*/
        //securityManager配置sessionManager之后，springboot中配置的session有效时间无效（sessionManager管理器覆盖了springboot中session有效时间的配置）。
        securityManager.setSessionManager(sessionManager); //注入session管理器
        return securityManager;
    }

    /**
     * shiro 用户数据注入
     * @return
     */
   /* @Bean("myRealm")
    public MyRealm myRealm(EhCacheManager ehCacheManager){
        MyRealm myRealm = new MyRealm();
        myRealm.setCacheManager(ehCacheManager);
        return myRealm;
    }*/

    /**
     * 用户授权信息Cache, 采用EhCache，本地缓存最长时间应比中央缓存时间短一些，以确保Session中doReadSession方法调用时更新中央缓存过期时间
     * 缓存管理器：此处使用Ehcache实现
     * @return
     */
    @Bean("shiroEhcacheManager")
    public EhCacheManager shiroEhcacheManager() {
        EhCacheManager shiroEhcacheManager = new EhCacheManager();
        //设置ehcache缓存的配置文件
        shiroEhcacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return shiroEhcacheManager;
    }

    /**
     * shiro session的会话管理器
     * 即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；
     * 管理session的生命周期
     *
     * @param sessionDAO
     * @return
     */
    @Bean("sessionManager")
    public SessionManager sessionManager(CachingShiroSessionDao sessionDAO, ShiroSessionFactory shiroSessionFactory, ShiroSessionListener shiroSessionListener) {
        sessionDAO.setPrefix("shiro-session:");
        //注意中央缓存有效时间要比本地缓存有效时间长,以确保Session中doReadSession方法调用时更新中央缓存过期时间
        //session在redis中的保存时间,最好大于session会话超时时间
        sessionDAO.setSeconds(1800); //30分钟
  //      sessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());//用于生成会话ID，默认就是JavaUuidSessionIdGenerator，使用java.util.UUID生成。
        //DefaultWebSessionManager：用于Web环境的实现，可以替代ServletContainerSessionManager，自己维护着会话，直接废弃了Servlet容器的会话管理。
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置全局会话超时时间,设置session过期时间为1小时(单位：毫秒)，默认为30分钟,定义的是全局的session会话超时时间，此操作会覆盖web.xml文件中的超时时间配置
        //意思是，登录30分钟后，会话超时，需要重新登录,设置的最大时间，正负都可以，为负数表示永不超时
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        //url中是否显示session Id,去掉shiro登录时url里的JSESSIONID
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        /**是否在会话过期后会调用SessionDAO的delete方法删除会话 默认true*/
        sessionManager.setDeleteInvalidSessions(true);//删除无效的session,此时的session被保存在了内存里面
        /** 是否定期检查Session的有效性 */
        //是否开启会话验证器任务 默认true
       /* ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        sessionValidationScheduler.setSessionManager(sessionManager);
        sessionManager.setSessionValidationScheduler(sessionValidationScheduler);*/
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 会话验证调度器,用于定期的验证会话是否已过期，如果过期将停止会话;出于性能考虑，一般情况下都是获取会话时来验证
        // 会话是否过期并停止会话的；但是如在web环境中，如果用户不主动退出是不知道会话是否过期的，因此需要定期的检测会话是否过期，
        // Shiro提供了会话验证调度器SessionValidationScheduler来做这件事情。
        // 会话验证器调度时间 设置session的失效扫描间隔，单位为毫秒,
        sessionManager.setSessionValidationInterval(1800000);
        sessionManager.setSessionFactory(shiroSessionFactory);
        //session存储的实现
        sessionManager.setSessionDAO(sessionDAO);
        //sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
        SimpleCookie simpleCookie = new SimpleCookie("SHRIOSESSIONID");
 ///       simpleCookie.setMaxAge(1800); //设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie
 ///       simpleCookie.setHttpOnly(true); //如果设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        sessionManager.setSessionIdCookie(simpleCookie);
        Collection list = new ArrayList();
        list.add(shiroSessionListener);
        sessionManager.setSessionListeners(list);//设置会话监听器
        return sessionManager;
    }

    /**
     * Shiro主过滤器本身功能十分强大,其强大之处就在于它支持任何基于URL路径表达式的、自定义的过滤器的执行
     * Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截,Shiro对基于Spring的Web应用提供了完美的支持
     * shiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * ShiroFilter是整个Shiro的入口点，用于拦截需要安全控制的请求进行处理
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     * anno代表不需要授权即可访问，对于静态资源，访问权限都设置为anno
     * authc表示需要登录才可访问
     *
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new MShiroFilterFactoryBean();
        //Shiro的核心安全接口,这个属性是必须的(// 必须设置 SecurityManager)
        //这句的配置，即引入设置shiro的控制中心，即securityManager ，安全管理器；即所有与安全有关的操作都会与securityManager交互；
        // 且它管理着所有Subject；可以看出它是Shiro的核心，它负责与后边介绍的其他组件进行交互，如果学习过SpringMVC，
        // 你可以把它看成DispatcherServlet前端控制器；关于subject ，你就理解为是每一个访问系统的用户对象即可，所有的访问用户的情况
        // 都是一种subject 的体现，它们又统一被securityManager 管理
        shiroFilter.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.html"页面
        // 登录路径,shiro配置的loginUrl在session过期时跳转至登录页面url的过程是针对普通数据访问方式的情况下，在异步数据访问方式下需要另外处理。
        shiroFilter.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilter.setSuccessUrl("/index.html");
        //未授权界面;用户访问无权限的链接时跳转此页面
        shiroFilter.setUnauthorizedUrl("/403.html");
        //自定义拦截器
   //     Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
   //     filtersMap.put("kickout", kickoutSessionControlFilter());
   //     filtersMap.put("shirosessionInvalid", LoginFilter());
   //     shiroFilter.setFilters(filtersMap);
        //shiro不拦截资源,过滤链定义
        //shiro配置过滤规则少量的话可以用hashMap,数量多了要用LinkedHashMap,保证有序
        //shiro自己的过滤器,anon，表示不拦截的路径，authc,表示拦截的路径
        //匹配时，首先匹配anon的，然后最后匹配authc
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/druid/**", "anon");
        filterMap.put("/app/**", "anon");
        filterMap.put("/login/login", "anon");
        filterMap.put("/**/*.css", "anon");
        filterMap.put("/**/*.js", "anon");
        filterMap.put("/*.html", "anon");
        filterMap.put("/**/*.html", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/plugins/**", "anon");
        filterMap.put("/login/captcha", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/**", "anon");
        filterMap.put("/**", "authc");//登录过的不拦截
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     * 必须配置这个类
     * shiro有其自己管理生命周期的类，各个bean需要Dependon这个类进行加载
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    /*@Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager();
        kickoutSessionControlFilter.setSessionManager();
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
        return kickoutSessionControlFilter;
    }*/

    /**
     * 并发登录控制
     * @return
     */
    /*@Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter(){
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        kickoutSessionControlFilter.setCacheManager(ehCacheManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
        kickoutSessionControlFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(1);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/login?kickout=1");
        return kickoutSessionControlFilter;
    }*/

    /**
     * AOP式方法级权限检查
     * 以下是两个启用注解的权限控制
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

     /*1.LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。主要是AuthorizingRealm类的子类，以及EhCacheManager类。
    2.HashedCredentialsMatcher，这个类是为了对密码进行编码的，防止密码在数据库里明码保存，当然在登陆认证的生活，这个类也负责对form里输入的密码进行编码。
    3.ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
    4.EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
    5.SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
    6.ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
    7.DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
    8.AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。*/


    /*
    Shiro提供SessionDAO用于会话的CRUD，即DAO（Data Access Object）模式实现：
    //如DefaultSessionManager在创建完session后会调用该方法；如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；返回会话ID；主要此处返回的ID.equals(session.getId())；
    Serializable create(Session session);
    //根据会话ID获取会话
    Session readSession(Serializable sessionId) throws UnknownSessionException;
    //更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
    void update(Session session) throws UnknownSessionException;
    //删除会话；当会话过期/会话停止（如用户退出时）会调用
    void delete(Session session);
    //获取当前所有活跃用户，如果用户量多此方法影响性能
    Collection<Session> getActiveSessions();
    */
}
