package com.habitos.gestor_habitos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarSenhaUsuarioDTO(
        @NotBlank(message = "A senha atual é obrigatória")
        String senhaAtual,
        @NotBlank(message = "A nova senha é obrigatória")
        @Size(min = 6, message = "A nova senha deve ter no mínimo 6 caracteres")
        @Size(max = 100, message = "A nova senha deve ter no máximo 100 caracteres")
        String novaSenha
) {
}
