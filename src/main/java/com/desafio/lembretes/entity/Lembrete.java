package com.desafio.lembretes.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lembretes", schema = "public")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "pessoa")
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "A pessoa associada não pode estar vazia.")
    @JsonBackReference
    private Pessoa pessoa;

    @Column(name = "titulo_lembrete", nullable = false, length = 100)
    @NotBlank(message = "O título do lembrete não pode estar em branco.")
    @Size(max = 100, message = "O título do lembrete deve ter no máximo 100 caracteres.")
    private String titulo;

    @Column(name = "descricao_lembrete", nullable = false)
    private String descricao;

}