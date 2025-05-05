package com.semana6.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


import com.semana6.eventos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsernameAndPassword(String username, String password);
    
}
