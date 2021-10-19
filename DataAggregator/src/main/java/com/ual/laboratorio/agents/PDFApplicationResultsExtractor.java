package com.ual.laboratorio.agents;

import com.ual.laboratorio.entities.*;
import com.ual.laboratorio.processors.RowProcessor;
import com.ual.laboratorio.repositories.CursoRepository;
import com.ual.laboratorio.repositories.ErrorsRepository;
import com.ual.laboratorio.repositories.EstabelecimentoRepository;
import com.ual.laboratorio.repositories.MediaAcessoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technology.tabula.*;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Service
public class PDFApplicationResultsExtractor extends Extractor{
    @Autowired
    private MediaAcessoRepository acessos;
    @Autowired
    private CursoRepository cursos;
    @Autowired
    private EstabelecimentoRepository estabelecimentos;
    @Autowired
    private RowProcessor[] processors;
    @Autowired
    private ErrorsRepository errors;


    public void execute(Configuracao configuration, ExecucaoAgente execucao) throws IOException {
            ObjectExtractor oe = new ObjectExtractor(PDDocument.load(getContent()));
            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
            PageIterator it = oe.extract();

            while (it.hasNext()) {
                Page page = it.next();
                for (Table table : sea.extract(page)) {
                    for (List<RectangularTextContainer> row : table.getRows()) {
                        processRecord(row, configuration,execucao);
                    }
                }
            }
            oe.close();
    }
    private void processRecord(List<RectangularTextContainer> row, Configuracao configuration, ExecucaoAgente execucao) {
        Optional<RowProcessor> optProcessor= getFileProcessor(configuration);
        if (optProcessor.isPresent()) {
            RowProcessor rowProcessor = optProcessor.get();
            rowProcessor.setRowData(row, configuration.getAno(), configuration.getFase(), configuration.getCorrecaoValores());
            if (rowProcessor.isValidEntry(configuration)) {
                totalRecords++;
                Estabelecimento estabelecimento=null;
                Curso curso=null;
                MediaAcesso acesso=null;
                estabelecimento = extractOrganizationData(configuration, execucao, rowProcessor);
                curso = (estabelecimento!=null)?extractCourseData(configuration, execucao, rowProcessor):null;
                acesso = (curso!=null)?ExtractCourseApplicationData(configuration, execucao, rowProcessor):null;
                if (estabelecimento==null||curso==null||acesso==null) totalErrors++;
                //verify data and check for errors
                try {
                    saveOrganizationEntity(estabelecimento);
                    saveCourseEntity(curso);
                    saveCourseApplicationAccess(acesso);
                } catch (Exception ex) {
                    log.error("Error {} persisting Data row: {}", ex.getMessage(), rowProcessor.getRowValues());
                    errors.save(ErroProcessamento.builder().
                            execucaoAgente(execucao)
                            .erro(ex.getMessage())
                            .registo( rowProcessor.getRowValues())
                            .build());
                    totalErrors++;
                }

            }
        }
    }

    private MediaAcesso ExtractCourseApplicationData(Configuracao configuration, ExecucaoAgente execucao, RowProcessor rowProcessor) {
        MediaAcesso acesso=null;
        try{
            acesso = rowProcessor.getCourseAccess();
        }catch(Exception e){
            log.info(String.format("Year: %1$s Row does not contain valid Application Access Data: %2$s", configuration.getAno(), rowProcessor.getRowValues()));
            errors.save(ErroProcessamento.builder().
                    execucaoAgente(execucao)
                    .erro(e.getMessage())
                    .registo( rowProcessor.getRowValues())
                    .build());
        }
        return acesso;
    }

    private Curso extractCourseData(Configuracao configuration, ExecucaoAgente execucao, RowProcessor rowProcessor) {
        Curso curso=null;
        try{
            curso = rowProcessor.getCourse();
        }catch(Exception e){
            log.info(String.format("Year: %1$s Row does not contain valid Course Data: %2$s", configuration.getAno(), rowProcessor.getRowValues()));
            errors.save(ErroProcessamento.builder().
                    execucaoAgente(execucao)
                    .erro(e.getMessage())
                    .registo( rowProcessor.getRowValues())
                    .build());
        }
        return curso;
    }

    private Estabelecimento extractOrganizationData(Configuracao configuration, ExecucaoAgente execucao, RowProcessor rowProcessor) {
        Estabelecimento  estabelecimento=null;
        try {
            estabelecimento = rowProcessor.getOrganization();
        }catch(Exception e){
            log.info(String.format("Year: %1$s Row does not contain valid Organization Data: %2$s", configuration.getAno(), rowProcessor.getRowValues()));
            errors.save(ErroProcessamento.builder().
                    execucaoAgente(execucao)
                    .erro(e.getMessage())
                    .registo( rowProcessor.getRowValues())
                    .build());
        }
        return estabelecimento;
    }

    public Optional<RowProcessor> getFileProcessor(Configuracao configuracao){
            return Arrays.stream(processors).filter(processor -> processor.checkParserCompatibility(configuracao)).findFirst();
    }
    private void saveCourseEntity(Curso curso) {
        if (curso !=null)
            cursos.save(curso);

    }

    private void saveOrganizationEntity(Estabelecimento estabelecimento) {
        if (estabelecimento!=null && !estabelecimentos.findById(estabelecimento.getIdEstabelecimento()).isPresent() )
            estabelecimentos.save(estabelecimento);

    }
    private void saveCourseApplicationAccess(MediaAcesso acesso) {
        if (acesso !=null)
            acessos.save(acesso);
    }
}
