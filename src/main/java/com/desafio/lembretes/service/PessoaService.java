package com.desafio.lembretes.service;

import com.desafio.lembretes.dto.PessoaDTO;
import com.desafio.lembretes.entity.Pessoa;
import com.desafio.lembretes.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa cadastrarPessoa(PessoaDTO pessoadto) {
        Pessoa pessoa = toPessoa(pessoadto);
        return pessoaRepository.save(pessoa);
    }

    public Pessoa editarPessoa(PessoaDTO pessoadto) {
        Pessoa pessoa = toPessoa(pessoadto);
        Optional<Pessoa> pessoaBanco = pessoaRepository.findById(pessoa.getId());
        Assert.isTrue(pessoaBanco.isPresent(), "Pessoa não encontrada!");
        Assert.isTrue(pessoaBanco.get().getId().equals(pessoa.getId()),
                "ID da Pessoa informada não é o mesmo que o ID da Pessoa a ser atualizado!");
        return pessoaRepository.save(pessoa);
    }

    public void excluirPessoa(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            pessoaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ID da Pessoa não foi encontrado.");
        }
    }

    public List<PessoaDTO> findAll() {
        return pessoaRepository.findAll().stream().map(this::toPessoaDTO).toList();
    }
    public PessoaDTO findById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isEmpty()){
            throw new IllegalArgumentException("ERROR");
        }
        return toPessoaDTO(pessoa.get());
    }

    public PessoaDTO toPessoaDTO(Pessoa pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setLembretes(pessoa.getLembretes());
        return pessoaDTO;
    }

    public Pessoa toPessoa(PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setId(pessoaDTO.getId());
        pessoa.setLembretes(pessoaDTO.getLembretes());
        return pessoa;
    }



}