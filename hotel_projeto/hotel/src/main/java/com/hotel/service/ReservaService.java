package com.hotel.service;

import com.hotel.entity.Hospede;
import com.hotel.entity.Quarto;
import com.hotel.entity.Reserva;
import com.hotel.exception.HospedeNotFoundException;
import com.hotel.exception.QuartoNotFoundException;
import com.hotel.exception.ReservaNotFoundException;
import com.hotel.repository.HospedeRepository;
import com.hotel.repository.QuartoRepository;
import com.hotel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    public Reserva criar(Long hospedeId, Long quartoId, LocalDate dataCheckin, LocalDate dataCheckout) {
        try {
            Hospede hospede = hospedeRepository.findById(hospedeId)
                    .orElseThrow(() -> new HospedeNotFoundException(hospedeId));

            Quarto quarto = quartoRepository.findById(quartoId)
                    .orElseThrow(() -> new QuartoNotFoundException(quartoId));

            if (!dataCheckout.isAfter(dataCheckin)) {
                throw new IllegalArgumentException("Data de checkout deve ser posterior ao checkin.");
            }

            Reserva reserva = new Reserva(hospede, quarto, dataCheckin, dataCheckout);
            return reservaRepository.save(reserva);
        } catch (HospedeNotFoundException | QuartoNotFoundException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar reserva: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<Reserva> listarTodas() {
        try {
            return reservaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar reservas: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public Reserva buscarPorId(Long id) {
        try {
            return reservaRepository.findById(id)
                    .orElseThrow(() -> new ReservaNotFoundException(id));
        } catch (ReservaNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar reserva: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<Reserva> listarPorHospede(Long hospedeId) {
        try {
            return reservaRepository.findByHospedeId(hospedeId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar reservas do hóspede: " + e.getMessage(), e);
        }
    }

    public Reserva atualizar(Long id, LocalDate dataCheckin, LocalDate dataCheckout) {
        try {
            Reserva reserva = reservaRepository.findById(id)
                    .orElseThrow(() -> new ReservaNotFoundException(id));

            if (!dataCheckout.isAfter(dataCheckin)) {
                throw new IllegalArgumentException("Data de checkout deve ser posterior ao checkin.");
            }

            reserva.setDataCheckin(dataCheckin);
            reserva.setDataCheckout(dataCheckout);
            return reservaRepository.save(reserva);
        } catch (ReservaNotFoundException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar reserva: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        try {
            Reserva reserva = reservaRepository.findById(id)
                    .orElseThrow(() -> new ReservaNotFoundException(id));
            reservaRepository.delete(reserva);
        } catch (ReservaNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar reserva: " + e.getMessage(), e);
        }
    }
}
