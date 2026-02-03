package com.habitos.gestor_habitos.service;

import com.habitos.gestor_habitos.config.exceptions.ForbiddenException;
import com.habitos.gestor_habitos.config.exceptions.ResouceNotFoundException;
import com.habitos.gestor_habitos.dto.HabitoDTO;
import com.habitos.gestor_habitos.model.Habito;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.repository.HabitoRepository;
import com.habitos.gestor_habitos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HabitoService {

    @Autowired
    private HabitoRepository habitoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RegistroService registroService;

    @Transactional
    public HabitoDTO.Response criarHabito(String email, HabitoDTO.Request habito) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));
        Habito novoHabito = new Habito();
        novoHabito.setTitulo(habito.title());
        novoHabito.setDescricao(habito.description());
        novoHabito.setDiasSemana(habito.daysOfWeek());
        novoHabito.setUsuario(usuario);

        Habito habitoSalvo = habitoRepository.save(novoHabito);
        registroService.gerarRegistrosIniciais(habitoSalvo);
        return new HabitoDTO.Response(habitoSalvo);
    }

    @Transactional
    public List<HabitoDTO.Response> listarHabitos(String email) {
        usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));
        List<Habito> habitos = habitoRepository.findByUsuarioEmail(email);
        return habitos.stream()
                .map(HabitoDTO.Response::new)
                .toList();
    }

    @Transactional
    public HabitoDTO.Response buscarHabitoPorId(String email, String id) {
        Habito habito = buscaHabitoPorIdEmail(email, id);
        return new HabitoDTO.Response(habito);
    }

    @Transactional
    public void deletarHabito(String email, String id) {
        Habito habito = buscaHabitoPorIdEmail(email, id);
        habitoRepository.delete(habito);
    }

    @Transactional
    public HabitoDTO.Response atualizarHabito(String email, String id, HabitoDTO.AtualizarHabito dto) {
        Habito habito = buscaHabitoPorIdEmail(email, id);

        if (dto.title() != null && !dto.title().isBlank()) {
            habito.setTitulo(dto.title());
        }
        if (dto.description() != null) {
            habito.setDescricao(dto.description());
        }
        if (dto.daysOfWeek() != null && !dto.daysOfWeek().isEmpty()) {
            habito.setDiasSemana(dto.daysOfWeek());
        }

        return new HabitoDTO.Response(habitoRepository.save(habito));

    }

    private Habito buscaHabitoPorIdEmail(String email, String id) {
        usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResouceNotFoundException("Usuário não encontrado com o email: " + email));
        Habito habito = habitoRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Hábito não encontrado com o ID: " + id));
        if (!habito.getUsuario().getEmail().equals(email)) {
            throw new ForbiddenException("Hábito não pertence ao usuário com o email: " + email);
        }
        return habito;
    }
}
