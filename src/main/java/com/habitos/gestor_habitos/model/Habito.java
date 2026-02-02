package com.habitos.gestor_habitos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.habitos.gestor_habitos.model.enums.DiaSemana;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "O título do hábito não pode estar em branco")
    @Size(min = 3, max = 50, message = "O título deve ter entre 3 e 50 caracteres")
    private String titulo;

    @Size(max = 200, message = "A descrição deve ter no máximo 200 caracteres")
    private String descricao;

    @NotNull(message = "A frequência do hábito não pode ser nula")
    @Min(value = 1, message = "A frequência mínima é 1")
    @Max(value = 7, message = "A frequência máxima é 7")
    private Integer frequencia;

    @ElementCollection(targetClass = DiaSemana.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "habito_dias_semana", joinColumns = @JoinColumn(name = "habito_id"))
    @Column(name = "dia_semana_codigo")
    private Set<DiaSemana> diasSemana;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "habito", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Registro> registros;
}
