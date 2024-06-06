package com.blogpeliculas.persistencia.repository;

import com.blogpeliculas.persistencia.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findUsuarioEntityByUsername(String username);
    boolean existsByUsername(String username);

}
