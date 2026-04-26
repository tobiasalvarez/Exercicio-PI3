package com.hotelaria.Controller;

import java.util.List;

import com.hotelaria.Entity.Hospede;
import com.hotelaria.Service.HospedeService;

public class HospedeController {

    private HospedeService service =
            new HospedeService();

    public void cadastrar(Hospede hospede) {

        service.cadastrar(hospede);

    }

    public List<Hospede> listar() {

        return service.listar();

    }

    public void deletar(Long id) {

        service.deletar(id);

    }

    public void atualizar(Hospede hospede) {

        service.atualizar(hospede);

    }

    public Hospede buscarPorId(Long id) {

        return service.buscarPorId(id);

    }
}