package com.habitos.gestor_habitos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public class PerfilDTO {
    @Schema(name = "AtualizarPerfilRequest", description = "Dados para atualização do perfil do usuário")
    public record AtualizarPerfil(
            @Schema(description = "Nome de exibição do perfil do usuário.", example = "Novo Usuário")
            @Size(min = 2, max = 50, message = "O nome de exibição deve ter entre 2 e 50 caracteres")
            String nomeExibicao,
            @Schema(description = "Biografia do perfil do usuário.", example = "Apaixonado por hábitos saudáveis.")
            @Size(max = 200, message = "A biografia deve ter no máximo 200 caracteres")
            String bio
    ) {
    }

}
