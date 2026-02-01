INSERT INTO usuarios (id, email, senha, role)
VALUES ('a1b2c3d4-e5f6-7890-1234-56789abcdef0', 'admin@sistema.com', '123789', 'SUPER_ADMIN');

INSERT INTO perfis (id, nome_exibicao, bio, nivel, pontos_experiencia, usuario_id)
VALUES ('f5e6d7c8-90a1-2345-6789-abcdef012345', 'Super Admin', 'Perfil do administrador do sistema', 99, 0, 'a1b2c3d4-e5f6-7890-1234-56789abcdef0');