package com.desafio.lembretes.service;

import com.desafio.lembretes.entity.Lembrete;
import com.desafio.lembretes.repository.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LembreteService {

    private final LembreteRepository lembreteRepository;

    @Autowired
    public LembreteService(LembreteRepository lembreteRepository) {
        this.lembreteRepository = lembreteRepository;
    }

    public Lembrete createLembrete(Lembrete lembrete) {
        return lembreteRepository.save(lembrete);
    }

    public Lembrete editarLembrete(Lembrete lembrete) {
        Long id = lembrete.getId();
        if (id == null) {
            throw new IllegalArgumentException("O ID do lembrete não pode ser nulo");
        }

        Optional<Lembrete> lembreteExistente = lembreteRepository.findById(id);
        if (lembreteExistente.isEmpty()) {
            throw new IllegalArgumentException("Lembrete com o ID " + id + " não encontrado");
        }

        return lembreteRepository.save(lembrete);
    }

    public void excluirLembrete(Long id) {
        Optional<Lembrete> lembrete = lembreteRepository.findById(id);
        if (lembrete.isPresent()) {
            lembreteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Lembrete com ID " + id + " não encontrado.");
        }
    }
}
