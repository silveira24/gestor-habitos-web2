package com.habitos.gestor_habitos.controller;

import com.habitos.gestor_habitos.dto.LoginDTO;
import com.habitos.gestor_habitos.model.Usuario;
import com.habitos.gestor_habitos.service.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Operações relacionadas à autenticação de usuários")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginDTO.response> login(@RequestBody @Valid LoginDTO.request dto) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());

        var authentication = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginDTO.response(token));
    }

}