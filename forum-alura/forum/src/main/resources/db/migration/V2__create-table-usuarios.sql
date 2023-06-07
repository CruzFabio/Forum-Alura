CREATE TABLE usuarios (
    usuario_id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email varchar(100) NOT NULL UNIQUE,
    senha VARCHAR(300) NOT NULL,
    PRIMARY KEY (usuario_id)
)ENGINE=InnoDB;