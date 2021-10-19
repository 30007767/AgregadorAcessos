package com.ual.laboratorio.processors;

import com.ual.laboratorio.entities.Configuracao;
import com.ual.laboratorio.entities.Curso;
import com.ual.laboratorio.entities.Estabelecimento;
import com.ual.laboratorio.entities.MediaAcesso;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import technology.tabula.RectangularTextContainer;

import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Getter
public abstract  class RowProcessor {
    protected List<RectangularTextContainer> row=null;
    protected  String rowValues;
    protected int year;
    protected int fase;
    private boolean validEntry;
    protected boolean valueCorrectionActive;
    public RowProcessor() {
    }

    public void setRowData(List<RectangularTextContainer> row, int year,int fase, boolean useValueCorrection) {
        this.row=row;
        this.year=year;
        this.fase=fase;
        this.rowValues="";
        this.row.stream().forEach(column -> this.rowValues+=column.getText()+";");
        validEntry=false;
        this.valueCorrectionActive = useValueCorrection;
    }

    public Estabelecimento getOrganization() {
        Estabelecimento estabelecimento=null;
        if (this.row==null) throw new UnsupportedOperationException ("Missing or Empty Row");
          estabelecimento= Estabelecimento.builder()
                .idEstabelecimento(Integer.parseInt(numberCorrection(row.get(0).getText())))
                .nome(row.get(2).getText())
                .ativo(1)
                .build();
        return estabelecimento;
    }


    public Curso getCourse() {
        Curso curso=null;
        if (this.row==null) throw new UnsupportedOperationException ("Missing or Empty Row");
        Estabelecimento estabelecimento= getOrganization();
            curso = Curso.builder()
                    .idCurso(row.get(1).getText())
                    .idEstabelecimento(estabelecimento.getIdEstabelecimento())
                    .nome(row.get(3).getText())
                    .grau(CourseDegreeCorrection(row.get(4).getText()))
                    .ativo(1)
                    .build();
        return curso;
    }
    public abstract MediaAcesso getCourseAccess() throws ParseException;
    public  abstract boolean checkParserCompatibility(Configuracao configuracao);

    public boolean isValidEntry(Configuracao configuration) {
        Pattern pattern = Pattern.compile(configuration.getRegExpLinhas());
        return pattern.matcher(rowValues).matches();


    }
    public String numberCorrection(String value){
            if (!isValueCorrectionActive()) return value ;
            if(value.isBlank() || value.isEmpty())
                return "0";
            else
                return  value.replaceAll("[^0-9,]", "");
    }
    public String CourseDegreeCorrection(String value) {
        if (!isValueCorrectionActive()) return value ;
        if(value.isBlank() || value.isEmpty())
            return "";
        else
            return  value.replaceAll("[^1LMPI]", "");
    }

}
