package com.ufps.ingsistemas.pensumapp.services;

import com.ufps.ingsistemas.pensumapp.models.output.SemestreOutput;
import com.ufps.ingsistemas.pensumapp.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SemestreService {
    @Autowired
    MateriaRepository materiaRepository;

//    public List<SemestreOutput> listarSemestres(){
//        List<SemestreOutput> semestres = new ArrayList<SemestreOutput>();
//        for (int i = 1; i <= 10; i++){
//            var materias = materiaRepository.findBySemestre(Integer.toString(i));
//            var semestre = SemestreOutput
//                    .builder()
//                    .semestre(Integer.toString(i))
//                    .materias(Collections.singletonList(materias))
//                    .build();
//            semestres.add(semestre);
//        }
//        return semestres;
//    }
}
