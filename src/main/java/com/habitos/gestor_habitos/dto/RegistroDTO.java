package com.habitos.gestor_habitos.dto;

import com.habitos.gestor_habitos.model.Registro;
import com.habitos.gestor_habitos.model.enums.DiaSemana;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class RegistroDTO {

    @Schema(name = "registroRequest", description = "dados para a criação de um registro")
    public record request(
            @Schema(description = "A data do registro no formato AAAA-MM-DD", example = "2026-02-15")
            @NotNull(message = "A data do registro não pode ser nula")
            @FutureOrPresent(message = "A data do registro não pode ser no passado")
            LocalDate data,
            @Schema(description = "O ID do hábito associado ao registro", example = "a1b2c3d4-e5f6-7g8h-9i0j-k1l2m3n4o5p6")
            @NotNull(message = "O ID do hábito não pode ser nulo")
            String habitoId
    ) {
    }

    @Schema(name = "registroResponse", description = "Dados do registro criado ou retornado")
    public record response(
            @Schema(description = "ID do registro", example = "r1s2t3u4-v5w6-x7y8-z9a0-b1c2d3e4f5g6")
            String id,
            @Schema(description = "Título do hábito associado ao registro", example = "Exercícios Matinais")
            String tituloHabito,
            @Schema(description = "Data do registro no formato AAAA-MM-DD", example = "2026-02-15")
            String data,
            @Schema(description = "Dia da semana do registro", example = "SABADO")
            DiaSemana diaSemana,
            @Schema(description = "Indica se o hábito foi concluído neste dia", example = "true")
            Boolean concluido,
            @Schema(description = "Data e hora da conclusão do hábito no formato AAAA-MM-DDTHH:MM:SS", example = "2026-02-15T08:30:00")
            String horarioConclusao,
            @Schema(description = "O ID do hábito associado ao registro", example = "a1b2c3d4-e5f6-7g8h-9i0j-k1l2m3n4o5p6")
            String habitoId
    ) {
        public response(Registro registro) {
            this(
                    registro.getId(),
                    registro.getHabito().getTitulo(),
                    registro.getData().toString(),
                    DiaSemana.fromJavaDayOfWeek(DayOfWeek.from(registro.getData())),
                    registro.getConcluido(),
                    registro.getDataConclusao() != null ? registro.getDataConclusao().toString() : null,
                    registro.getHabito().getId()
            );
        }
    }
}
