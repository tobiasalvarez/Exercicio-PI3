package com.hotel.repository;

import com.hotel.entity.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {

    Optional<Checkin> findByReservaId(Long reservaId);

    boolean existsByReservaId(Long reservaId);
}
