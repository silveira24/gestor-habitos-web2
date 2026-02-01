package com.habitos.gestor_habitos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "habitos")
public class Habito {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;

    @NotBlank(message = "O nome do hábito não pode estar em branco")
    private String nome;

    private String descricao;

    @NotNull(message = "A frequência do hábito não pode ser nula")
    private Integer frequencia;

    @ElementCollection(targetClass = DiaSemana.class)
    @CollectionTable(name = "habito_dias_semana", joinColumns = @JoinColumn(name = "habito_id"))
    @Enumerated(EnumType.STRING)
    private Set<DiaSemana> diasSemana;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "habito", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Registro> registros;
}
