package com.desafio.lembretes.dto;

import com.desafio.lembretes.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LembreteDTO {

    private Long id;
    private Pessoa pessoa;
    private String titulo;
    private String descricao;

}
