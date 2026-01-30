package com.habitos.gestor_habitos.repository;

import com.habitos.gestor_habitos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
