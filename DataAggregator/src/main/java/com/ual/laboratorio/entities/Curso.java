package com.ual.laboratorio.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "curso") // This tells Hibernate to make a table out of this class
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Curso {
    @Id
    private String idCurso;
    private int idEstabelecimento;
    private String nome;
    private String grau;
    private int ativo;
    @Column(updatable=false, insertable=false)
    private Date dataCriacao;
    private Date dataAlteracao;
}
