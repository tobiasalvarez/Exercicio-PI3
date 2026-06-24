package com.hotel.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "quartos")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Double precoDiaria;

    @Column(nullable = false)
    private Integer capacidade;

    @Column(nullable = false)
    private String status; // DISPONIVEL, OCUPADO

    // JPA @OneToMany: Um quarto pode ter várias reservas
    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    public Quarto() {}

    public Quarto(String numero, String tipo, Double precoDiaria, Integer capacidade) {
        this.numero = numero;
        this.tipo = tipo;
        this.precoDiaria = precoDiaria;
        this.capacidade = capacidade;
        this.status = "DISPONIVEL";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Double getPrecoDiaria() { return precoDiaria; }
    public void setPrecoDiaria(Double precoDiaria) { this.precoDiaria = precoDiaria; }
    public Integer getCapacidade() { return capacidade; }
    public void setCapacidade(Integer capacidade) { this.capacidade = capacidade; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<Reserva> getReservas() { return reservas; }
    public void setReservas(List<Reserva> reservas) { this.reservas = reservas; }
}
