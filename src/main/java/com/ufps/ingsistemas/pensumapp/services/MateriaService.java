package com.ufps.ingsistemas.pensumapp.services;

import com.ufps.ingsistemas.pensumapp.entities.MateriaEntity;
import com.ufps.ingsistemas.pensumapp.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {
    @Autowired
    MateriaRepository materiaRepository;

    public MateriaEntity almacenarMateria(MateriaEntity materiaEntity){
        return materiaRepository.save(materiaEntity);
    }

    public List<MateriaEntity> listarMaterias(){
        return (List<MateriaEntity>) materiaRepository.findAll();
    }
}
