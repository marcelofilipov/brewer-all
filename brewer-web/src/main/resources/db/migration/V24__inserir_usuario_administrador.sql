ALTER TABLE usuario AUTO_INCREMENT = 1;
INSERT INTO usuario (nome, email, senha, ativo) VALUES ('Admin', 'admin@brewer.com', '$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', 1);
INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (1, 1);
