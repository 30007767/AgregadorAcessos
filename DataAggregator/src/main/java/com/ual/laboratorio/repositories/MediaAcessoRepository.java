package com.ual.laboratorio.repositories;
import com.ual.laboratorio.entities.Keys.AcessoID;
import org.springframework.data.repository.CrudRepository;

import com.ual.laboratorio.entities.*;


public interface MediaAcessoRepository extends CrudRepository<MediaAcesso, AcessoID> {

}

