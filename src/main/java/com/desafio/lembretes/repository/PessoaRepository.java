package com.desafio.lembretes.repository;

import com.desafio.lembretes.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    List<Pessoa> findByNome(String nome);
}
