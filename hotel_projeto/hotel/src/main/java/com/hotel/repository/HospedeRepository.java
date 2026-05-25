package com.hotel.repository;

import com.hotel.entity.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {

    Optional<Hospede> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    // INNER JOIN: hóspedes que possuem reservas
    @Query("SELECT DISTINCT h FROM Hospede h INNER JOIN h.reservas r")
    List<Hospede> findHospedesComReservas();

    // LEFT JOIN: todos os hóspedes com suas reservas (inclusive sem reserva)
    @Query("SELECT DISTINCT h FROM Hospede h LEFT JOIN h.reservas r")
    List<Hospede> findTodosComReservas();
}
