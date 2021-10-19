package com.ual.laboratorio.agents;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ual.laboratorio.entities.Configuracao;
import com.ual.laboratorio.entities.Estabelecimento;
import com.ual.laboratorio.agents.json.JSONData;
import com.ual.laboratorio.entities.ExecucaoAgente;
import com.ual.laboratorio.repositories.EstabelecimentoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@AllArgsConstructor
@Service
public class JSONOrganizationExtractor extends Extractor{
    @Autowired
    EstabelecimentoRepository repository;


    public void execute(Configuracao configuration, ExecucaoAgente execucao) throws IOException {
            ObjectMapper mapper=new ObjectMapper();
            JSONData data=  mapper.readValue(getContent(),JSONData.class);
            Estabelecimento[] estabelecimentos = data.estabelecimentos;
            totalRecords=estabelecimentos.length;

            Arrays.stream(estabelecimentos).sorted((o1, o2) -> o1.getIdEstabelecimento().compareTo(o2.getIdEstabelecimento()))
                    .forEach(estabelecimento -> repository.save(estabelecimento));

    }


}
