package com.blogpeliculas.persistencia.entities;

import com.blogpeliculas.persistencia.enums.ERol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String avatar;
    private boolean estado;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, name = "roles")
    private ERol rol;

}
