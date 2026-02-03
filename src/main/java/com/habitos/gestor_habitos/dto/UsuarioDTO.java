package com.habitos.gestor_habitos.dto;

import com.habitos.gestor_habitos.model.enums.RoleUsuario;
import com.habitos.gestor_habitos.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

    @Schema(name = "UserRequest", description = "Dados necessários para criar um novo usuário.")
    public record Request(
        @Schema(description = "Email do usuário.", example = "example@email.com")
        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "Formato de email inválido")
        String email,
        @Schema(description = "Senha do usuário.", example = "senha123")
        @NotBlank(message = "A password não pode estar em branco")
        @Size(min = 6, message = "A password deve ter no mínimo 6 caracteres")
        @Size(max = 100, message = "A password deve ter no máximo 100 caracteres")
        String password
    ) {}

    @Schema(name = "ChangePasswordRequest", description = "Dados necessários para atualizar a password de um usuário.")
    public record ChangePasswordRequest(
        @Schema(description = "Senha atual do usuário.", example = "senha123")
        @NotBlank(message = "A password atual não pode estar em branco")
        String currentPassword,
        @Schema(description = "Nova password do usuário.", example = "senha456")
        @NotBlank(message = "A password não pode estar em branco")
        @Size(min = 6, message = "A password deve ter no mínimo 6 caracteres")
        @Size(max = 100, message = "A password deve ter no máximo 100 caracteres")
        String newPassword
    ) {}

    @Schema(name = "ChangeRoleRequest", description = "Dados necessários para alterar a role de um usuário.")
    public record AlterarRole(
        @Schema(description = "Nova role do usuário.", example = "ADMIN")
        @NotNull(message = "A nova role é obrigatória")
        RoleUsuario newRole
    ) {}

    @Schema(name = "UserResponse", description = "Dados retornados ao buscar ou listar usuários.")
    public record Response(
        @Schema(description = "ID do usuário.", example = "123e4567-e89b-12d3-a456-426614174000")
        String id,
        @Schema(description = "Email do usuário.", example = "example@email.com")
        String email,
        @Schema(description = "Role do usuário.", example = "USER")
        String role,
        @Schema(description = "Nome de exibição do perfil do usuário.", example = "Novo Usuário")
        String displayName,
        @Schema(description = "Biografia do perfil do usuário.", example = "Apaixonado por hábitos saudáveis.")
        String bio,
        @Schema(description = "Nível do perfil do usuário.", example = "1")
        Integer level,
        @Schema(description = "Pontos de experiência do perfil do usuário.", example = "120")
        Integer xpPoints
    ) {
        public Response(Usuario usuario) {
            this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getRole().name(),
                usuario.getPerfil() != null ? usuario.getPerfil().getNomeExibicao() : null,
                usuario.getPerfil() != null ? usuario.getPerfil().getBio() : null,
                usuario.getPerfil() != null ? usuario.getPerfil().getNivel() : null,
                usuario.getPerfil() != null ? usuario.getPerfil().getPontosExperiencia() : null
            );
        }
    }
}
