package com.hotelaria.Controller;

import java.util.List;

import com.hotelaria.Entity.Hospede;
import com.hotelaria.Entity.Quarto;
import com.hotelaria.Service.QuartoService;

public class QuartoController {

    private QuartoService service =
            new QuartoService();

    public void cadastrar(Quarto quarto) {

        service.cadastrar(quarto);

    }

    public List<Quarto> listar() {

        return service.listar();

    }
    public void deletar(Long id) {

        service.deletar(id);

    }

    public void atualizar(Hospede hospede) {

        service.atualizar(hospede);

    }

    public Quarto buscarPorId(Long id) {

        return service.buscarPorId(id);

    }
}