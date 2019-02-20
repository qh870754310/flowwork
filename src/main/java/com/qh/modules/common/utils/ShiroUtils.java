package com.qh.modules.common.utils;

import com.qh.modules.sys.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * 类ShiroUtils的功能描述:
 * Shiro工具类
 * Shiro提供了base64和16进制字符串编码/解码的API支持，方便一些编码解码操作。Shiro内部的一些数据的存储/表示都使用了base64和16进制字符串。
 * String str = "hello";
 * String base64Encoded = Base64.encodeToString(str.getBytes());
 * String str2 = Base64.decodeToString(base64Encoded);
 * Assert.assertEquals(str, str2);通过如上方式可以进行base64编码/解码操作
 * 散列算法一般用于生成数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的数据，常见的散列算法如MD5、SHA等;
 * 一般进行散列时最好提供一个salt（盐），比如加密密码“admin”，产生的散列值是“21232f297a57a5a743894a0e4a801fc3”，
 * 可以到一些md5解密网站很容易的通过散列值得到密码“admin”，即如果直接对密码进行散列相对来说破解更容易，
 * 此时我们可以加一些只有系统知道的干扰数据，如用户名和ID（即盐）；这样散列的对象是“密码+用户名+ID”，
 * 这样生成的散列值相对来说更难破解。
 * String str = "hello";String salt = "123";String md5 = new Md5Hash(str, salt).toString();//还可以转换为 toBase64()/toHex()
 * 如上代码通过盐“123”MD5散列“hello”。另外散列时还可以指定散列次数，如2次表示：md5(md5(str))：“new Md5Hash(str, salt, 2).toString()”。
 * String str = "hello";String salt = "123";String sha1 = new Sha256Hash(str, salt).toString();   使用SHA256算法生成相应的散列数据，另外还有如SHA1、SHA512算法。
 * Shiro还提供了通用的散列支持：String str = "hello";String salt = "123";//内部使用MessageDigest
 * String simpleHash = new SimpleHash("SHA-1", str, salt).toString();   通过调用SimpleHash时指定散列算法，其内部使用了Java的MessageDigest实现。
 * 为了方便使用，Shiro提供了HashService，默认提供了DefaultHashService实现。
 *
 * Created by Administrator on 2018/4/19.
 */
public class ShiroUtils {

    /**
     * 加密算法
     */
    public final static String algorithmName = "SHA-256";

    /**
     * 加密散列次数
     */
    public static final int hashIterations = 1;


    public static UserEntity getUserEntity() {
        return (UserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static Subject getSubject() {
        //通过SecurityUtils得到Subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定；然后获取身份验证的Token，如用户名/密码；
        return SecurityUtils.getSubject();
    }

    public static String getUserId() {
        return getUserEntity().getId();
    }

    public static String EncodeSalt(String password, String salt) {
        return new SimpleHash(algorithmName, password, salt, hashIterations).toString();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }

    /**
     * 1、首先创建一个DefaultHashService，默认使用SHA-512算法；
     * 2、可以通过hashAlgorithmName属性修改算法；
     * 3、可以通过privateSalt设置一个私盐，其在散列时自动与用户传入的公盐混合产生一个新盐；
     * 4、可以通过generatePublicSalt属性在用户没有传入公盐的情况下是否生成公盐；
     * 5、可以设置randomNumberGenerator用于生成公盐；
     * 6、可以设置hashIterations属性来修改默认加密迭代次数；
     * 7、需要构建一个HashRequest，传入算法、数据、公盐、迭代次数。
     * @param password
     * @param salt
     * @return
     */
    /*public static String EncodeSaltTest(String password, String salt) {
        DefaultHashService hashService = new DefaultHashService(); //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource(salt)); //私盐，默认无
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个
        hashService.setHashIterations(1); //生成Hash值的迭代次数

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes(password))
                .setSalt(ByteSource.Util.bytes(salt)).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        return hex;
    }*/
}
