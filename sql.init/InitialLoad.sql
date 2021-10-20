/*
-- Initial Table Load

-- Date: 2021-09-27 00:12
*/
use ensinosuperior;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE ensinosuperior.configuracao;
TRUNCATE TABLE ensinosuperior.contingenteAcesso;
TRUNCATE TABLE ensinosuperior.erroProcessamento;
TRUNCATE TABLE ensinosuperior.execucaoAgente;
TRUNCATE TABLE ensinosuperior.mediaAcesso;
TRUNCATE TABLE ensinosuperior.curso;
TRUNCATE TABLE ensinosuperior.grauEnsino;
TRUNCATE TABLE ensinosuperior.estabelecimento;
TRUNCATE TABLE ensinosuperior.faseAcesso;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES  ('1','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2020_1.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2020','1','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES  ('2','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2020_2.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2020','2',^\\d{4};[\\w\\d]{4};.*,1,NULL,'2021-09-26 22:02:54',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES  ('3','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2019_1.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2019','1','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES  ('4','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2019_2.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2019','2','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES  ('5','https://dados.gov.pt/s/dadosGovFiles/InstituicoesdoEnsinoSuperior.json',"com.ual.laboratorio.agents.JSONOrganizationExtractor",'INSTITUICOES',NULL,NULL,NULL,NULL,NULL,NULL,'2021-09-26 22:05:43',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES  ('6','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2018_1.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2018','1','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES  ('7','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2018_2.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2018','2','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES  ('8','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2017_1.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2017','1','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES  ('9','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2017_2.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2017','2','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES ('10','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2016_1.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2016','1','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);
INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES ('11','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2016_2.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2016','2','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);
-- INSERT INTO ensinosuperior.configuracao (`idConfiguracao`,`url`,`javaProcessor`,`tipoDocumento`,`checkSum`,`ano`,`fase`,`regExpLinhas`,`correcaoValores`,`dataUltAcesso`,`dataCriacao`,`dataAlteracao`) VALUES ('11','https://orientacao-vocacional.com.pt/wp-content/uploads/documentos/media_2021_1.pdf',"com.ual.laboratorio.agents.PDFApplicationResultsExtractor",'MEDIAS',NULL,'2021','1','^\\d{4};[\\w\\d]{4};.*',1,NULL,'2021-09-26 22:02:54',NULL);

/*
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '1');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '2');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '3');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '4');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '6');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '7');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '8');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '9');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '10');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '', `correcaoValores` = '0' WHERE (`idConfiguracao` = '11');
UPDATE `ensinosuperior`.`configuracao` SET `checkSum` = '' WHERE (`idConfiguracao` = '5');
*/

INSERT INTO `ensinosuperior`.`contingenteAcesso` (`idcontingente`, `nome`) VALUES ('1', 'Geral');
INSERT INTO `ensinosuperior`.`contingenteAcesso` (`idcontingente`, `nome`) VALUES ('2', 'Candidatos Oriundos da Região Autónoma dos Açores');
INSERT INTO `ensinosuperior`.`contingenteAcesso` (`idcontingente`, `nome`) VALUES ('3', 'Candidatos Oriundos da Região Autónoma da Madeira');
INSERT INTO `ensinosuperior`.`contingenteAcesso` (`idcontingente`, `nome`) VALUES ('4', 'Candidatos Emigrantes Portugueses e Familiares que com eles residam');
INSERT INTO `ensinosuperior`.`contingenteAcesso` (`idcontingente`, `nome`) VALUES ('5', 'Candidatos Militares');
INSERT INTO `ensinosuperior`.`contingenteAcesso` (`idcontingente`, `nome`) VALUES ('6', 'Candidatos com Deficiência');

INSERT INTO `ensinosuperior`.`grauEnsino` (`codgrau`, `nome`) VALUES ('L1', 'Licenciatura');
INSERT INTO `ensinosuperior`.`grauEnsino` (`codgrau`, `nome`) VALUES ('MI', 'Mestrado Integrado');
INSERT INTO `ensinosuperior`.`grauEnsino` (`codgrau`, `nome`) VALUES ('PL', 'Preparatório de uma Licenciatura');
INSERT INTO `ensinosuperior`.`grauEnsino` (`codgrau`, `nome`) VALUES ('PM', 'Preparatório de um Mestrado integrado');

INSERT INTO `ensinosuperior`.`faseAcesso` (`idFase`, `nome`) VALUES ('1', 'Fase 1');
INSERT INTO `ensinosuperior`.`faseAcesso` (`idFase`, `nome`) VALUES ('2', 'Fase 2');
INSERT INTO `ensinosuperior`.`faseAcesso` (`idFase`, `nome`) VALUES ('3', 'Fase 3');