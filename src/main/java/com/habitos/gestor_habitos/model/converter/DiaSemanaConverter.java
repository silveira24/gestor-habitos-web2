package com.habitos.gestor_habitos.model.converter;

import com.habitos.gestor_habitos.model.enums.DiaSemana;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DiaSemanaConverter implements AttributeConverter<DiaSemana, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DiaSemana dia) {
        if (dia == null) {
            return null;
        }
        return dia.getCodigo();
    }

    @Override
    public DiaSemana convertToEntityAttribute(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        return DiaSemana.toEnum(codigo);
    }
}
