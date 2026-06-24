package com.hotel.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkins")
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // JPA @OneToOne: Um checkin pertence a exatamente uma reserva
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = false, unique = true)
    private Reserva reserva;

    @Column(name = "data_hora_entrada", nullable = false)
    private LocalDateTime dataHoraEntrada;

    @Column(name = "numero_hospedes", nullable = false)
    private Integer numeroHospedes;

    public Checkin() {}

    public Checkin(Reserva reserva, Integer numeroHospedes) {
        this.reserva = reserva;
        this.numeroHospedes = numeroHospedes;
        this.dataHoraEntrada = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }
    public LocalDateTime getDataHoraEntrada() { return dataHoraEntrada; }
    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) { this.dataHoraEntrada = dataHoraEntrada; }
    public Integer getNumeroHospedes() { return numeroHospedes; }
    public void setNumeroHospedes(Integer numeroHospedes) { this.numeroHospedes = numeroHospedes; }
}
