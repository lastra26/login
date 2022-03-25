package com.example.login.security;

import com.example.login.dao.IUsuarioDAO;
import com.example.login.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioDAO.findByUsername(username);
        User.UserBuilder builder = null;
        if(usuario != null){
        builder = User.withUsername(username);
        builder.disabled(false);
        builder.password(usuario.getPassword());
        builder.authorities(new SimpleGrantedAuthority("ROLE USER"));
        }else{
            throw new UsernameNotFoundException("usuario no encontrado");
        }

        return builder.build();
    }
}
