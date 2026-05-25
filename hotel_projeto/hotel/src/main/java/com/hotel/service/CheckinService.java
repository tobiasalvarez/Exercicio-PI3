package com.hotel.service;

import com.hotel.entity.Checkin;
import com.hotel.entity.Quarto;
import com.hotel.entity.Reserva;
import com.hotel.exception.ReservaNotFoundException;
import com.hotel.repository.CheckinRepository;
import com.hotel.repository.QuartoRepository;
import com.hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    // CHECKIN = criar o registro e marcar quarto como OCUPADO
    public Checkin realizarCheckin(Long reservaId, Integer numeroHospedes) {
        try {
            Reserva reserva = reservaRepository.findById(reservaId)
                    .orElseThrow(() -> new ReservaNotFoundException(reservaId));

            if (checkinRepository.existsByReservaId(reservaId)) {
                throw new IllegalStateException("Esta reserva já possui checkin registrado.");
            }

            // Marca quarto como ocupado
            Quarto quarto = reserva.getQuarto();
            quarto.setStatus("OCUPADO");
            quartoRepository.save(quarto);

            Checkin checkin = new Checkin(reserva, numeroHospedes);
            return checkinRepository.save(checkin);
        } catch (ReservaNotFoundException | IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar checkin: " + e.getMessage(), e);
        }
    }

    // CHECKOUT = deletar o checkin e marcar quarto como DISPONIVEL
    public void realizarCheckout(Long reservaId) {
        try {
            Checkin checkin = checkinRepository.findByReservaId(reservaId)
                    .orElseThrow(() -> new RuntimeException("Checkin não encontrado para a reserva ID: " + reservaId));

            // Libera o quarto
            Quarto quarto = checkin.getReserva().getQuarto();
            quarto.setStatus("DISPONIVEL");
            quartoRepository.save(quarto);

            checkinRepository.delete(checkin);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar checkout: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<Checkin> listarTodos() {
        try {
            return checkinRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar checkins: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public Checkin buscarPorReserva(Long reservaId) {
        try {
            return checkinRepository.findByReservaId(reservaId)
                    .orElseThrow(() -> new RuntimeException("Checkin não encontrado para reserva ID: " + reservaId));
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar checkin: " + e.getMessage(), e);
        }
    }
}
