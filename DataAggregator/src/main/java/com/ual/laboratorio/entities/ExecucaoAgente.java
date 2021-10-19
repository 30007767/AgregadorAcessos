package com.ual.laboratorio.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity(name = "execucaoAgente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecucaoAgente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idExecucao;
    private Integer idConfiguracao;
    private int totalRegistos;
    private int totalErros;
    @Column(precision = 5,scale = 2)
    private BigDecimal taxaSucesso=new BigDecimal(0);
    private Date dataInicio;
    private Date dataFim;
    private String estado;

    @OneToMany(mappedBy = "execucaoAgente")
    private Collection<ErroProcessamento> erroProcessamento;

    public Collection<ErroProcessamento> getErroProcessamento() {
        return erroProcessamento;
    }

    public void setErroProcessamento(Collection<ErroProcessamento> erroProcessamento) {
        this.erroProcessamento = erroProcessamento;
    }
}
