package com.hotelaria;

import java.time.LocalDate;

import com.hotelaria.Controller.HospedeController;
import com.hotelaria.Controller.QuartoController;
import com.hotelaria.Controller.ReservaController;
import com.hotelaria.Entity.Hospede;
import com.hotelaria.Entity.Quarto;
import com.hotelaria.Entity.Reserva;

public class Main {

    public static void main(String[] args) {

        HospedeController hospedeController = new HospedeController();
        QuartoController quartoController = new QuartoController();
        ReservaController reservaController = new ReservaController();

        Hospede hospede = new Hospede("Tobias", "3757000000");
        hospedeController.cadastrar(hospede);

        Quarto quarto = new Quarto("101", "Luxo", "250.00");
        quartoController.cadastrar(quarto);

        Reserva reserva = new Reserva(
                "LocalDate.of(2026, 4, 26)",
                "LocalDate.of(2026, 4, 30)",
                hospede,
                quarto
                
        );

        reservaController.cadastrar(reserva);

        reservaController.listar().forEach(r -> {
            System.out.println(
                    r.getId() + " - " +
                    r.getHospede().getNome() + " - quarto " +
                    r.getQuarto().getNumero() + " - " +
                    r.getDataEntrada() + " até " +
                    r.getDataSaida()
            );
        });
    }
}