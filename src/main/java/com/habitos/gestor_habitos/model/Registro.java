package com.habitos.gestor_habitos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "registros", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"data", "habito_id"})
})
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;

    @NotNull(message = "A data do registro não pode ser nula")
    private LocalDate data;
    private Boolean concluido = false;
    private LocalDateTime dataConclusao;

    @ManyToOne
    @JoinColumn(name = "habito_id", nullable = false)
    @JsonBackReference
    private Habito habito;
}
