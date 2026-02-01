package com.habitos.gestor_habitos.controller;

import com.habitos.gestor_habitos.dto.AtualizarRoleDTO;
import com.habitos.gestor_habitos.dto.AtualizarSenhaUsuarioDTO;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Operation(summary = "Criar uma nova conta de usuário")
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = service.criarUsuario(usuario);
            return ResponseEntity.ok(novoUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(service.listarUsuarios());
    }

    @GetMapping("/{email}")
    @Operation(summary = "Buscar usuário por email")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable String email) {
        Usuario usuario = service.buscarUsuarioPorEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{email}/senha")
    @Operation(summary = "Atualizar a senha do usuário", description = "Requer a senha atual e a nova senha")
    public ResponseEntity<Void> atualizarSenha(@PathVariable String email, @Valid @RequestBody AtualizarSenhaUsuarioDTO dto) {
        service.atualizarSenha(email, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{email}/role")
    @Operation(summary = "Atualizar a role do usuário", description = "Requer a nova role. Somente o SUPER_ADMIN pode realizar esta ação.")
    public ResponseEntity<Void> atualizarRole(@PathVariable String email, @Valid @RequestBody AtualizarRoleDTO dto) {
        service.atualizarRole(email, dto);
        return ResponseEntity.ok().build();
    }
}
