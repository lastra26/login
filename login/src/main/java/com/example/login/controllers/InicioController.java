package com.example.login.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String Inicio(){
        return "/layouts/inicio.html";
    }

    @GetMapping("/registro")
    public String registro(){
        return "/layouts/registro.html";
    }
}
