package com.dirge.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dirge.entity.User;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import tk.mybatis.mapper.genid.GenId;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private  static  final long  EXPIRE_TIME = 30*60*1000;

    private static final String SECRET = "Jason.Chen";

    public static String createToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        //设置过期时间
        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);
        Date expireDate = new Date(nowMillis+EXPIRE_TIME);
        Map<String,Object> map = new HashMap<>();
        String token = JWT.create()
                .withClaim("name",user.getUserName())//payload
                .withClaim("password",user.getPassWord())
                .withExpiresAt(expireDate)     //设置过期时间，过期时间要大于签发时间
                .withIssuedAt(date)  //签发时间
                .sign(algorithm);  //加密
        return token;
    }

    /**
     * 解密token
     * @param token
     * @return
     */
    public static boolean verify(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt=verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("登录凭证已过去，请重新登录！！！");
        }
        return true;
    }

}
