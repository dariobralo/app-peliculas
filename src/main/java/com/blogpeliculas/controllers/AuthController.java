package com.blogpeliculas.controllers;

import com.blogpeliculas.dto.UsuarioDto;
import com.blogpeliculas.service.impl.UsuarioServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    /*
     *
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("usuario", new UsuarioDto());
        return "register";
    }

    @PostMapping("/registro")
    public String createUsuario(@ModelAttribute UsuarioDto usuario,
                                Model model,
                                RedirectAttributes redirect){

        logger.warn("info: " + usuario.getUsername());

        if(usuario.getUsername().isBlank()|| usuario.getPassword().isBlank()){
            model.addAttribute("errorForm", "Debe completar todos los campos.");
            model.addAttribute("usuario", usuario);
            logger.info("ENTRO en IF 1");
            return "register";
        }
        if(!usuario.getPassword().equals(usuario.getPassword().trim())){
            model.addAttribute("errorForm", "La contraseña no debe llevar espacios vacios.");
            model.addAttribute("usuario", usuario);
            logger.info("ENTRO en IF 2");
            return "register";
        }
        if(usuarioService.existsByUsername(usuario.getUsername())){
            model.addAttribute("usernameError", "Nombre de usuario no disponible, elija otro.");
            model.addAttribute("usuario", usuario);
            logger.info("ENTRO en IF 3");
            return "register";
        }
        if(!usuario.getPassword().equals(usuario.getPasswordConfirm())){
            model.addAttribute("errorForm", "Las contraseñas no coiciden");
            model.addAttribute("usuario", usuario);
            logger.info("ENTRO en IF 4");
            return "register";
        }
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsername(usuario.getUsername());
        usuarioDto.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioService.saveUsuario(usuarioDto);
        redirect.addFlashAttribute("usuarioOk", "Usuario creado! Por favor inicie sesión.");
        return "redirect:/auth/login";
    }

}
