package com.hotelaria.Service;

import java.util.List;

import com.hotelaria.Entity.Reserva;
import com.hotelaria.Repository.ReservaRepository;

public class ReservaService {

    private ReservaRepository repository = new ReservaRepository();

    public void cadastrar(Reserva reserva) {
        repository.salvar(reserva);
    }

    public List<Reserva> listar() {
        return repository.listar();
    }

    public Reserva buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public void atualizar(Reserva reserva) {
        repository.atualizar(reserva);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}