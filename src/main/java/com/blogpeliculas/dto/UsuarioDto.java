package com.blogpeliculas.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {

    private String username;
    private String password;
    private String passwordConfirm;

}
