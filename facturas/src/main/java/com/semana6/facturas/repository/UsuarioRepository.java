package com.semana6.facturas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


import com.semana6.facturas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsernameAndPassword(String username, String password);
    
}
