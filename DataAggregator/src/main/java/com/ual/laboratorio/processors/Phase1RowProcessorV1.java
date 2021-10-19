package com.ual.laboratorio.processors;

import com.ual.laboratorio.entities.*;
import com.ual.laboratorio.entities.Keys.AcessoID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
@Slf4j
@Component
public class Phase1RowProcessorV1 extends RowProcessor {

    @Override
    public MediaAcesso getCourseAccess() throws ParseException {
        if (this.row==null) throw new UnsupportedOperationException ("Missing or Empty Row");
        Estabelecimento estabelecimento= getOrganization();
        Curso curso= getCourse();
        MediaAcesso acesso=null;
        NumberFormat format = NumberFormat.getInstance(new Locale("pt"));
        Number number = format.parse(numberCorrection(row.get(7).getText()));

        acesso = MediaAcesso.builder()
                .idAcesso( AcessoID.builder()
                        .idCurso(curso.getIdCurso())
                        .idEstabelecimento(estabelecimento.getIdEstabelecimento())
                        .ano(year)
                        .fase(fase)
                        .build()
                )
                .vagasIniciais(Integer.parseInt(numberCorrection(row.get(5).getText())))
                .colocados(Integer.parseInt(numberCorrection(row.get(6).getText())))
                .notaUltimoColocado(number.doubleValue())
                .vagasSobrantes(Integer.parseInt(numberCorrection(row.get(8).getText())))
                .build();
        return acesso;
    }

    @Override
    public boolean checkParserCompatibility(Configuracao configuracao){
        return configuracao.getFase()==1;
    }
}
