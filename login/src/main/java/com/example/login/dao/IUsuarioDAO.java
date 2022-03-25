package com.example.login.dao;

import com.example.login.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {
    public Usuario findByUsername(String username);


}
