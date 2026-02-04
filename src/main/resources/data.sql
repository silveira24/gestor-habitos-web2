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

INSERT INTO usuarios (id, email, senha, role)
VALUES (
           'b1c2d3e4-f5a6-7890-1234-56789abcdef1',
           'silveira@email.com',
        '$2a$10$5pCNSQ5pRr6MN//MORjO..Dzb/G93QmH9CQiCZE56ZCO3Ig6b31o2',
           'USER'
       );

INSERT INTO perfis (id, nome_exibicao, bio, nivel, pontos_experiencia, usuario_id)
VALUES (
           'e5f6d7c8-90a1-2345-6789-abcdef012346',
           'Silveira',
           'Perfil do usuário Silveira',
           1,
           0,
           'b1c2d3e4-f5a6-7890-1234-56789abcdef1'
       );

INSERT INTO usuarios (id, email, senha, role)
VALUES (
           'c1d2e3f4-a5b6-7890-1234-56789abcdef2',
           'malu@email.com',
        '$2a$10$5pCNSQ5pRr6MN//MORjO..Dzb/G93QmH9CQiCZE56ZCO3Ig6b31o2',
              'USER'
         );

INSERT INTO perfis (id, nome_exibicao, bio, nivel, pontos_experiencia, usuario_id)
VALUES (
           'd5e6f7c8-90a1-2345-6789-abcdef012347',
           'Malu',
           'Perfil do usuário Malu',
           1,
           0,
           'c1d2e3f4-a5b6-7890-1234-56789abcdef2'
       );

INSERT INTO habitos (id, titulo, descricao, usuario_id)
VALUES (
              'h1a2b3c4-d5e6-7890-1234-56789abcdef3',
              'Exercício Físico',
              'Praticar exercícios físicos por pelo menos 30 minutos diariamente.',
              'b1c2d3e4-f5a6-7890-1234-56789abcdef1'
         );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
              'h1a2b3c4-d5e6-7890-1234-56789abcdef3',
              2
         );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h1a2b3c4-d5e6-7890-1234-56789abcdef3',
           3
       );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
              'h1a2b3c4-d5e6-7890-1234-56789abcdef3',
              4
         );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h1a2b3c4-d5e6-7890-1234-56789abcdef3',
           5
       );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
              'h1a2b3c4-d5e6-7890-1234-56789abcdef3',
              6
         );

INSERT INTO habitos (id, titulo, descricao, usuario_id)
VALUES (
           'h2a3b4c5-d6e7-8901-2345-6789abcdef4',
           'Leitura Diária',
           'Ler por pelo menos 20 minutos diariamente.',
           'b1c2d3e4-f5a6-7890-1234-56789abcdef1'
       );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h2a3b4c5-d6e7-8901-2345-6789abcdef4',
           3
       );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h2a3b4c5-d6e7-8901-2345-6789abcdef4',
           4
         );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h2a3b4c5-d6e7-8901-2345-6789abcdef4',
           5
         );

INSERT INTO habitos (id, titulo, descricao, usuario_id)
VALUES (
           'h3a4b5c6-d7e8-9012-3456-789abcdef5',
           'Meditação',
           'Praticar meditação por pelo menos 10 minutos diariamente.',
           'c1d2e3f4-a5b6-7890-1234-56789abcdef2'
       );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h3a4b5c6-d7e8-9012-3456-789abcdef5',
           2
         );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h3a4b5c6-d7e8-9012-3456-789abcdef5',
           4
         );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h3a4b5c6-d7e8-9012-3456-789abcdef5',
           6
         );

INSERT INTO habitos (id, titulo, descricao, usuario_id)
VALUES (
           'h4a5b6c7-d8e9-0123-4567-89abcdef6',
           'Ler todos os dias',
              'Ler por pelo menos 20 minutos diariamente.',
              'c1d2e3f4-a5b6-7890-1234-56789abcdef2'
         );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h4a5b6c7-d8e9-0123-4567-89abcdef6',
           2
            );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h4a5b6c7-d8e9-0123-4567-89abcdef6',
           3
         );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h4a5b6c7-d8e9-0123-4567-89abcdef6',
           4
         );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h4a5b6c7-d8e9-0123-4567-89abcdef6',
           5
       );

INSERT INTO habito_dias_semana (habito_id, dia_semana_codigo)
VALUES (
           'h4a5b6c7-d8e9-0123-4567-89abcdef6',
           6
       );

-- =================================================================
-- GERADOR AUTOMÁTICO DE REGISTROS (PRÓXIMOS 7 DIAS)
-- =================================================================

INSERT INTO registros (id, data, concluido, data_conclusao, habito_id)
WITH RECURSIVE Agenda(data_futura) AS (
    -- 1. Começa hoje
    SELECT CURRENT_DATE
    UNION ALL
    -- 2. Adiciona +1 dia até chegar em Hoje + 7
    SELECT DATEADD('DAY', 1, data_futura)
    FROM Agenda
    WHERE data_futura < DATEADD('DAY', 7, CURRENT_DATE)
)
SELECT
    random_uuid(),    -- ID (Gera um UUID novo)
    a.data_futura,    -- DATA (Data gerada pela recursão)
    false,            -- CONCLUIDO (Sempre falso ao criar)
    NULL,             -- DATA_CONCLUSAO (Nulo, pois não foi feito ainda)
    h.id              -- HABITO_ID
FROM Agenda a
         CROSS JOIN habitos h
         INNER JOIN habito_dias_semana hds ON h.id = hds.habito_id
-- Verifica se o dia da semana da data bate com o dia configurado no hábito
WHERE hds.dia_semana_codigo = DAY_OF_WEEK(a.data_futura);
