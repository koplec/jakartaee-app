package prv.koplec.jaxrs_todo.services;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AuthService {
    private static SecretKey getSecretKey(){
        // シークレットキーを固定の値に設定
        String secretKeyString = "mySecretKeymySecretKeymySecretKeymySecretKeymySecretKey";

        // シークレットキーの Base64 エンコード
        byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
        return secretKey;
    } 

    public String issueToken(String subject){
        SecretKey secretKey = getSecretKey();
        // トークンの発行
         // トークンの有効期限（ここでは1時間）
        long expirationMillis = System.currentTimeMillis() + 3600000;

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(expirationMillis))
                .signWith(secretKey)
                .compact();
    }

    public String getSubject(String token){
        // シークレットキー（安全なキーを生成）
        SecretKey secretKey = AuthService.getSecretKey();
        // トークンの検証
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        Claims body = claimsJws.getBody();

        // トークンからユーザ名を取得
        return body.getSubject();
    }
}
