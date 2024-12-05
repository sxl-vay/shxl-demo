package top.boking.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public class JwtExample {

    /**
     * RSA 非对称签名算法
     * 私钥加密
     *
     * 公钥解密
     * @param args
     * @throws NoSuchAlgorithmException
     * @author  史祥连
     * @date    2024/11/25-14:28
     * @version 1.0     */
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // 初始化 KeyPairGenerator
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // 指定密钥长度

        // 生成密钥对
        KeyPair keyPair = keyGen.generateKeyPair();



        // 提取私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 创建签名的算法对象
        Algorithm algorithm = Algorithm.RSA256(null, privateKey);

        // 创建 JWT
        String token = JWT.create()
                .withIssuer("auth0")                // 设置签发者
                .withSubject("user123")             // 设置主题
                .withClaim("role", "admin")         // 自定义数据
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
                .sign(algorithm);                   // 使用私钥签名

        System.out.println("Generated JWT: " + token);



        // 提取公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 创建验证算法对象 公钥解密
        Algorithm algorithm2 = Algorithm.RSA256(publicKey, null);


        // 创建验证器
        JWTVerifier verifier = JWT.require(algorithm2)
                .withIssuer("auth0")  // 验证签发者
                .build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("Token is valid");
            System.out.println("Subject: " + jwt.getSubject());
            System.out.println("Role: " + jwt.getClaim("role").asString());
        } catch (Exception e) {
            System.out.println("Invalid token: " + e.getMessage());
        }
    }
}
