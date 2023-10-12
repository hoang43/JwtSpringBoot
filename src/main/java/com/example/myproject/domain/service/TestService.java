package com.example.myproject.domain.service;

import com.example.myproject.app.dto.TokenDto;
import com.example.myproject.domain.security.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {
  private String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALnAoUttGDE2QDuRFHnXqVNyV7Ujs5DPBDsIEv4lrAfGq/bCSvcdISULF54xlnQQTzeupAIiKD5EUHFN1C2f/bmEixjSZ4vvZbAiL62j1v2BAwIiDwmPaqZiMG/GtpWPtSg6sqfjxyv1ATcWf42dNJQiWE9aZrvlb8BQyTbSYGYZAgMBAAECgYAvu5F3OXSTCNbet7xkRwgLGoHxWVhGaPw0UzHWP0YCxYnhIjJzZ/fhdgU+sI1yC9LfgzBAIBjpT1LAmvhgNRHUzw5emLDn2BqohRa1cZd0gpWIb3FCKXuTuvXbXzbiYumlljmEYyCpTLyygFF9+INfd+s2w9gF7X2zcQTDGALZRQJBAOwPewFEQ8buajGp2LyojkNDGAYEqAck0UgRsbby5ezeLjV6H36NvCEy5C/HYmCkYEHZb1ywp/VMx5ZP/FrBVAsCQQDJcUze5vyQOGRzqIcGFigw70AGpGdMJEAMTwQH2yHQWd1mHEPMeANJQ5xxhRLuGxBktBk07KsLF/eT0HcXoMDrAkATp+JVI6sFGHsNMZhTdTGLVj3c2mxnU+B9QMPseOM+Su3MFCQlNIYSuZsYg2CkrWs6cHF+ZVNG8KK52GUp5WkrAkBXvIE+lFxsdCNMPJqSjBP1PD8mpXRYNXGv6rpWsZqIpJtJXgYpiBvmTQxWZR9ZlXAObGKOKoZUzpuGRcWZBOsBAkAozRNyDQOBlcrLs2pwZSWTYf5Vq1cc4GA5Tjt0J8EXGb6D0QTd9fBYcCmditnGyoppD+Fnf5lWM3sgNS5M55T7";
  public String createToken(TokenDto dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
    byte[] keyBytes = Base64.getDecoder().decode(PRIVATE_KEY);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory fact = KeyFactory.getInstance("RSA");
    PrivateKey privateKey = fact.generatePrivate(keySpec);

    Map<String, Object> claims = new HashMap<>();
    claims.put("phone", dto.getPhone());
    claims.put("fullname", dto.getFullname());
    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
            .setExpiration(Date.from(ZonedDateTime.now().plusSeconds(2).toInstant()))
            .signWith(SignatureAlgorithm.RS256, privateKey)
            .compact();
  }
}
