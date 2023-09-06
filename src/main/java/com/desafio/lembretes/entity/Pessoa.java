package com.desafio.lembretes.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pessoas", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "lembretes")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @NotNull(message = "O nome não pode estar nulo")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name = "nome_pessoa", nullable = false, length = 100)
    @JsonProperty("nome")
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    @JsonManagedReference
    private List<Lembrete> lembretes;

    public Pessoa(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}