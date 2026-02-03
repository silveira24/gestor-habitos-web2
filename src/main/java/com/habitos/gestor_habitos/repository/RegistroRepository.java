package com.habitos.gestor_habitos.repository;

import com.habitos.gestor_habitos.model.Habito;
import com.habitos.gestor_habitos.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, String> {
    @Query("""
            SELECT r FROM Registro r
            JOIN r.habito h
            WHERE h.usuario.id = :usuarioId
            AND r.data BETWEEN :inicio AND :fim
            """)
    List<Registro> findAllByUsuarioAndPeriodo(String usuarioId, LocalDate inicio, LocalDate fim);
    boolean existsByHabitoAndData(Habito habito, LocalDate data);
}
