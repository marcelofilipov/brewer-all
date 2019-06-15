CREATE TABLE estilo (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    estilo VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO estilo VALUES (0, 'Amber Lager');
INSERT INTO estilo VALUES (0, 'Dark Lager');
INSERT INTO estilo VALUES (0, 'Pale Lager');
INSERT INTO estilo VALUES (0, 'Pilsner');