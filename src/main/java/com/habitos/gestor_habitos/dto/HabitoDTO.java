package com.habitos.gestor_habitos.dto;

import com.habitos.gestor_habitos.model.Habito;
import com.habitos.gestor_habitos.model.enums.DiaSemana;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Set;

public class HabitoDTO {
    @Schema(name = "HabitoRequest", description = "Dados para criação de um hábito")
    public record Request(
            @Schema(description = "Título do hábito", example = "Exercícios Matinais")
            @NotBlank(message="O título do hábito não pode estar em branco")
            @Size(min=3, max=50, message="O título deve ter entre 3 e 50 caracteres")
            String titulo,
            @Schema(description = "Descrição do hábito", example = "Fazer uma série de exercícios todas as manhãs")
            @Size(max=200, message="A descrição deve ter no máximo 200 caracteres")
            String descricao,
            @Schema(description = "Dias da semana em que o hábito deve ser realizado", example = """
                       ["SEGUNDA", "QUARTA", "SEXTA"]
                    """)
            @NotEmpty(message="Selecione pelo menos um dia da semana")
            Set<DiaSemana> diasSemana
    ) {
    }

    @Schema(name = "HabitoResponse", description = "Dados do hábito criado ou recuperado")
    public record Response(
            @Schema(description = "ID do hábito", example = "a1b2c3d4-e5f6-7g8h-9i0j-k1l2m3n4o5p6")
            String id,
            @Schema(description = "Título do hábito", example = "Exercícios Matinais")
            String titulo,
            @Schema(description = "Descrição do hábito", example = "Fazer uma série de exercícios todas as manhãs")
            String descricao,
            @Schema(description = "Dias da semana em que o hábito deve ser realizado", example = """
                       ["SEGUNDA", "QUARTA", "SEXTA"]
                    """)
            Set<DiaSemana> diasSemana,
            Integer frequencia
    ) {
        public Response (Habito habito) {
            this(
                    habito.getId(),
                    habito.getTitulo(),
                    habito.getDescricao(),
                    habito.getDiasSemana(),
                    habito.getFrequencia()
            );
        }
    }

    @Schema(name = "AtualizarHabitoRequest", description = "Dados para atualização de um hábito. Todos os campos são opcionais.")
    public record AtualizarHabito(
            @Schema(description = "Título do hábito", example = "Exercícios Matinais")
            @Size(min=3, max=50, message="O título deve ter entre 3 e 50 caracteres")
            String titulo,
            @Schema(description = "Descrição do hábito", example = "Fazer uma série de exercícios todas as manhãs")
            @Size(max=200, message="A descrição deve ter no máximo 200 caracteres")
            String descricao,
            @Schema(description = "Dias da semana em que o hábito deve ser realizado", example = """
                       ["SEGUNDA", "QUARTA", "SEXTA"]
                    """)
            Set<DiaSemana> diasSemana
    ) {
    }
}
