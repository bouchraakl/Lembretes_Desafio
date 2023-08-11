package com.desafio.lembretes.service;

import com.desafio.lembretes.dto.LembreteDTO;
import com.desafio.lembretes.dto.PessoaDTO;
import com.desafio.lembretes.entity.Lembrete;
import com.desafio.lembretes.entity.Pessoa;
import com.desafio.lembretes.repository.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class LembreteService {

    private final LembreteRepository lembreteRepository;

    @Autowired
    public LembreteService(LembreteRepository lembreteRepository) {
        this.lembreteRepository = lembreteRepository;
    }

    public Lembrete createLembrete(LembreteDTO lembrete) {
        Lembrete lembreteCreate = toLembrete(lembrete);
        return lembreteRepository.save(lembreteCreate);
    }


    public Lembrete editarLembrete(LembreteDTO lembrete) {
        Lembrete lembreteEditar = toLembrete(lembrete);

        Long id = lembrete.getId();
        if (id == null) {
            throw new IllegalArgumentException("O ID do lembrete não pode ser nulo");
        }

        Optional<Lembrete> lembreteExistente = lembreteRepository.findById(id);
        if (lembreteExistente.isEmpty()) {
            throw new IllegalArgumentException("Lembrete com o ID " + id + " não encontrado");
        }

        return lembreteRepository.save(lembreteEditar);
    }


    public void excluirLembrete(Long id) {
        Optional<Lembrete> lembrete = lembreteRepository.findById(id);
        if (lembrete.isPresent()) {
            lembreteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Lembrete com ID " + id + " não encontrado.");
        }
    }

    public LembreteDTO findById(Long id) {
        Optional<Lembrete> lembrete = lembreteRepository.findById(id);
        if (lembrete.isEmpty()) {
            throw new IllegalArgumentException("ERROR");
        }
        return toLembreteDTO(lembrete.get());
    }

    public List<LembreteDTO> findAll() {
        return lembreteRepository.findAll().stream().map(this::toLembreteDTO).toList();
    }

    public List<LembreteDTO> findAllByPessoaNome(String nome) {
        return lembreteRepository.findAllByPessoaNome(nome).stream().map(this::toLembreteDTO).toList();
    }

    public LembreteDTO toLembreteDTO(Lembrete lembrete) {
        LembreteDTO lembreteDTO = new LembreteDTO();
        lembreteDTO.setPessoa(lembrete.getPessoa());
        lembreteDTO.setTitulo(lembrete.getTitulo());
        lembreteDTO.setDescricao(lembrete.getDescricao());
        lembreteDTO.setId(lembrete.getId());
        return lembreteDTO;
    }

    public Lembrete toLembrete(LembreteDTO lembreteDTO) {
        Lembrete lembrete = new Lembrete();
        lembrete.setDescricao(lembreteDTO.getDescricao());
        lembrete.setTitulo(lembreteDTO.getTitulo());
        lembrete.setId(lembreteDTO.getId());
        lembrete.setPessoa(lembreteDTO.getPessoa());
        return lembrete;
    }

}
