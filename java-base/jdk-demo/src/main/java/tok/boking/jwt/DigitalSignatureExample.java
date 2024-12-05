package tok.boking.jwt;

import java.security.*;
import java.util.Base64;

public class DigitalSignatureExample {

    // 生成签名
    public static String signText(String text, PrivateKey privateKey) throws Exception {
        // 创建一个 Signature 对象
        Signature signature = Signature.getInstance("SHA256withRSA");
        // 初始化 Signature 对象
        signature.initSign(privateKey);
        // 更新数据（将文本数据传入 Signature 对象）
        signature.update(text.getBytes());
        // 签名并返回 Base64 编码的签名
        byte[] signedData = signature.sign();
        return Base64.getEncoder().encodeToString(signedData);
    }

    // 使用公钥验证签名
    public static boolean verifySignature(String text, String signatureStr, PublicKey publicKey) throws Exception {
        // 创建 Signature 对象
        Signature signature = Signature.getInstance("SHA256withRSA");
        // 初始化 Signature 对象
        signature.initVerify(publicKey);
        // 更新数据（将文本数据传入 Signature 对象）
        signature.update(text.getBytes());
        // 解码 Base64 编码的签名
        byte[] signedData = Base64.getDecoder().decode(signatureStr);
        // 验证签名
        return signature.verify(signedData);
    }

    public static void main(String[] args) throws Exception {
        // 生成密钥对
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 原始文本
        String originalText = "This is the message to be signed";

        // 生成签名
        String signature = signText(originalText, privateKey);
        System.out.println("Signature: " + signature);

        // 验证签名
        boolean isVerified = verifySignature(originalText, signature, publicKey);
        System.out.println("Signature verified: " + isVerified);
    }
}
