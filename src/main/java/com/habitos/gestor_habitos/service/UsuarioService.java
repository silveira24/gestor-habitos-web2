package com.habitos.gestor_habitos.service;

import com.habitos.gestor_habitos.model.Perfil;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
