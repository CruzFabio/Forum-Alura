CREATE TABLE respostas (
    resposta_id BIGINT NOT NULL AUTO_INCREMENT,
    mensagem VARCHAR(300) NOT NULL UNIQUE,
    data_criacao DATETIME NOT NULL,
    solucao BOOLEAN NOT NULL,
    topico_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,
    PRIMARY KEY (resposta_id),
    FOREIGN KEY(topico_id) REFERENCES topicos(topico_id) ON DELETE CASCADE,
    FOREIGN KEY(autor_id) REFERENCES usuarios(usuario_id) ON DELETE CASCADE
)ENGINE=InnoDB;