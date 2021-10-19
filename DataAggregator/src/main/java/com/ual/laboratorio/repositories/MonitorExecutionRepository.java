package com.ual.laboratorio.repositories;

import com.ual.laboratorio.entities.ExecucaoAgente;
import org.springframework.data.repository.CrudRepository;

public interface MonitorExecutionRepository extends CrudRepository<ExecucaoAgente, Integer> {
}