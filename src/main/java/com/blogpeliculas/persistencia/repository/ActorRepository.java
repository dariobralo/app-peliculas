package com.blogpeliculas.persistencia.repository;

import com.blogpeliculas.persistencia.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}
