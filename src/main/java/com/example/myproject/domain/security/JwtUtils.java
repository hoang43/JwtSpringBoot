package com.example.myproject.domain.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtUtils {
  private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5wKFLbRgxNkA7kRR516lTcle1I7OQzwQ7CBL+JawHxqv2wkr3HSElCxeeMZZ0EE83rqQCIig+RFBxTdQtn/25hIsY0meL72WwIi+to9b9gQMCIg8Jj2qmYjBvxraVj7UoOrKn48cr9QE3Fn+NnTSUIlhPWma75W/AUMk20mBmGQIDAQAB";

  public boolean validateJwtToken(String token) throws AuthenticationException {
    try {
      byte[] data = Base64.getDecoder().decode((publicKey));
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(data);
      KeyFactory kf = KeyFactory.getInstance("RSA");
      RSAPublicKey publicKey = (RSAPublicKey) kf.generatePublic(keySpec);

      Algorithm algorithm = Algorithm.RSA256(publicKey, null);
      JWTVerifier verifier = JWT.require(algorithm).build();

      DecodedJWT jwt = JWT.decode(token);
      verifier.verify(jwt);

      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Claims getInfo(String token) {
    try {
      byte[] data = Base64.getDecoder().decode((publicKey));
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(data);
      KeyFactory kf = KeyFactory.getInstance("RSA");
      RSAPublicKey publicKey = (RSAPublicKey) kf.generatePublic(keySpec);
      return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
    } catch (Exception e) {
      return null;
    }
  }

}
