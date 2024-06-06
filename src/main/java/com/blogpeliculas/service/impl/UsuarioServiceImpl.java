package com.blogpeliculas.service.impl;

import com.blogpeliculas.dto.UsuarioDto;
import com.blogpeliculas.persistencia.entities.RolEntity;
import com.blogpeliculas.persistencia.entities.UsuarioEntity;
import com.blogpeliculas.persistencia.enums.ERol;
import com.blogpeliculas.persistencia.repository.RolRepository;
import com.blogpeliculas.persistencia.repository.UsuarioRepository;
import com.blogpeliculas.service.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolRepository rolRepository;

    @Override
    public Optional<UsuarioEntity> findUsuarioByUsername(String username) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findUsuarioEntityByUsername(username);
        return usuario;
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public void saveUsuario(UsuarioDto usuarioDto) {
        UsuarioEntity nuevoUsuario = new UsuarioEntity();
        nuevoUsuario.setUsername(usuarioDto.getUsername());
        nuevoUsuario.setPassword(usuarioDto.getPassword());
        nuevoUsuario.setEnabled(true);
        nuevoUsuario.setAccountNoLocked(true);
        nuevoUsuario.setAccountNoExpired(true);
        nuevoUsuario.setCredentialNoExpired(true);

        Set<RolEntity> roles = new HashSet<RolEntity>();
        roles.add(rolRepository.getRolEntityByRol(ERol.MIEMBRO).get());
        nuevoUsuario.setRoles(roles);

        usuarioRepository.save(nuevoUsuario);

    }
}
