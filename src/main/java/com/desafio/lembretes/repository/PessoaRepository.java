package com.desafio.lembretes.repository;

import com.desafio.lembretes.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    @Query("SELECT p FROM Pessoa p WHERE p.nome = :nome")
    Pessoa findByName(@Param("nome") String nome);
}
