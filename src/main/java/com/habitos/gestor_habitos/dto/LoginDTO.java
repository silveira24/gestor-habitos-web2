package com.habitos.gestor_habitos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginDTO {

    @Schema(name = "LoginRequest",description = "Dados de requisição para login")
    public record request(
            @Schema(description = "Email do usuário", example = "example@email.com")
            @NotBlank(message = "O email não pode estar em branco")
            @Email(message = "Formato de email inválido")
            String email,
            @Schema(description = "Senha do usuário", example = "SenhaSegura123!")
            @NotBlank(message = "A senha não pode estar em branco")
            @NotNull(message = "A senha não pode ser nula")
            String senha
    ){}

    @Schema(name = "LoginResponse",description = "Dados de resposta para login")
    public record response(
            @Schema(description = "Token JWT para autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
            String token
    ){}
}
