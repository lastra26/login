package com.example.login.impl;

import com.example.login.dao.IUsuarioDAO;
import com.example.login.entity.Usuario;
import com.example.login.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public Usuario findByUsername(String username){
        return usuarioDAO.findByUsername(username);
    }
    @Override
    public Usuario registrar (Usuario u){
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return usuarioDAO.save(u);
    }
    @Override
    @Transactional(readOnly = true)
    public Usuario finOne(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioDAO.deleteById(id);
    }

}















