package com.blogpeliculas.persistencia.repository;

import com.blogpeliculas.persistencia.entities.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {
}
