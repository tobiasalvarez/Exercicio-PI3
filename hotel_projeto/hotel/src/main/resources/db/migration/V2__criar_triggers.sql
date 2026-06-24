-- V2__criar_triggers.sql

-- TRIGGER 1: Impede cadastrar hóspede com CPF duplicado
DROP TRIGGER IF EXISTS trg_hospede_cpf_unico;

CREATE TRIGGER trg_hospede_cpf_unico
    BEFORE INSERT ON hospedes
    FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM hospedes WHERE cpf = NEW.cpf) > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Já existe um hóspede cadastrado com este CPF.';
    END IF;
END;

-- TRIGGER 2: Impede checkout sem checkin registrado
DROP TRIGGER IF EXISTS trg_validar_checkout;

CREATE TRIGGER trg_validar_checkout
    BEFORE DELETE ON checkins
    FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM checkins WHERE reserva_id = OLD.reserva_id) = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Não é possível realizar checkout sem checkin registrado.';
    END IF;
END;

-- TRIGGER 3: Atualiza status do quarto automaticamente ao fazer checkin
DROP TRIGGER IF EXISTS trg_quarto_ocupado_checkin;

CREATE TRIGGER trg_quarto_ocupado_checkin
    AFTER INSERT ON checkins
    FOR EACH ROW
BEGIN
    UPDATE quartos
    SET status = 'OCUPADO'
    WHERE id = (SELECT quarto_id FROM reservas WHERE id = NEW.reserva_id);
END;
