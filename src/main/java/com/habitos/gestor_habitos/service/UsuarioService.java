package com.habitos.gestor_habitos.service;

import com.habitos.gestor_habitos.config.exceptions.ForbiddenException;
import com.habitos.gestor_habitos.config.exceptions.ResouceNotFoundException;
import com.habitos.gestor_habitos.dto.UsuarioDTO;
import com.habitos.gestor_habitos.model.Perfil;
import com.habitos.gestor_habitos.model.RoleUsuario;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioDTO.usuarioResponse criarUsuario(UsuarioDTO.usuarioRequest usuarioRequest) {
        if (usuarioRepository.existsByEmail(usuarioRequest.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioRequest.email());
        usuario.setSenha(usuarioRequest.senha());
        usuario.setRole(RoleUsuario.USER);

        Perfil novoPerfil = new Perfil();
        novoPerfil.setNomeExibicao("Novo Usuário");

        usuario.setPerfil(novoPerfil);
        novoPerfil.setUsuario(usuario);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioDTO.usuarioResponse(usuarioSalvo);

    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO.usuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO.usuarioResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioDTO.usuarioResponse buscarUsuarioPorEmail(String email) {
        Usuario usuario= usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));

        return new UsuarioDTO.usuarioResponse(usuario);
    }

    public void atualizarSenha(String email, UsuarioDTO.usuarioAlterarSenha dto) {
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

    public void atualizarRole(String email, UsuarioDTO.usuarioAlterarRole dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));

        if (usuario.getRole() == RoleUsuario.SUPER_ADMIN) {
            throw new ForbiddenException("Não é permitido alterar a role de um SUPER_ADMIN");
        }

        usuario.setRole(dto.novaRole());
        usuarioRepository.save(usuario);
    }
}
