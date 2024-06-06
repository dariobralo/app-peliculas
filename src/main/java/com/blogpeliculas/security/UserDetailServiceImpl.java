package com.blogpeliculas.security;

import com.blogpeliculas.persistencia.entities.UsuarioEntity;
import com.blogpeliculas.persistencia.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findUsuarioEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario incorrecto"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        usuarioEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRol().name()))));

        logger.warn("Usuario traido de db para userDetail: [ " + usuarioEntity.getUsername() + " ] - [ "
                + usuarioEntity.getPassword()+" ] - ROLE: " + authorityList);

        return new User(usuarioEntity.getUsername(),
                usuarioEntity.getPassword(),
                usuarioEntity.isEnabled(),
                usuarioEntity.isAccountNoExpired(),
                usuarioEntity.isCredentialNoExpired(),
                usuarioEntity.isAccountNoLocked(),
                authorityList);
    }
}
