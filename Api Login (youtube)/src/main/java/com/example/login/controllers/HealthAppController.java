package com.example.login.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*Este controlador es creado exclusivamente para ver si nuestra app esta funcionando*/
@RestController
public class HealthAppController {
    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
