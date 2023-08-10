package com.desafio.lembretes.service;

import com.desafio.lembretes.entity.Pessoa;
import com.desafio.lembretes.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    public PessoaRepository pessoaRepository;

    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        pessoa.setNome(pessoa.getNome().trim());
        return pessoaRepository.save(pessoa);
    }

    public Pessoa editarPessoa(Pessoa pessoa) {

        final Pessoa pessoaBanco = this.pessoaRepository.findById(pessoa.getId()).orElse(null);
        Assert.notNull(pessoaBanco, "Pessoa não encontrada!");
        Assert.isTrue(pessoaBanco.getId().equals(pessoa.getId()), "ID da Pessoa informada não " +
                "é a mesmo que o ID da Pessoa a ser atualizado!");

        return pessoaRepository.save(pessoa);
    }

    public void exluirPessoa(Long id) {
        if (pessoaRepository.findById(id).isPresent()){
            pessoaRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("ID da Pessoa não foi encontrado.");
        }

    }
}
