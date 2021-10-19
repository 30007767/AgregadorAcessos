package com.ual.laboratorio.repositories;

import com.ual.laboratorio.entities.ErroProcessamento;
import org.springframework.data.repository.CrudRepository;

public interface ErrorsRepository extends CrudRepository<ErroProcessamento, Integer> {
}