package com.habitos.gestor_habitos.dto;

import com.habitos.gestor_habitos.model.Registro;
import com.habitos.gestor_habitos.model.enums.DiaSemana;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class RegistroDTO {

    @Schema(name = "RecordRequest", description = "dados para a criação de um registro")
    public record request(
            @Schema(description = "A date do registro no formato AAAA-MM-DD", example = "2026-02-15")
            @NotNull(message = "A date do registro não pode ser nula")
            @FutureOrPresent(message = "A date do registro não pode ser no passado")
            LocalDate date,
            @Schema(description = "O ID do hábito associado ao registro", example = "a1b2c3d4-e5f6-7g8h-9i0j-k1l2m3n4o5p6")
            @NotNull(message = "O ID do hábito não pode ser nulo")
            String habitId
    ) {
    }

    @Schema(name = "RecordResponse", description = "Dados do registro criado ou retornado")
    public record response(
            @Schema(description = "ID do registro", example = "r1s2t3u4-v5w6-x7y8-z9a0-b1c2d3e4f5g6")
            String id,
            @Schema(description = "Título do hábito associado ao registro", example = "Exercícios Matinais")
            String habitTitle,
            @Schema(description = "Data do registro no formato AAAA-MM-DD", example = "2026-02-15")
            String date,
            @Schema(description = "Dia da semana do registro", example = "SABADO")
            DiaSemana dayOfWeek,
            @Schema(description = "Indica se o hábito foi concluído neste dia", example = "true")
            Boolean isCompleted,
            @Schema(description = "Data e hora da conclusão do hábito no formato AAAA-MM-DDTHH:MM:SS", example = "2026-02-15T08:30:00")
            String completedAt,
            @Schema(description = "O ID do hábito associado ao registro", example = "a1b2c3d4-e5f6-7g8h-9i0j-k1l2m3n4o5p6")
            String habitId
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
