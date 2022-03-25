package com.example.login.controllers;

import com.example.login.entity.Usuario;
import com.example.login.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/private")
public class PrivateController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/index")
    public String index(Authentication auth, HttpSession session){
        String username = auth.getName();

        if (session.getAttribute("usuario") == null){
            Usuario usuario = usuarioService.findByUsername(username);
            usuario.setPassword(null);
            session.setAttribute("usuario", usuario);
        }

        return "/layouts/index";
    }




}
