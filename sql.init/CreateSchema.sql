-- user:root
-- passwod:password
drop database `ensinosuperior`;

CREATE DATABASE `ensinosuperior` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


use ensinosuperior;

/*SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `ensinosuperior`.`configuracao`;
DROP TABLE IF EXISTS `ensinosuperior`.`contingenteAcesso`;
DROP TABLE IF EXISTS `ensinosuperior`.`curso`;
DROP TABLE IF EXISTS `ensinosuperior`.`erroProcessamento`;
DROP TABLE IF EXISTS  `ensinosuperior`.`estabelecimento`;
DROP TABLE IF EXISTS  `ensinosuperior`.`execucaoAgente`;
DROP TABLE IF EXISTS  `ensinosuperior`.`grauEnsino`;
DROP TABLE IF EXISTS  `ensinosuperior`.`mediaAcesso`;
DROP TABLE IF EXISTS  `ensinosuperior`.`faseAcesso`;
SET FOREIGN_KEY_CHECKS = 1;
*/
CREATE TABLE `configuracao` (
  `idConfiguracao` int NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `javaProcessor` varchar(255) DEFAULT NULL,
  `tipoDocumento` varchar(50) DEFAULT NULL,
  `ano` int DEFAULT NULL,
  `fase` int DEFAULT NULL,
  `checkSum` varchar(255) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `regExpLinhas` varchar(50) DEFAULT NULL,
  `correcaoValores` tinyint DEFAULT NULL,
  `dataUltAcesso` timestamp NULL DEFAULT NULL,
  `dataCriacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `dataAlteracao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idConfiguracao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contingenteAcesso` (
  `idcontingente` int NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `dataCriacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `dataAlteracao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idcontingente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `grauEnsino` (
  `codgrau` varchar(10) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `dataCriacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `dataAlteracao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`codgrau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `curso` (
  `idCurso` varchar(4) NOT NULL,
  `idEstabelecimento` int NOT NULL,
  `grau` varchar(10) NOT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `ativo` int DEFAULT NULL,
  `dataCriacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `dataAlteracao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idCurso`),
  KEY `fk_cursos_Estabelecimento_idx` (`idEstabelecimento`),
  KEY `fk_curso_grauEnsino_idx` (`grau`),
  CONSTRAINT `fk_curso_grauEnsino` FOREIGN KEY (`grau`) REFERENCES `grauEnsino` (`codgrau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `execucaoAgente` (
  `idExecucao` int NOT NULL AUTO_INCREMENT,
  `idConfiguracao` int NOT NULL,
  `totalRegistos` int NOT NULL,
  `totalErros` int NOT NULL,
  `taxaSucesso` decimal(5,2) NOT NULL,
  `dataInicio` timestamp NOT NULL,
  `dataFim` timestamp NULL DEFAULT NULL,
  `estado` varchar(50) NOT NULL,
  PRIMARY KEY (`idExecucao`),
  KEY `fk_execucaoAgente_configuracaoAgentes_idx` (`idConfiguracao`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `erroProcessamento` (
  `idErro` int NOT NULL AUTO_INCREMENT,
  `idExecucao` int NOT NULL,
  `registo` varchar(512) NOT NULL,
  `erro` varchar(1024) NOT NULL,
  `dataCriacao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idErro`),
  KEY `fk_erroProcessamento_execucaoAgente_idx` (`idExecucao`),
  CONSTRAINT `fk_erroProcessamento_execucaoAgente` FOREIGN KEY (`idExecucao`) REFERENCES `execucaoAgente` (`idExecucao`)  
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `estabelecimento` (
  `idEstabelecimento` int NOT NULL COMMENT 'código DGE da instituição de ensino superior\nNão podem existir duplicados caso existam\no registo pode no entanto estar inativo',
  `GuidEntidade` varchar(100) DEFAULT NULL COMMENT 'chave guid para identificar registo com processod elimentação de dados\n',
  `nome` varchar(200) DEFAULT NULL COMMENT 'identificação do establecimento de esino',
  `morada` varchar(500) DEFAULT NULL,
  `refIdEstabelecimento` int DEFAULT NULL COMMENT 'referencia para estabelecimento agregadora',
  `codPostal` varchar(100) DEFAULT NULL,
  `distrito` varchar(100) DEFAULT NULL,
  `concelho` varchar(100) DEFAULT NULL,
  `tipoEnsino` varchar(100) DEFAULT NULL,
  `paginaWeb` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `ativo` int DEFAULT NULL COMMENT 'Permite identificar quais os establecimento ativos\nvalores possiveis:\n1:ativo\n0:inativo\n',
  `dataCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `dataAlteracao` datetime DEFAULT NULL,
  PRIMARY KEY (`idEstabelecimento`),
  UNIQUE KEY `idEstabelecimento_UNIQUE` (`idEstabelecimento`),
  KEY `fk_estabelecimento_estabelecimento_idx` (`refIdEstabelecimento`,`idEstabelecimento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Entidade com as instituições de ensino superior';

CREATE TABLE `faseAcesso` (
  `idFase` int NOT NULL,
  `Nome` varchar(100) DEFAULT NULL,
  `dataCriacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `dataAlteracao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idFase`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mediaAcesso` (
  `idEstabelecimento` int NOT NULL,
  `idCurso` varchar(4) NOT NULL,
  `ano` int NOT NULL,
  `fase` int NOT NULL,
  `vagasIniciais` int DEFAULT NULL,
  `colocados` int DEFAULT NULL,
  `notaUltimoColocado` decimal(5,2) DEFAULT NULL,
  `vagasSobrantes` int DEFAULT NULL,
  `vagasRecolocados` int NOT NULL,
  `colocadosVagaAdicional` int DEFAULT NULL,
  `colocadosSemClassificacao` int DEFAULT NULL,
  `dataCriacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `dataAlteracao` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idEstabelecimento`,`idCurso`,`ano`,`fase`),
  KEY `fk_mediaAcesso_curso1_idx` (`idCurso`),
  KEY `fk_mediaAcesso_faseAcesso_idx` (`fase`),
  CONSTRAINT `fk_mediaAcesso_curso` FOREIGN KEY (`idCurso`) REFERENCES `curso` (`idCurso`),
  CONSTRAINT `fk_mediaAcesso_estabelecimento` FOREIGN KEY (`idEstabelecimento`) REFERENCES `estabelecimento` (`idEstabelecimento`),
  CONSTRAINT `fk_mediaAcesso_faseAcesso` FOREIGN KEY (`fase`) REFERENCES `faseAcesso` (`idFase`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
