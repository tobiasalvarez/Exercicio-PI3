package com.hotelaria.Service;

import java.util.List;

import com.hotelaria.Entity.Hospede;
import com.hotelaria.Repository.HospedeRepository;

public class HospedeService {

    private HospedeRepository repository =
            new HospedeRepository();

    public void cadastrar(Hospede hospede) {

        repository.salvar(hospede);

    }

    public List<Hospede> listar() {

        return repository.listar();

    }

    public void deletar(Long id) {

        repository.deletar(id);

    }

    public void atualizar(Hospede hospede) {

        repository.atualizar(hospede);

    }

    public Hospede buscarPorId(Long id) {

        return repository.buscarPorId(id);

    }
}