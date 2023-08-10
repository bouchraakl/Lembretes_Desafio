package com.desafio.lembretes.repository;

import com.desafio.lembretes.entity.Lembrete;
import com.desafio.lembretes.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LembreteRepository extends JpaRepository<Lembrete,Long> {

    @Query("SELECT l FROM Lembrete l WHERE l.pessoa.nome = :nome")
    List<Lembrete> findAllByPessoaNome(@Param("nome") String nome);

}
