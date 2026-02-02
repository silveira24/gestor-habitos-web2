package com.habitos.gestor_habitos.repository;

import com.habitos.gestor_habitos.model.Habito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HabitoRepository extends JpaRepository<Habito, String> {
    List<Habito> findAllByUsuarioEmail(String email);

    List<Habito> findByUsuarioEmail(String usuarioEmail);
}
