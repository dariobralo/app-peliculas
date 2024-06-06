package com.blogpeliculas.service.interfaces;

import com.blogpeliculas.dto.UsuarioDto;
import com.blogpeliculas.persistencia.entities.UsuarioEntity;

import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioEntity> findUsuarioByUsername(String username);
    boolean existsByUsername(String username);
    void saveUsuario(UsuarioDto usuarioDto);

}
