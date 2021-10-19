package com.ual.laboratorio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity(name = "erroProcessamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroProcessamento {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idErro;
    @ManyToOne
    @JoinColumn(name = "idExecucao")
    private ExecucaoAgente execucaoAgente;
    private String registo;
    private String erro;
    @Column(updatable = false,insertable = false)
    private Date dataCriacao;

}
