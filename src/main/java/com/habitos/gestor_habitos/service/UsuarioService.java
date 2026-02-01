package com.habitos.gestor_habitos.service;

import com.habitos.gestor_habitos.config.exceptions.ForbiddenException;
import com.habitos.gestor_habitos.config.exceptions.ResouceNotFoundException;
import com.habitos.gestor_habitos.dto.AtualizarRoleDTO;
import com.habitos.gestor_habitos.dto.AtualizarSenhaUsuarioDTO;
import com.habitos.gestor_habitos.model.Perfil;
import com.habitos.gestor_habitos.model.RoleUsuario;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if (usuario.getPerfil() == null) {
            Perfil novoPerfil = new Perfil();
            novoPerfil.setNomeExibicao("Novo Usuário");
            usuario.setPerfil(novoPerfil);
        }

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail((email)).orElse(null);
    }

    public void atualizarSenha(String email, AtualizarSenhaUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));

        if (!usuario.getSenha().equals(dto.senhaAtual())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        if (usuario.getSenha().equals(dto.novaSenha())) {
            throw new IllegalArgumentException("A nova senha deve ser diferente da senha atual");
        }

        usuario.setSenha(dto.novaSenha());
        usuarioRepository.save(usuario);
    }

    public void atualizarRole(String email, AtualizarRoleDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));

        if (usuario.getRole() == RoleUsuario.SUPER_ADMIN) {
            throw new ForbiddenException("Não é permitido alterar a role de um SUPER_ADMIN");
        }

        usuario.setRole(dto.novaRole());
        usuarioRepository.save(usuario);
    }
}
