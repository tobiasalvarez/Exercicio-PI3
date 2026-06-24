-- V1__criar_tabelas.sql

CREATE TABLE IF NOT EXISTS hospedes (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(100) NOT NULL,
    cpf      VARCHAR(14)  NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL,
    telefone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS quartos (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero       VARCHAR(10)   NOT NULL UNIQUE,
    tipo         VARCHAR(50)   NOT NULL,
    preco_diaria DOUBLE        NOT NULL,
    capacidade   INT           NOT NULL,
    status       VARCHAR(20)   NOT NULL DEFAULT 'DISPONIVEL'
);

CREATE TABLE IF NOT EXISTS reservas (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    hospede_id    BIGINT NOT NULL,
    quarto_id     BIGINT NOT NULL,
    data_checkin  DATE   NOT NULL,
    data_checkout DATE   NOT NULL,
    CONSTRAINT fk_reserva_hospede FOREIGN KEY (hospede_id) REFERENCES hospedes(id),
    CONSTRAINT fk_reserva_quarto  FOREIGN KEY (quarto_id)  REFERENCES quartos(id)
);

CREATE TABLE IF NOT EXISTS checkins (
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    reserva_id        BIGINT   NOT NULL UNIQUE,
    data_hora_entrada DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    numero_hospedes   INT      NOT NULL,
    CONSTRAINT fk_checkin_reserva FOREIGN KEY (reserva_id) REFERENCES reservas(id)
);
