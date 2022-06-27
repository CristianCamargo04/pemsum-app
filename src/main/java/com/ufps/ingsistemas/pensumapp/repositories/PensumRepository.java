package com.ufps.ingsistemas.pensumapp.repositories;

import com.ufps.ingsistemas.pensumapp.entities.PensumEntity;
import org.springframework.data.repository.CrudRepository;

public interface PensumRepository extends CrudRepository<PensumEntity,String> {
}
