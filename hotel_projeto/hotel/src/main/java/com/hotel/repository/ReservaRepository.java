package com.hotel.repository;

import com.hotel.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByHospedeId(Long hospedeId);

    List<Reserva> findByQuartoId(Long quartoId);

    // INNER JOIN: reservas com hóspede e quarto
    @Query("SELECT r FROM Reserva r INNER JOIN r.hospede h INNER JOIN r.quarto q")
    List<Reserva> findTodasComDetalhes();

    // LEFT JOIN: reservas com ou sem checkin registrado
    @Query("SELECT r FROM Reserva r LEFT JOIN r.checkin c")
    List<Reserva> findTodasComCheckin();
}
