package com.ual.laboratorio.runners;

import com.ual.laboratorio.agents.Extractor;
import com.ual.laboratorio.entities.Configuracao;
import com.ual.laboratorio.entities.ExecucaoAgente;
import com.ual.laboratorio.repositories.ConfigurationRepository;
import com.ual.laboratorio.repositories.MonitorExecutionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ual.laboratorio.repositories"})
@Slf4j
@ComponentScan(basePackages = { "com.ual.laboratorio.*" })
@EntityScan("com.ual.laboratorio.*")
@EnableScheduling
public class MonitorRunner {
	@Autowired
	Extractor[] extractors;
	@Autowired
	private ConfigurationRepository configurations;
	@Autowired
	private MonitorExecutionRepository executions;

	public static void main(String[] args)  {
		SpringApplication.run(MonitorRunner.class, args);
	}

	@Scheduled(cron = "0 */2 * * * *")
	public void run() throws IOException {
		Instant start = Instant.now();
		log.info("EXECUTING : command line runner");
		log.info("EXECUTING: Retriving all configurations");
		configurations.findAll().forEach(this::processConfiguration);
		Instant finnish = Instant.now();
		Duration duration = Duration.between(start, finnish);
		log.info("FINISHED : command line runner in " + duration.toSeconds() + " seconds ");
	}

	private void processConfiguration(Configuracao configuration) {
		String estado="Em execução";
		Instant startProcessor = Instant.now();
		log.info("Starting processor: {} for configuration id: {}", configuration.getJavaProcessor(),configuration.getIdConfiguracao());
		ExecucaoAgente execution=new ExecucaoAgente();
		execution.setIdConfiguracao(configuration.getIdConfiguracao());
		execution.setDataInicio(new Date());
		configuration.setEstado(estado);
		execution.setEstado(estado);
		configurations.save(configuration);
		execution=executions.save(execution);
		//Identificar extractor pela nome da classe Java
		//A coleção é preenchida pelo componentScan do SpringBoot as anotações @Autowired fazem  a Injecção de dependencias
		Optional<Extractor> optionalExtractor = Arrays.stream(extractors).filter(iExtractor -> iExtractor.getClass().getName().equals(configuration.getJavaProcessor()))
				.findFirst();
		Extractor extractor = null;
		if (optionalExtractor.isPresent()) {
			extractor = optionalExtractor.get();
			extractor.reset();
			try {
				//check md5 checksum
				if (extractor.shouldExecute(configuration)) {
					extractor.execute(configuration,execution );
					configuration.setCheckSum(extractor.getMd5Checksum());
				}else{
					log.info("Url:{} content has same checksum:{} bypassing extraction...",configuration.getUrl(),configuration.getCheckSum());
					estado="Sem Alterações";
				}
			} catch (IOException ex) {
				log.error("Extractor reported an error :{}", ex.getMessage());
				estado="Erro";
			}
			Instant stopProcessor = Instant.now();
			Duration processorDuration = Duration.between(startProcessor, stopProcessor);
			if (configuration.getTipoDocumento().equals("MEDIAS")) {
				log.info("Processor for year:{} phase:{} finished in {} seconds", configuration.getAno(), configuration.getFase(), processorDuration.getSeconds());
			}else {
				log.info("Processor finished in {} seconds ", processorDuration.getSeconds());
			}
			if (extractor != null && extractor.getTotalRecords()>0) {
				log.info("Total Records:{} Errors:{} SuccessRate:{}%", extractor.getTotalRecords(), extractor.getTotalErrors(), String.format("%.2f", extractor.getSuccessRate()));
				estado="Sucesso";
			}
		} else {
			log.error("Unable to find a suitable Extractor with name:{}", configuration.getJavaProcessor());
			execution.setEstado("Erro");
		}
		execution.setEstado(estado);
		execution.setTotalErros(extractor.getTotalErrors());
		execution.setTotalRegistos(extractor.getTotalRecords());
		execution.setTaxaSucesso(new BigDecimal(extractor.getSuccessRate()));
		configuration.setEstado(estado);
		//persist configuration data
		execution.setDataFim(new Date());
		executions.save(execution);
		configuration.setDataUltAcesso(new Date());
		configurations.save(configuration);
	}
}
