package com.hotel.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // JPA @ManyToOne: Várias reservas para um hóspede
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospede_id", nullable = false)
    private Hospede hospede;

    // JPA @ManyToOne: Várias reservas para um quarto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarto_id", nullable = false)
    private Quarto quarto;

    @Column(name = "data_checkin", nullable = false)
    private LocalDate dataCheckin;

    @Column(name = "data_checkout", nullable = false)
    private LocalDate dataCheckout;

    // JPA @OneToOne: Uma reserva possui no máximo um checkin
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Checkin checkin;

    public Reserva() {}

    public Reserva(Hospede hospede, Quarto quarto, LocalDate dataCheckin, LocalDate dataCheckout) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Hospede getHospede() { return hospede; }
    public void setHospede(Hospede hospede) { this.hospede = hospede; }
    public Quarto getQuarto() { return quarto; }
    public void setQuarto(Quarto quarto) { this.quarto = quarto; }
    public LocalDate getDataCheckin() { return dataCheckin; }
    public void setDataCheckin(LocalDate dataCheckin) { this.dataCheckin = dataCheckin; }
    public LocalDate getDataCheckout() { return dataCheckout; }
    public void setDataCheckout(LocalDate dataCheckout) { this.dataCheckout = dataCheckout; }
    public Checkin getCheckin() { return checkin; }
    public void setCheckin(Checkin checkin) { this.checkin = checkin; }
}
