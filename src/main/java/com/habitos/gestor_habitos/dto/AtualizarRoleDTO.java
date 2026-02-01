package com.habitos.gestor_habitos.dto;

import com.habitos.gestor_habitos.model.RoleUsuario;
import jakarta.validation.constraints.NotNull;

public record AtualizarRoleDTO(

        @NotNull
        RoleUsuario novaRole
) {
}
