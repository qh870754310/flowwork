package com.qh.modules.app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 类JwtUtils的功能描述:
 * jwt工具类
 * JWT构成
 * JWT由三部分构成：head：头部,  payload：负载信息, signature：认证签名
 * head:jwt的头部由两部分构成，一是jwt声明，二是加密算法声明。{'typ': 'JWT','alg': 'HS256'}
 * 将上面的json进行base64编码，即为jwt第一部分:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
 *
 * 顾名思义，payload用于保存有效信息
 * iss: jwt签发者
 * sub: jwt所面向的用户
 * aud: 接收jwt的一方
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 * iat: jwt的签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 * signature
 * signature由上面两部分组成，它需要base64加密后的header和base64加密后的payload连接组成的字符串，
 * 然后通过header中声明的加密方式进行加盐secret组合加密，然后就构成了jwt的第三部分。其中的secret为私钥，保存在服务端。
 *
 * Created by Administrator on 2018/5/28.
 */
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtUtils {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String secret;

    private long expire;

    private String header;


    /**
     * 生成jwt token
     *
     * @param userId
     * @return
     */
    public String generateToken(String userId) {
        Date nowDate = new Date();

        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        //添加构成JWT的参数
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                //签发用户
                .setSubject(userId)            //主题，也差不多是个人的一些信息
                //签发时间
                .setIssuedAt(nowDate)          //创建时间
                //设置失效时间
                .setExpiration(expireDate)     //添加Token过期时间
              //.setAudience(audience) //个人签名
              //.setIssuer(issuer) //发送谁
                //签名算法，私钥
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 验证JWT
     * Claims就是我们生成Token是的对象，我们把传递的头信息token通过JWT可以逆转成Claims对象，并且通过getSubject可以获取到我们用户的appId
     * @param token
     * @return
     */
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.debug("token验证错误,请重新登陆", e);
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @param expiration
     * @return true : 过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
