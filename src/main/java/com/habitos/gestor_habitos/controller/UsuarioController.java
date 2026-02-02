package com.habitos.gestor_habitos.controller;

import com.habitos.gestor_habitos.dto.PerfilDTO;
import com.habitos.gestor_habitos.dto.UsuarioDTO;
import com.habitos.gestor_habitos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Operation(summary = "Criar uma nova conta de usuário")
    public ResponseEntity<UsuarioDTO.Response> criarUsuario(@Valid @RequestBody UsuarioDTO.Request usuario) {
        return ResponseEntity.status(201).body(service.criarUsuario(usuario));
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    public ResponseEntity<List<UsuarioDTO.Response>> listarUsuarios() {
        return ResponseEntity.ok(service.listarUsuarios());
    }

    @GetMapping("/{email}")
    @Operation(summary = "Buscar usuário por email")
    public ResponseEntity<UsuarioDTO.Response> buscarUsuarioPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.buscarUsuarioPorEmail(email));
    }

    @PatchMapping("/{email}/senha")
    @Operation(summary = "Atualizar a senha do usuário", description = "Requer a senha atual e a nova senha")
    public ResponseEntity<Void> atualizarSenha(@PathVariable String email, @Valid @RequestBody UsuarioDTO.AlterarSenha dto) {
        service.atualizarSenha(email, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{email}/role")
    @Operation(summary = "Atualizar a role do usuário", description = "Requer a nova role. Somente o SUPER_ADMIN pode realizar esta ação.")
    public ResponseEntity<Void> atualizarRole(@PathVariable String email, @Valid @RequestBody UsuarioDTO.AlterarRole dto) {
        service.atualizarRole(email, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{email}/perfil")
    @Operation(summary = "Atualizar o perfil do usuário", description = "Permite atualizar informações do perfil, exceto a senha e a role.")
    public ResponseEntity<Void> atualizarPerfil(@PathVariable String email, @Valid @RequestBody PerfilDTO.AtualizarPerfil dto) {
        service.atualizarPerfil(email, dto);
        return ResponseEntity.ok().build();
    }
}
