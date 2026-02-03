package com.habitos.gestor_habitos.controller;

import com.habitos.gestor_habitos.dto.HabitoDTO;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.service.HabitoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/habits")
@Tag(name = "Habits", description = "Operações relacionadas a hábitos")
@SecurityRequirement(name = "bearer-key")
public class HabitoController {

    @Autowired
    private HabitoService service;

    @PostMapping
    @Operation(summary = "Criar um novo hábito para o usuário" )
    public ResponseEntity<HabitoDTO.Response> criarHabito(@AuthenticationPrincipal Usuario usuarioLogado, @Valid @RequestBody HabitoDTO.Request habito) {
        return ResponseEntity.status(201).body(service.criarHabito(usuarioLogado.getEmail(), habito));
    }

    @GetMapping
    @Operation(summary = "Listar todos os hábitos do usuário" )
    public ResponseEntity<List<HabitoDTO.Response>> listarHabitos(@AuthenticationPrincipal Usuario usuarioLogado) {
        return ResponseEntity.ok(service.listarHabitos(usuarioLogado.getEmail()));
    }

    @GetMapping("/{id}" )
    @Operation(summary = "Buscar hábito por ID" )
    public ResponseEntity<HabitoDTO.Response> buscarHabitoPorId(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String id) {
        return ResponseEntity.ok(service.buscarHabitoPorId(usuarioLogado.getEmail(), id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar hábito por ID" )
    public ResponseEntity<Void> deletarHabitoPorId(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String id) {
        service.deletarHabito(usuarioLogado.getEmail(), id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar hábito por ID" )
    public ResponseEntity<HabitoDTO.Response> atualizarHabitoPorId(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String id,
                                                              @Valid @RequestBody HabitoDTO.AtualizarHabito habitoAtualizado) {
        return ResponseEntity.ok(service.atualizarHabito(usuarioLogado.getEmail(), id, habitoAtualizado));
    }
}
