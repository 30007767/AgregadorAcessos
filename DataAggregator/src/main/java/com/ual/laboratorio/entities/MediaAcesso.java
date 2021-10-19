package com.ual.laboratorio.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.ual.laboratorio.entities.Keys.AcessoID;
import lombok.*;

import java.util.Date;

@Entity(name = "mediaAcesso")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaAcesso {
    @EmbeddedId
    private AcessoID idAcesso;
    private int vagasIniciais;
    private int colocados;
    private Double notaUltimoColocado;
    private int vagasSobrantes;
    private int vagasRecolocados;
    private int colocadosVagaAdicional;
    private int colocadosSemClassificacao;
    //private int idExecucao;
    @Column(updatable=false, insertable=false)
    private Date dataCriacao;
    private Date dataAlteracao=new Date();
}