package com.ual.laboratorio.agents;

import com.ual.laboratorio.entities.Configuracao;
import com.ual.laboratorio.entities.ExecucaoAgente;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@Slf4j
public abstract class Extractor {
    protected int totalErrors=0;
    protected int totalRecords=0;
    byte[] content;
    String md5Checksum=null;

    public abstract void execute(Configuracao configuration, ExecucaoAgente execucao) throws IOException;
    public double getSuccessRate() {
        return (totalRecords>0)?(getTotalRecords()-getTotalErrors())/(double)getTotalRecords() * 100:0;
    }
    public void reset(){
        totalErrors=0;
        totalRecords=0;
        content=null;
        md5Checksum=null;

    }
    public String calculateMD5Hash(byte[] bytes) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
    public boolean shouldExecute(Configuracao configuration) {
        URL url = null;
        InputStream in = null;
        try {
            url = new URL(configuration.getUrl());
            in = url.openStream();
            content=in.readAllBytes();
            in.close();
        } catch (Exception e) {
            log.error("Mal Formed or invalid URL:{}", configuration.getUrl());
        }
        setMd5Checksum(calculateMD5Hash(content));
        return !getMd5Checksum().equals(configuration.getCheckSum());
    }

}
