-- V3__dados_iniciais.sql

INSERT INTO hospedes (nome, cpf, email, telefone) VALUES
    ('João Silva',     '111.222.333-44', 'joao@email.com',   '(41) 99999-0001'),
    ('Maria Oliveira', '222.333.444-55', 'maria@email.com',  '(41) 99999-0002'),
    ('Carlos Santos',  '333.444.555-66', 'carlos@email.com', '(41) 99999-0003');

INSERT INTO quartos (numero, tipo, preco_diaria, capacidade, status) VALUES
    ('101', 'SIMPLES',    150.00, 1, 'DISPONIVEL'),
    ('201', 'DUPLO',      250.00, 2, 'DISPONIVEL'),
    ('301', 'SUITE',      450.00, 3, 'DISPONIVEL');
