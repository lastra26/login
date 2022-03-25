package com.example.login.service;

import com.example.login.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    public Usuario findByUsername(String username);
    public Usuario registrar(Usuario u);
    public Usuario finOne(Long id);

    public List<Usuario> findAll();

    public void delete(Long id);
}
