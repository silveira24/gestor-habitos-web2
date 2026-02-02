package com.habitos.gestor_habitos.controller;

import com.habitos.gestor_habitos.dto.HabitoDTO;
import com.habitos.gestor_habitos.service.HabitoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag(name = "Hábitos", description = "Operações relacionadas a hábitos")
public class HabitoController {

    @Autowired
    private HabitoService service;

    @PostMapping("{email}/habitos")
    @Operation(summary = "Criar um novo hábito para o usuário" )
    public ResponseEntity<HabitoDTO.Response> criarHabito(@PathVariable String email, @Valid @RequestBody HabitoDTO.Request habito) {
        return ResponseEntity.status(201).body(service.criarHabito(email, habito));
    }

    @GetMapping("{email}/habitos")
    @Operation(summary = "Listar todos os hábitos do usuário" )
    public ResponseEntity<List<HabitoDTO.Response>> listarHabitos(@PathVariable String email) {
        return ResponseEntity.ok(service.listarHabitos(email));
    }

    @GetMapping("{email}/habitos/{id}" )
    @Operation(summary = "Buscar hábito de um usuário por ID" )
    public ResponseEntity<HabitoDTO.Response> buscarHabitoPorId(@PathVariable String email, @PathVariable String id) {
        return ResponseEntity.ok(service.buscarHabitoPorId(email, id));
    }

    @DeleteMapping("{email}/habitos/{id}")
    @Operation(summary = "Deletar hábito de um usuário por ID" )
    public ResponseEntity<Void> deletarHabitoPorId(@PathVariable String email, @PathVariable String id) {
        service.deletarHabito(email, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{email}/habitos/{id}")
    @Operation(summary = "Atualizar hábito de um usuário por ID" )
    public ResponseEntity<HabitoDTO.Response> atualizarHabitoPorId(@PathVariable String email, @PathVariable String id,
                                                              @Valid @RequestBody HabitoDTO.AtualizarHabito habitoAtualizado) {
        return ResponseEntity.ok(service.atualizarHabito(email, id, habitoAtualizado));
    }
}
