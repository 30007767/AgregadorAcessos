package com.ual.laboratorio.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "estabelecimento") // This tells Hibernate to make a table out of this class
@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamicUpdate
public class Estabelecimento {
    @Id
    @JsonProperty("codigodoestabelecimento")
    public Integer idEstabelecimento;
    @JsonProperty("entityid")
    private String GuidEntidade;
    @JsonProperty("nomedoestabelecimento")
    private String nome;
    private String morada;
    @JsonProperty("codigodependede")
    private Integer refIdEstabelecimento;
    @JsonProperty("codigopostal")
    private String codPostal;
    private String distrito;
    private String concelho;
    @JsonProperty("tipodeensino")
    private String tipoEnsino;
    @JsonProperty("paginaweb")
    private String paginaWeb;
    private String email;
    private String telefone;
    private String fax;
    private int ativo;
    @Column(updatable=false, insertable=false)
    private Date dataCriacao;
    private Date dataAlteracao;

}

