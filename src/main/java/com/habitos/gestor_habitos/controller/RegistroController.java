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
@Tag(name = "Records", description = "Operações relacionadas a registros de hábitos")
@SecurityRequirement(name = "bearer-key")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping("/habits/records/today")
    @Operation(summary = "Buscar registros do dia atual do usuário logado")
    public List<RegistroDTO.response> obterRegistrosDeHoje(@AuthenticationPrincipal Usuario usuarioLogado) {
        return registroService.buscarRegistrosPorUsuarioEPeriodo(usuarioLogado.getId(), java.time.LocalDate.now(), java.time.LocalDate.now());
    }

    @GetMapping("/habits/{habitId}/records")
    @Operation(summary = "Buscar registros de um hábito")
    public List<RegistroDTO.response> obterRegistrosPorHabito(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String habitId) {
        return registroService.buscarRegistrosPorHabito(habitId, usuarioLogado);
    }

    @PostMapping("/habits/{habitId}/records/extra")
    @Operation(summary = "Criar um registro extra")
    public RegistroDTO.response criarRegistroExtra(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String habitId,@RequestParam @Valid LocalDate date) {
        return registroService.salvarRegistroExtra(usuarioLogado, habitId, date);
    }

    @PatchMapping("habits/{habitId}/records/{recordId}/complete")
    @Operation(summary = "Marcar registro como isCompleted")
    public void concluirRegistro(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String habitId, @PathVariable String recordId) {
        registroService.marcarRegistroConcluido(usuarioLogado, habitId, recordId);
    }

    @DeleteMapping("habits/{habitId}/records/{recordId}")
    @Operation(summary = "Deletar um registro")
    public void deletarRegistro(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable String habitId, @PathVariable String recordId) {
        registroService.deletarRegistro(usuarioLogado, habitId, recordId);
    }

}
