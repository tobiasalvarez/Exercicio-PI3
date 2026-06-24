package com.hotel.service;

import com.hotel.entity.Quarto;
import com.hotel.exception.QuartoNotFoundException;
import com.hotel.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;

    public Quarto criar(Quarto quarto) {
        try {
            quarto.setStatus("DISPONIVEL");
            return quartoRepository.save(quarto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar quarto: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<Quarto> listarTodos() {
        try {
            return quartoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar quartos: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public Quarto buscarPorId(Long id) {
        try {
            return quartoRepository.findById(id)
                    .orElseThrow(() -> new QuartoNotFoundException(id));
        } catch (QuartoNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar quarto: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<Quarto> listarDisponiveis() {
        try {
            return quartoRepository.findByStatus("DISPONIVEL");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar quartos disponíveis: " + e.getMessage(), e);
        }
    }

    public Quarto atualizar(Long id, Quarto dados) {
        try {
            Quarto quarto = quartoRepository.findById(id)
                    .orElseThrow(() -> new QuartoNotFoundException(id));
            quarto.setTipo(dados.getTipo());
            quarto.setPrecoDiaria(dados.getPrecoDiaria());
            quarto.setCapacidade(dados.getCapacidade());
            return quartoRepository.save(quarto);
        } catch (QuartoNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar quarto: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        try {
            Quarto quarto = quartoRepository.findById(id)
                    .orElseThrow(() -> new QuartoNotFoundException(id));
            quartoRepository.delete(quarto);
        } catch (QuartoNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar quarto: " + e.getMessage(), e);
        }
    }
}
