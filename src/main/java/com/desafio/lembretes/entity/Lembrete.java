package com.desafio.lembretes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lembretes",schema = "public")
@Data
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_pessoa",nullable = false)
    @ManyToOne
    private Pessoa pessoa;

    @Column(name = "titulo_lembrete",nullable = false,length = 100)
    private String titulo;

    @Column(name = "disc_lembrete")
    private String discription;

}
