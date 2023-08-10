package com.desafio.lembretes.repository;

import com.desafio.lembretes.entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LembreteRepository extends JpaRepository<Lembrete,Long> {
}
