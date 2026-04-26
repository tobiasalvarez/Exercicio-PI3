package com.hotelaria.Service;

import java.util.List;

import com.hotelaria.Entity.Hospede;
import com.hotelaria.Entity.Quarto;
import com.hotelaria.Repository.QuartoRepository;

public class QuartoService {

    private QuartoRepository repository =
            new QuartoRepository();

    public void cadastrar(Quarto quarto) {

        repository.salvar(quarto);

    }

    public List<Quarto> listar() {

        return repository.listar();

    }
    public void deletar(Long id) {

        repository.deletar(id);

    }

    public void atualizar(Hospede hospede) {

        repository.atualizar(hospede);

    }

    public Quarto buscarPorId(Long id) {

        return repository.buscarPorId(id);

    }
}