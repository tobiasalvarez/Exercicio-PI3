package com.hotelaria.Controller;

import java.util.List;

import com.hotelaria.Entity.Reserva;
import com.hotelaria.Service.ReservaService;

public class ReservaController {

    private ReservaService service = new ReservaService();

    public void cadastrar(Reserva reserva) {
        service.cadastrar(reserva);
    }

    public List<Reserva> listar() {
        return service.listar();
    }

    public Reserva buscarPorId(Long id) {
        return service.buscarPorId(id);
    }

    public void atualizar(Reserva reserva) {
        service.atualizar(reserva);
    }

    public void deletar(Long id) {
        service.deletar(id);
    }
}