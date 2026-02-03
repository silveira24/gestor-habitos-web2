package com.habitos.gestor_habitos.controller;

import com.habitos.gestor_habitos.dto.PerfilDTO;
import com.habitos.gestor_habitos.dto.UsuarioDTO;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.service.UsuarioService;
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
@RequestMapping("/v1/users")
@Tag(name = "Users", description = "Operações relacionadas a usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Operation(summary = "Criar uma nova conta de usuário")
    public ResponseEntity<UsuarioDTO.Response> criarUsuario(@Valid @RequestBody UsuarioDTO.Request usuario) {
        return ResponseEntity.status(201).body(service.criarUsuario(usuario));
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Listar todos os usuários (ADMIN apenas)")
    public ResponseEntity<List<UsuarioDTO.Response>> listarUsuarios() {
        return ResponseEntity.ok(service.listarUsuarios());
    }

    @GetMapping("/me")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Buscar dados do usuário autenticado")
    public ResponseEntity<UsuarioDTO.Response> buscarUsuarioPorEmail(@AuthenticationPrincipal Usuario usuarioLogado) {
        return ResponseEntity.ok(new UsuarioDTO.Response(usuarioLogado));
    }

    @PatchMapping("/me/password")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Atualizar a password do usuário autenticado")
    public ResponseEntity<Void> atualizarSenha(@AuthenticationPrincipal Usuario usuarioLogado, @Valid @RequestBody UsuarioDTO.ChangePasswordRequest dto) {
        service.atualizarSenha(usuarioLogado.getEmail(), dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/profile")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Atualizar o perfil do usuário autenticado", description = "Permite atualizar informações do perfil, exceto a password e a role.")
    public ResponseEntity<UsuarioDTO.Response> atualizarPerfil(@AuthenticationPrincipal Usuario usuarioLogado, @Valid @RequestBody PerfilDTO.AtualizarPerfil dto) {
        UsuarioDTO.Response response = service.atualizarPerfil(usuarioLogado.getEmail(), dto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/me" )
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Deletar a conta do usuário autenticado")
    public ResponseEntity<Void> deletarUsuario(@AuthenticationPrincipal Usuario usuarioLogado) {
        service.deletarUsuario(usuarioLogado.getEmail());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{email}")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Deletar um usuário por email (ADMIN apenas)")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email) {
        service.deletarUsuario(email);
        return ResponseEntity.noContent().build();
    }

}
