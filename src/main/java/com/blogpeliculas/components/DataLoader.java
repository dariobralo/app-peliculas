package com.blogpeliculas.components;

import com.blogpeliculas.persistencia.entities.RolEntity;
import com.blogpeliculas.persistencia.entities.UsuarioEntity;
import com.blogpeliculas.persistencia.enums.ERol;
import com.blogpeliculas.persistencia.repository.RolRepository;
import com.blogpeliculas.persistencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;


    private PasswordEncoder passwordEncoder;
    public DataLoader(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        /* CREATE ROLES */
        RolEntity rolMiembro = RolEntity.builder()
                .rol(ERol.MIEMBRO)
                .build();

        RolEntity rolModerador = RolEntity.builder()
                .rol(ERol.MODERADOR)
                .build();

        RolEntity rolAdmin = RolEntity.builder()
                .rol(ERol.ADMIN)
                .build();

        RolEntity rolDeveloper = RolEntity.builder()
                .rol(ERol.DEVELOPER)
                .build();

        /* CREATE USUARIOS*/
        UsuarioEntity userDario = UsuarioEntity.builder()
                .username("dario")
                .password(passwordEncoder.encode("1234"))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .roles(Set.of(rolMiembro, rolModerador, rolAdmin, rolDeveloper))
                .build();

        UsuarioEntity userPocha = UsuarioEntity.builder()
                .username("pocha")
                .password(passwordEncoder.encode("1234"))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .roles(Set.of(rolMiembro, rolModerador, rolAdmin))
                .build();

        UsuarioEntity userYanina = UsuarioEntity.builder()
                .username("yanina")
                .password(passwordEncoder.encode("1234"))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .roles(Set.of(rolMiembro, rolModerador))
                .build();

        UsuarioEntity userManteco = UsuarioEntity.builder()
                .username("manteco")
                .password(passwordEncoder.encode("1234"))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .roles(Set.of(rolMiembro))
                .build();

        usuarioRepository.saveAll(List.of(userDario, userManteco, userPocha, userYanina));

    }

}
