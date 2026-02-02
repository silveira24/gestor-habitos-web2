package com.habitos.gestor_habitos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestor de Hábitos API")
                        .version("v1")
                        .description("API para gerenciamento de hábitos e acompanhamento de metas pessoais.")
                        .contact(new Contact()
                                .name("Gabriel Silveira e Maria Luiza Câmara")
                                .url("https://github.com/silveira24/gestor-habitos-web2"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor Local")
                ));
    }
}
