-- consultas_joins.sql
-- Exemplos de JOIN para o sistema de hotel

-- INNER JOIN: reservas com dados do hóspede e quarto
SELECT
    r.id            AS reserva_id,
    h.nome          AS hospede,
    h.cpf,
    q.numero        AS quarto,
    q.tipo,
    r.data_checkin,
    r.data_checkout
FROM reservas r
INNER JOIN hospedes h ON h.id = r.hospede_id
INNER JOIN quartos  q ON q.id = r.quarto_id;


-- LEFT JOIN: todos os hóspedes, mesmo sem reservas
SELECT
    h.nome,
    h.cpf,
    COUNT(r.id) AS total_reservas
FROM hospedes h
LEFT JOIN reservas r ON r.hospede_id = h.id
GROUP BY h.id, h.nome, h.cpf;


-- LEFT JOIN: todas as reservas, com ou sem checkin
SELECT
    r.id           AS reserva_id,
    h.nome         AS hospede,
    q.numero       AS quarto,
    c.data_hora_entrada,
    c.numero_hospedes
FROM reservas r
INNER JOIN hospedes h ON h.id = r.hospede_id
INNER JOIN quartos  q ON q.id = r.quarto_id
LEFT  JOIN checkins c ON c.reserva_id = r.id;


-- RIGHT JOIN: todos os quartos, com ou sem reserva
SELECT
    q.numero,
    q.tipo,
    q.status,
    r.id AS reserva_id,
    r.data_checkin,
    r.data_checkout
FROM reservas r
RIGHT JOIN quartos q ON q.id = r.quarto_id
ORDER BY q.numero;
