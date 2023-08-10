package com.desafio.lembretes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pessoas", schema = "public")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_pessoa", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "pessoa")
    private List<Lembrete> lembretes;

}
