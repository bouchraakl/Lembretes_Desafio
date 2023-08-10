package com.desafio.lembretes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pessoas", schema = "public")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @NotNull(message = "O nome não pode estar nulo")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name = "nome_pessoa", nullable = false,length = 100)
    private String nome;

    @OneToMany(mappedBy = "pessoa")
    private List<Lembrete> lembretes;

}
