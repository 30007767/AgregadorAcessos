package com.ual.laboratorio.entities.Keys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcessoID implements Serializable {
    private int idEstabelecimento;
    private String idCurso;
    private int ano;
    private int fase;
}
