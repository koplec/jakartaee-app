package prv.koplec.jaxrs_todo.services;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class AuthService {
    public static SecretKey getSecretKey(){
        // シークレットキーを固定の値に設定
        String secretKeyString = "mySecretKeymySecretKeymySecretKeymySecretKeymySecretKey";

        // シークレットキーの Base64 エンコード
        byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        return secretKey;
    } 
}
