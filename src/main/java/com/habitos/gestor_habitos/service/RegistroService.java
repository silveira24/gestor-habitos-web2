package com.habitos.gestor_habitos.service;

import com.habitos.gestor_habitos.config.exceptions.ForbiddenException;
import com.habitos.gestor_habitos.config.exceptions.ResouceNotFoundException;
import com.habitos.gestor_habitos.dto.RegistroDTO;
import com.habitos.gestor_habitos.model.Habito;
import com.habitos.gestor_habitos.model.Registro;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.model.enums.DiaSemana;
import com.habitos.gestor_habitos.repository.HabitoRepository;
import com.habitos.gestor_habitos.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private HabitoRepository habitoRepository;

    @Transactional
    public void gerarRegistrosIniciais(Habito habito) {
        LocalDate hoje = LocalDate.now();
        List<Registro> registros = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            LocalDate dataRegistro = hoje.plusDays(i);
            DayOfWeek diaSemana = dataRegistro.getDayOfWeek();

            if (habito.getDiasSemana().contains(DiaSemana.fromJavaDayOfWeek(diaSemana))) {

                boolean existeRegistro = registroRepository.existsByHabitoAndData(habito, dataRegistro);
                if (!existeRegistro) {
                    Registro registro = new Registro();
                    registro.setHabito(habito);
                    registro.setData(dataRegistro);
                    registro.setConcluido(false);
                    registros.add(registro);
                }
            }
        }

        if (!registros.isEmpty()) {
            registroRepository.saveAll(registros);
        }
    }

    @Transactional
    public void marcarRegistroConcluido(Usuario usuarioLogado, String habito_id, String registro_id) {
        Registro registro = verificacaoUsuarioHabito(usuarioLogado, habito_id, registro_id);
        registro.setConcluido(true);
        registro.setDataConclusao(java.time.LocalDateTime.now());
        registroRepository.save(registro);
    }

    public List<RegistroDTO.response> buscarRegistrosPorHabito(String habito_id, Usuario usuarioLogado) {
        Habito habito = habitoRepository.findById(habito_id)
                .orElseThrow(() -> new ResouceNotFoundException("Hábito não encontrado com o ID: " + habito_id));
        if (!usuarioLogado.getEmail().equals(habito.getUsuario().getEmail())) {
            throw new ResouceNotFoundException("Hábito não encontrado com o ID: " + habito_id);
        }
        return registroRepository.findAll().stream()
                .filter(registro -> registro.getHabito().equals(habito))
                .map(RegistroDTO.response::new)
                .toList();
    }

    public List<RegistroDTO.response> buscarRegistrosPorUsuarioEPeriodo(String usuarioId, LocalDate inicio, LocalDate fim) {
        return registroRepository.findAllByUsuarioAndPeriodo(usuarioId, inicio, fim)
                .stream()
                .map(RegistroDTO.response::new)
                .toList();
    }

    public RegistroDTO.response salvarRegistroExtra(Usuario usuarioLogado, String habito_id, LocalDate data) {
        Habito habito = habitoRepository.findById(habito_id)
                .orElseThrow(() -> new ResouceNotFoundException("Hábito não encontrado com o ID: " + habito_id));
        if (!usuarioLogado.getEmail().equals(habito.getUsuario().getEmail())) {
            throw new ForbiddenException("Hábito não pertence ao usuário logado.");
        }
        Registro novoRegistro = new Registro();
        novoRegistro.setHabito(habito);
        novoRegistro.setData(data);
        return new RegistroDTO.response(registroRepository.save(novoRegistro));
    }

    public void deletarRegistro(Usuario usuarioLogado, String habitoId, String registroId) {
        Registro registro = verificacaoUsuarioHabito(usuarioLogado, habitoId, registroId);
        registroRepository.delete(registro);
    }

    private Registro verificacaoUsuarioHabito(Usuario usuarioLogado, String habitoId, String registroId) {
        Habito habito = habitoRepository.findById(habitoId)
                .orElseThrow(() -> new ResouceNotFoundException("Hábito não encontrado com o ID: " + habitoId));
        if (!usuarioLogado.getEmail().equals(habito.getUsuario().getEmail())) {
            throw new ForbiddenException("Hábito não pertence ao usuário logado.");
        }
        Registro registro = registroRepository.findById(registroId)
                .orElseThrow(() -> new ResouceNotFoundException("Registro não encontrado com o ID: " + registroId));
        if (!registro.getHabito().getId().equals(habitoId)) {
            throw new ForbiddenException("Registro não pertence ao hábito especificado.");
        }
        return registro;
    }

}
