package com.qh.component.shiro;

import com.qh.component.redis.CachingShiroSessionDao;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.utils.ShiroUtils;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.sys.entity.MenuEntity;
import com.qh.modules.sys.entity.RoleEntity;
import com.qh.modules.sys.entity.UserEntity;
import com.qh.modules.sys.service.MenuService;
import com.qh.modules.sys.service.RoleService;
import com.qh.modules.sys.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 类的功能描述：
 * Shiro认证
 * shiro的主要模块分别就是授权和认证和会话管理。
 * 认证就是验证用户。比如用户登录的时候验证账号密码是否正确。
 * 我们可以把对登录的验证交给shiro。我们执行要查询相应的用户信息，并传给shiro。
 * 域，shiro从realm获取安全数据（如用户，角色，权限），就是说 SecurityManager 要验证用户身份，
 * 那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；可以把 Realm 看成 DataSource，即安全数据源。
 * 也就是说对于我们而言，最简单的一个 Shiro 应用：
 * 1、应用代码通过 Subject 来进行认证和授权，而 Subject 又委托给 SecurityManager；
 * 2、我们需要给 Shiro 的 SecurityManager 注入 Realm，从而让 SecurityManager 能得到合法的用户及其权限进行判断。
 * Shiro 不提供维护用户 / 权限，而是通过 Realm 让开发人员自己注入。
 * 以后一般继承 AuthorizingRealm（授权）即可；其继承了 AuthenticatingRealm（即身份验证），而且也间接继承了 CachingRealm（带有缓存实现）。
 *
 * Created by Administrator on 2018/4/19.
 */
 @Component
public class MyRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private CachingShiroSessionDao sessionDao;
    /*@Autowired
    private SessionDAO sessionDAO;*/

    /**
     * 授权,也叫访问控制，即在应用中控制谁能访问哪些资源（如访问页面/编辑数据/页面操作等）
     * principals：身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。一个主体可以有多个 principals，但只有一个 Primary principals，一般是用户名 / 密码 / 手机号
     * credentials：证明 / 凭证，即只有主体知道的安全值，如密码 / 数字证书等。
     * 因为我们可以在Shiro中同时配置多个Realm，所以呢身份信息可能就有多个；因此其提供了PrincipalCollection用于聚合这些身份信息
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
   //     UserEntity user = (UserEntity) principals.getPrimaryPrincipal();
        Object principal = principals.getPrimaryPrincipal();
        if (principal instanceof UserEntity) {
            UserEntity user = (UserEntity) principal;
            if(user !=null){
                //根据用户id查询该用户所有的角色,并加入到shiro的SimpleAuthorizationInfo
                //根据用户ID查询角色（role），放入到Authorization里
                List<RoleEntity> roles = roleService.queryByUserId(user.getId(), Constant.YESNO.YES.getValue());
                for (RoleEntity role:roles){
                    info.addRole(role.getId());
                }
                //查询所有该用户授权菜单，并加到shiro的SimpleAuthorizationInfo 做菜单按钮权限控件
                //根据用户ID查询权限（permission），放入到Authorization里。
                Set<String> permissions = new HashSet<>();
                List<MenuEntity> menuEntities=null;
                //超级管理员拥有最高权限
                if(Constant.SUPERR_USER.equals(user.getId())) {
                    menuEntities = menuService.queryList(new HashedMap());
                } else {
                    //普通帐户权限查询
                    menuEntities = menuService.queryByUserId(user.getId());
                }
                for (MenuEntity menuEntity:menuEntities){
                    if(menuEntity != null && !StringUtils.isEmpty(menuEntity.getPermission())){
                        permissions.add(menuEntity.getPermission());
                    }
                }
                info.addStringPermissions(permissions);
            }
        }
        return info;
    }

    /**
     * 认证信息，主要针对用户登录
     * 如果身份验证失败请捕获AuthenticationException或其子类
     * 常见的如： DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）等，具体请查看其继承关系
     * @param token
     * @return  AuthenticationInfo
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String userLoginName = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials()); //得到密码
        UserEntity user = userService.queryByLoginName(userLoginName);
        if (user == null) {
            throw new AuthenticationException("账号密码错误");
        }
        if (Constant.ABLE_STATUS.NO.getValue().equals(user.getStatus())) {
            throw new AuthenticationException("账号被禁用，请联系管理员!");
        }
        //只允许同一账户单个登录
        /*Subject subject = SecurityUtils.getSubject();
        Session nowSession = subject.getSession();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        if(sessions != null && sessions.size() > 0) {
            for (Session session : sessions) {
                if (!nowSession.getId().equals(session.getId()) && (session.getTimeout() == 0
                        || userLoginName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY))))) {
                    sessionDAO.delete(session);
                }
            }
        }*/
        //用户对应的机构集合
        List<String> baidList = userService.queryBapidByUserIdArray(user.getId());
        //用户对应的部门集合
        List<String> bapidList = userService.queryBaidByUserIdArray(user.getId());
        user.setBaids(StringUtils.toStringBySqlIn(baidList));
        user.setBapids(StringUtils.toStringBySqlIn(bapidList));
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassWord(), ByteSource.Util.bytes(user.getSalt()), getName());
        Subject subject = SecurityUtils.getSubject();
        //登录成功后使用Subject.getSession()即可获取会话；其等价于Subject.getSession(true)，
        // 即如果当前没有创建Session对象会创建一个；另外Subject.getSession(false)，如果当前没有创建Session则返回null（不过默认情况下如果启用会话存储功能的话在创建Subject时会主动创建一个Session）
        Serializable sessionId = subject.getSession().getId(); //获取当前会话的唯一标识。
        Session session = sessionDao.doReadSessionWithoutExpire(sessionId);
        session.setAttribute("userId", user.getId());
        session.setAttribute("loginName", user.getLoginName());
        sessionDao.update(session);
        return simpleAuthenticationInfo;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * HashedCredentialsMatcher实现密码验证服务
     * Shiro提供了CredentialsMatcher的散列实现HashedCredentialsMatcher，和之前的PasswordMatcher不同的是，它只用于密码验证，且可以提供自己的盐，而不是随机生成盐，且生成密码散列值的算法需要自己写，因为能提供自己的盐。
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroUtils.algorithmName); //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations); //散列的次数，比如散列两次，相当于 md5(md5(""));
        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }

}
