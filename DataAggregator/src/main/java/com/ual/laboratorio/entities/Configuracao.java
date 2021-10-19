package com.ual.laboratorio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Builder
@Entity(name = "configuracao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Configuracao {
            @Id
            private int idConfiguracao;
            private String url;
            private String javaProcessor;
            private String tipoDocumento;
            private String checkSum;
            private Integer ano;
            private Integer fase;
            private String estado;
            private String regExpLinhas;
            @Column(columnDefinition = "BIT")
            private Boolean correcaoValores;
            private Date dataUltAcesso=new Date();
            @Column(insertable = false,updatable = false)
            private Date dataCriacao;
            private Date dataAlteracao=new Date();
}
