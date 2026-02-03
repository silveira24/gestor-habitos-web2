-- Senha para login: 123456
-- O hash abaixo é gerado via BCrypt ($2a$10$...)
INSERT INTO usuarios (id, email, senha, role)
VALUES (
           'a1b2c3d4-e5f6-7890-1234-56789abcdef0',
           'admin@sistema.com',
           '$2a$10$.5Elh8fgxypNUWhpUUr/xOa2sZm0VIaE0qWuGGl9otUfobb46T1Pq',
           'ADMIN'
       );

INSERT INTO perfis (id, nome_exibicao, bio, nivel, pontos_experiencia, usuario_id)
VALUES (
           'f5e6d7c8-90a1-2345-6789-abcdef012345',
           'Super Admin',
           'Perfil do administrador do sistema',
           99,
           0,
           'a1b2c3d4-e5f6-7890-1234-56789abcdef0'
       );