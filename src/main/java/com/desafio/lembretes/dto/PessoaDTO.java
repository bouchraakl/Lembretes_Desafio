package com.desafio.lembretes.dto;

import com.desafio.lembretes.entity.Lembrete;
import lombok.Data;


import java.util.List;

@Data
public class PessoaDTO {
    private Long id;
    private String nome;
    private List<Lembrete> lembretes;
}
