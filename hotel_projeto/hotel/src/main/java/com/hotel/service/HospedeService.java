package com.hotel.service;

import com.hotel.entity.Hospede;
import com.hotel.exception.HospedeNotFoundException;
import com.hotel.repository.HospedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    public Hospede criar(Hospede hospede) {
        try {
            if (hospedeRepository.existsByCpf(hospede.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado: " + hospede.getCpf());
            }
            return hospedeRepository.save(hospede);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar hóspede: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public List<Hospede> listarTodos() {
        try {
            return hospedeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar hóspedes: " + e.getMessage(), e);
        }
    }

    @Transactional(readOnly = true)
    public Hospede buscarPorId(Long id) {
        try {
            return hospedeRepository.findById(id)
                    .orElseThrow(() -> new HospedeNotFoundException(id));
        } catch (HospedeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar hóspede: " + e.getMessage(), e);
        }
    }

    public Hospede atualizar(Long id, Hospede dados) {
        try {
            Hospede hospede = hospedeRepository.findById(id)
                    .orElseThrow(() -> new HospedeNotFoundException(id));
            hospede.setNome(dados.getNome());
            hospede.setEmail(dados.getEmail());
            hospede.setTelefone(dados.getTelefone());
            return hospedeRepository.save(hospede);
        } catch (HospedeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar hóspede: " + e.getMessage(), e);
        }
    }

    public void deletar(Long id) {
        try {
            Hospede hospede = hospedeRepository.findById(id)
                    .orElseThrow(() -> new HospedeNotFoundException(id));
            hospedeRepository.delete(hospede);
        } catch (HospedeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar hóspede: " + e.getMessage(), e);
        }
    }
}
