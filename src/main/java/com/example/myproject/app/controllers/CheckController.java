package com.example.myproject.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checks")
public class CheckController {

  @GetMapping()
  public ResponseEntity<?> test(){
    return ResponseEntity.ok("SUCCESS");
  }
}
