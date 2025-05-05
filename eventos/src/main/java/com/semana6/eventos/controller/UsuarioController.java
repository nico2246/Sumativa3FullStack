package com.semana6.eventos.controller;



import com.semana6.eventos.dto.LoginRequest;
import com.semana6.eventos.model.Usuario;
import com.semana6.eventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> user = usuarioRepository.findByUsernameAndPassword(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        if (user.isPresent()) {
            return "Login exitoso.";
        } else {
            return "Credenciales incorrectas.";
        }
    }
}