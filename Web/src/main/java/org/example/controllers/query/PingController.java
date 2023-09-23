package org.example.controllers.query;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

  @GetMapping("/ping")
  public String ping(){
    try{
      return "pong";
    }catch (Exception e){
      return e.getMessage();
    }

  }
}
