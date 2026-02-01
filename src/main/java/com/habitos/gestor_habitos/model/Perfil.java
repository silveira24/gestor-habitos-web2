package com.habitos.gestor_habitos.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "perfis")
public class Perfil {

    public Perfil() {
        this.nivel = 1;
        this.pontosExperiencia = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private String id;

    private String nomeExibicao;
    private String bio;

    private Integer nivel;
    private Integer pontosExperiencia;

    @NotNull
    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;
}
