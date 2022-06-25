package com.ufps.ingsistemas.pensumapp.repositories;

import com.ufps.ingsistemas.pensumapp.entities.MateriaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MateriaRepository extends CrudRepository<MateriaEntity,String> {
    public List<MateriaEntity> findBySemestre(String semestre);
}
