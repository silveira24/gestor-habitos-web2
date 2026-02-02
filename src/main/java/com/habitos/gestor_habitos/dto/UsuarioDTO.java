package com.habitos.gestor_habitos.dto;

import com.habitos.gestor_habitos.model.enums.RoleUsuario;
import com.habitos.gestor_habitos.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {

    @Schema(description = "Dados necessários para criar um novo usuário.")
    public record usuarioRequest(
        @Schema(description = "Email do usuário.", example = "example@email.com")
        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "Formato de email inválido")
        String email,
        @Schema(description = "Senha do usuário.", example = "senha123")
        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
        String senha
    ) {}

    @Schema(description = "Dados necessários para atualizar a senha de um usuário.")
    public record usuarioAlterarSenha(
        @Schema(description = "Senha atual do usuário.", example = "senha123")
        @NotBlank(message = "A senha atual não pode estar em branco")
        String senhaAtual,
        @Schema(description = "Nova senha do usuário.", example = "senha456")
        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
        String novaSenha
    ) {}

    @Schema(description = "Dados necessários para alterar a role de um usuário.")
    public record usuarioAlterarRole(
        @Schema(description = "Nova role do usuário.", example = "ADMIN")
        @NotNull(message = "A nova role é obrigatória")
        RoleUsuario novaRole
    ) {}

    public record usuarioResponse(
        @Schema(description = "ID do usuário.", example = "123e4567-e89b-12d3-a456-426614174000")
        String id,
        @Schema(description = "Email do usuário.", example = "example@email.com")
        String email,
        @Schema(description = "Role do usuário.", example = "USER")
        String role,
        @Schema(description = "Nome de exibição do perfil do usuário.", example = "Novo Usuário")
        String nomeExibicao,
        @Schema(description = "Biografia do perfil do usuário.", example = "Apaixonado por hábitos saudáveis.")
        String bio,
        @Schema(description = "Nível do perfil do usuário.", example = "1")
        Integer nivel,
        @Schema(description = "Pontos de experiência do perfil do usuário.", example = "120")
        Integer pontosExperiencia
    ) {
        public usuarioResponse(Usuario usuario) {
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
