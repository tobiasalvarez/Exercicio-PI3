package com.hotel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.hotel.service.HospedeService;
import com.hotel.service.QuartoService;
import com.hotel.view.TelaHospede;
import com.hotel.view.TelaInicio;
import com.hotel.view.TelaQuarto;

@SpringBootApplication
public class HotelApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(HotelApplication.class);
        builder.headless(false);
        var context = builder.run(args);

        HospedeService hospedeService = context.getBean(HospedeService.class);
        TelaHospede telaHospede = new TelaHospede(hospedeService);

        QuartoService quartoService = context.getBean(QuartoService.class);
        TelaQuarto telaQuarto = new TelaQuarto(quartoService);

        TelaInicio telaInicio = new TelaInicio(hospedeService, quartoService);

        telaHospede.setVisible(false);
        telaQuarto.setVisible(false);
        telaInicio.setVisible(true);
    }
}


