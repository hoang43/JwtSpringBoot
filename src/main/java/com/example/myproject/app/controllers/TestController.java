package com.example.myproject.app.controllers;

import com.example.myproject.app.dto.TokenDto;
import com.example.myproject.domain.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
@RestController
@RequestMapping("/api/tests")
public class TestController {

  @Autowired
  private TestService testService;

  @PostMapping("/token")
  public String genToken(@RequestBody TokenDto dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
    return testService.createToken(dto);
  }

  @GetMapping()
  public ResponseEntity<?> test(){
    return ResponseEntity.ok("SUCCESS");
  }

//  public static void main(String[] args) throws NoSuchAlgorithmException {
//
//    // Tạo đối tượng KeyPairGenerator với thuật toán RSA
//    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//
//    // Thiết lập độ dài khóa (key length), ví dụ: 2048 bit
//    keyPairGenerator.initialize(2048);
//
//    // Sinh cặp khóa bí mật và khóa công khai
//    KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//    // Lấy khóa bí mật
//    byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
//
//    // Lấy khóa công khai
//    byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
//
//    // Chuyển đổi sang dạng chuỗi (Base64 hoặc hex)
//    String privateKey = Base64.getEncoder().encodeToString(privateKeyBytes);
//    String publicKey = Base64.getEncoder().encodeToString(publicKeyBytes);
//
//    // In ra khóa bí mật và khóa công khai
//    System.out.println("Private Key: " + privateKey);
//    System.out.println("Public Key: " + publicKey);
//  }

}
