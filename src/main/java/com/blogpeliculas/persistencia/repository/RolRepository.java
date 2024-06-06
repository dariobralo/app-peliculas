package com.blogpeliculas.persistencia.repository;

import com.blogpeliculas.persistencia.entities.RolEntity;
import com.blogpeliculas.persistencia.enums.ERol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {

    Optional<RolEntity> getRolEntityByRol(ERol rol);

}
