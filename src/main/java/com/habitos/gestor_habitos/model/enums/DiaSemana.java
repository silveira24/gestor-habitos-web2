package com.habitos.gestor_habitos.model.enums;

import lombok.Getter;

@Getter
public enum DiaSemana {
    DOMINGO(1, "Domingo"),
    SEGUNDA(2, "Segunda-feira"),
    TERCA(3, "Terça-feira"),
    QUARTA(4, "Quarta-feira"),
    QUINTA(5, "Quinta-feira"),
    SEXTA(6, "Sexta-feira"),
    SABADO(7, "Sábado");

    private final int codigo;
    private final String descricao;

    DiaSemana(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static DiaSemana toEnum(int codigo) {

        for (DiaSemana dia : DiaSemana.values()) {
            if (dia.getCodigo() == codigo) {
                return dia;
            }
        }
        throw new IllegalArgumentException("Código inválido para Dia da Semana: " + codigo);
    }
}
