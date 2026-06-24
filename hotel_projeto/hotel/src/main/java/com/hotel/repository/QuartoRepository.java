package com.hotel.repository;

import com.hotel.entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    List<Quarto> findByStatus(String status);

    // INNER JOIN: quartos que possuem reservas vinculadas
    @Query("SELECT DISTINCT q FROM Quarto q INNER JOIN q.reservas r")
    List<Quarto> findQuartosComReservas();

    // LEFT JOIN: todos os quartos, com ou sem reservas
    @Query("SELECT DISTINCT q FROM Quarto q LEFT JOIN q.reservas r")
    List<Quarto> findTodosComReservas();
}
