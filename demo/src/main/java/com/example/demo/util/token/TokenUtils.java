/*
package com.example.demo.util.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author luosen
 * @date 8/12/2019 4:02 PM
 *//*

@Component
public class TokenUtils {
    //通过HMAC256算法将字符串编码为一个字节序列，结果存储到新的字节数组中
    Algorithm algorithm = Algorithm.HMAC256("HoneywellTest");

    */
/**
     * @params: userEntity
     * @return:
     * @description:
     *//*

    */
/*public String getToken(UserInfoEntity userInfoEntity) {
        return createToken(userInfoEntity);
    }

    *//*
*/
/**
     * 根据user.password或者自定义secret（这里）来创建token
     *
     * @param userInfoEntity
     * @return token
     *//*
*/
/*
    public String createToken(UserInfoEntity userInfoEntity) {
        String token = "";
        try {
            token = JWT.create().withIssuedAt(new Date()) //签署token时间
                    .withExpiresAt(new Date(System.currentTimeMillis() + (30 * 60 * 1000))) // 过期时间（毫秒）
                    .withClaim("USER_ID",userInfoEntity.getUSER_ID().toString())// 将 user id 声明到 token 里面
                    .withClaim("COMPANY_ID",userInfoEntity.getCOMPANY_ID().toString())//将company id声明到token
                    .sign(algorithm);//用algorithm来签署token令牌，验证时需要一致
        } catch (JWTCreationException exception) {
            // Invalid Signing configuration / Couldn't convert Claims.
            exception.printStackTrace();
        }
        return token;
    }

    *//*
*/
/**
     * 验证token是否正确(时效或者是否与生成的的token一致)
     *
     * @param token
     * @param userInfoEntity
     * @return 注意:如果验证token已失效或者不正确，则会通过JWTVerificationException异常来抛出
     *//*
*/
/*
    public Map<String,Object> verifyToken(String token, UserInfoEntity userInfoEntity) {
        Map<String,Object> map = new HashMap<>();
        try {
            JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
            verifier.verify(token);// 对token进行有效期验证，错误则抛出异常
            token = createToken(userInfoEntity);//重新创建token
            map.put("code", Response.CODE_ALL_CORRECT);
            map.put("token", token);
            map.put("userName", userInfoEntity.getNAME());
            map.put("EID",userInfoEntity.getEID());
        } catch (JWTVerificationException exception) {
//			exception.printStackTrace();
            map.put("code", Response.CODE_TOKEN_OVERVUE);
            map.put("token", "");
            map.put("message", Response.MSG_TOKEN_OVERVUE);

        }
        return map;
    }*//*

}
*/
