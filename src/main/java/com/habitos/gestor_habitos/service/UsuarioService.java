package com.habitos.gestor_habitos.service;

import com.habitos.gestor_habitos.config.exceptions.ForbiddenException;
import com.habitos.gestor_habitos.config.exceptions.ResouceNotFoundException;
import com.habitos.gestor_habitos.dto.PerfilDTO;
import com.habitos.gestor_habitos.dto.UsuarioDTO;
import com.habitos.gestor_habitos.model.Perfil;
import com.habitos.gestor_habitos.model.enums.RoleUsuario;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioDTO.Response criarUsuario(UsuarioDTO.Request Request) {
        if (usuarioRepository.existsByEmail(Request.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        Usuario usuario = new Usuario();
        usuario.setEmail(Request.email());
        usuario.setSenha(passwordEncoder.encode(Request.password()));
        usuario.setRole(RoleUsuario.USER);

        Perfil novoPerfil = new Perfil();
        novoPerfil.setNomeExibicao("Novo Usuário");

        usuario.setPerfil(novoPerfil);
        novoPerfil.setUsuario(usuario);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioDTO.Response(usuarioSalvo);

    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO.Response> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO.Response::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioDTO.Response buscarUsuarioPorEmail(String email) {
        Usuario usuario= usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));

        return new UsuarioDTO.Response(usuario);
    }

    public void atualizarSenha(String email, UsuarioDTO.ChangePasswordRequest dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));

        if (!passwordEncoder.matches(dto.currentPassword(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        if (passwordEncoder.matches(dto.newPassword(), usuario.getSenha())) {
            throw new IllegalArgumentException("A nova password deve ser diferente da password atual");
        }

        usuario.setSenha(passwordEncoder.encode(dto.newPassword()));
        usuarioRepository.save(usuario);
    }

    public UsuarioDTO.Response atualizarPerfil(String email, PerfilDTO.AtualizarPerfil dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));

        Perfil perfil = usuario.getPerfil();
        if (dto.displayName() != null) {
            perfil.setNomeExibicao(dto.displayName());
        }
        if (dto.bio() != null) {
            perfil.setBio(dto.bio());
        }

        return new UsuarioDTO.Response(usuarioRepository.save(usuario));
    }

    public void deletarUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));

        if (usuario.getRole().equals(RoleUsuario.ADMIN)) {
            throw new ForbiddenException("Não é permitido deletar um usuário ADMIN");
        }

        usuarioRepository.delete(usuario);
    }
}
