package com.ufps.ingsistemas.pensumapp.services;

import com.ufps.ingsistemas.pensumapp.models.output.MallaOutput;
import com.ufps.ingsistemas.pensumapp.models.output.SemestreOutput;
import com.ufps.ingsistemas.pensumapp.repositories.MateriaRepository;
import com.ufps.ingsistemas.pensumapp.repositories.PensumElectivaRepository;
import com.ufps.ingsistemas.pensumapp.repositories.PensumMateriaRepository;
import com.ufps.ingsistemas.pensumapp.vo.ElectivaMallaVO;
import com.ufps.ingsistemas.pensumapp.vo.ElectivaPensumVO;
import com.ufps.ingsistemas.pensumapp.vo.MateriaMallaVo;
import com.ufps.ingsistemas.pensumapp.vo.MateriaPensumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MallaService {
    @Autowired
    MateriaRepository materiaRepository;
    @Autowired
    PensumMateriaRepository pensumMateriaRepository;
    @Autowired
    PensumElectivaRepository pensumElectivaRepository;

    public List<MateriaPensumVO> listarMaterias(){
        return pensumMateriaRepository.findAllMaterias();
    }

    public List<ElectivaPensumVO> listarElectivas() {
        return pensumElectivaRepository.findAllElectivas();
    }

    public List<MateriaMallaVo> listarMateriasPorPensumYSemestre(String codPensum, String semestre){
        return pensumMateriaRepository.findAllMateriasByPensumBySemestre(codPensum,semestre);
    }

    public List<ElectivaMallaVO> listarMateriasPorPensumYCreditos(String codPensum, String creditos){
        return pensumMateriaRepository.findAllElectivasByPensumByCreditos(codPensum,creditos);
    }

    public MallaOutput obtenerMalla(String codPensum){
        List<SemestreOutput> semestres = new ArrayList<SemestreOutput>();
        for (int i = 1; i <= 10; i++){
            var materias = listarMateriasPorPensumYSemestre(codPensum,Integer.toString(i));
            var semestre = SemestreOutput
                    .builder()
                    .numero(Integer.toString(i))
                    .materias(Collections.singletonList(materias))
                    .build();
            semestres.add(semestre);
        }
        var sociohumanisticas = listarMateriasPorPensumYCreditos(codPensum,"2");
        var profesionales = listarMateriasPorPensumYCreditos(codPensum,"3");
        var malla = MallaOutput.builder()
                .codPensum(codPensum)
                .semestres(semestres)
                .sociohumanisticas(sociohumanisticas)
                .profesionales(profesionales)
                .build();
        return malla;
    }
}
