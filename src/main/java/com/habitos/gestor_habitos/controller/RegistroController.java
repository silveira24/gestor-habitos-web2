package com.habitos.gestor_habitos.controller;

import com.habitos.gestor_habitos.dto.RegistroDTO;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.service.RegistroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag(name = "Registros", description = "Operações relacionadas a registros de hábitos")
@SecurityRequirement(name = "bearer-key")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping("/habitos/registros/hoje")
    @Operation(summary = "Buscar registros do dia atual do usuário logado")
    public List<RegistroDTO.response> obterRegistrosDeHoje(@AuthenticationPrincipal Usuario usuarioLogado) {
        return registroService.buscarRegistrosPorUsuarioEPeriodo(usuarioLogado.getId(), java.time.LocalDate.now(), java.time.LocalDate.now());
    }

    @GetMapping("/habitos/{habitoId}/registros")
    @Operation(summary = "Buscar registros de um hábito")
    public List<RegistroDTO.response> obterRegistrosPorHabito(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String habitoId) {
        return registroService.buscarRegistrosPorHabito(habitoId, usuarioLogado);
    }

    @PostMapping("/habitos/{habitoId}/registros/novo")
    @Operation(summary = "Criar um registro extra")
    public RegistroDTO.response criarRegistroExtra(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String habitoId, @Valid LocalDate data) {
        return registroService.salvarRegistroExtra(usuarioLogado, habitoId, data);
    }

    @PatchMapping("habitos/{habitoId}/registros/{registroId}/concluir")
    @Operation(summary = "Marcar registro como concluido")
    public void concluirRegistro(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String habitoId, @PathVariable String registroId) {
        registroService.marcarRegistroConcluido(usuarioLogado, habitoId, registroId);
    }

    @DeleteMapping("habitos/{habitoId}/registros/{registroId}")
    @Operation(summary = "Deletar um registro")
    public void deletarRegistro(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String habitoId, @PathVariable String registroId) {
        registroService.deletarRegistro(usuarioLogado, habitoId, registroId);
    }

}
