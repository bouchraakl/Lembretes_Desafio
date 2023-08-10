package com.desafio.lembretes.service;

import com.desafio.lembretes.entity.Pessoa;
import com.desafio.lembretes.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        pessoa.setNome(pessoa.getNome().trim());
        return pessoaRepository.save(pessoa);
    }

    public Pessoa editarPessoa(Pessoa pessoa) {
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
}
