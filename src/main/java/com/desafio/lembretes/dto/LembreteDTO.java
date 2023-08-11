package com.desafio.lembretes.dto;

import com.desafio.lembretes.entity.Pessoa;
import lombok.Data;

@Data
public class LembreteDTO {

    private Long id;
    private Pessoa pessoa;
    private String titulo;
    private String descricao;
}
