package com.ufps.ingsistemas.pensumapp.services;

import com.ufps.ingsistemas.pensumapp.entities.MateriaEntity;
import com.ufps.ingsistemas.pensumapp.models.input.MateriaInput;
import com.ufps.ingsistemas.pensumapp.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {
    @Autowired
    MateriaRepository materiaRepository;

    public MateriaEntity almacenarMateria(MateriaInput materiaInput){
        var materiaEntity = MateriaEntity.builder()
                .nombre(materiaInput.getNombre())
                .horas(materiaInput.getHoras())
                .creditos(materiaInput.getCreditos())
                .build();
        return materiaRepository.save(materiaEntity);
    }

    public List<MateriaEntity> listarMaterias(){
        return (List<MateriaEntity>) materiaRepository.findAll();
    }

    public MateriaEntity buscarMateria(Long id){
        return materiaRepository.findById(id).orElse(null);
    }

    public boolean eliminarMateria(Long id){
        var materiaDB = buscarMateria(id);
        if (materiaDB == null) return false;
        materiaRepository.deleteById(id);
        return true;
    }
}
