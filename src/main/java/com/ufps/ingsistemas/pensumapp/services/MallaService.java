package com.ufps.ingsistemas.pensumapp.services;

import com.ufps.ingsistemas.pensumapp.entities.MateriaEntity;
import com.ufps.ingsistemas.pensumapp.entities.PensumElectivaEntity;
import com.ufps.ingsistemas.pensumapp.entities.PensumMateriaEntity;
import com.ufps.ingsistemas.pensumapp.models.input.PensumElectivaInput;
import com.ufps.ingsistemas.pensumapp.models.input.PensumMateriaInput;
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
    @Autowired
    MateriaService materiaService;
    @Autowired
    PensumService pensumService;

    /*
        CRUD PENSUM-MATERIA
    */
    public PensumMateriaEntity almacenarMateria(PensumMateriaInput pensumMateriaInput){
        var materia = materiaService.buscarMateria(pensumMateriaInput.getIdMateria());
        var pensum = pensumService.encontrarPensum(pensumMateriaInput.getCodPensum());
        var pensumMateria = PensumMateriaEntity.builder()
                .id(pensumMateriaInput.getId())
                .materiaEntity(materia)
                .pensumEntity(pensum)
                .codigo(pensumMateriaInput.getCodigo())
                .semestre(pensumMateriaInput.getSemestre())
                .electiva(pensumMateriaInput.isElectiva())
                .codPerrequisito(pensumMateriaInput.getCodPerrequisito())
                .crePerrequisito(pensumMateriaInput.getCrePerrequisito())
                .build();
        return pensumMateriaRepository.save(pensumMateria);
    }
    public PensumMateriaEntity buscarMateria(Long id){
        return pensumMateriaRepository.findById(id).orElse(null);
    }
    public List<MateriaPensumVO> listarMaterias(){
        return pensumMateriaRepository.findAllMaterias();
    }

    public boolean eliminarMateria(Long id){
        var materiaDB = buscarMateria(id);
        if (materiaDB == null) return false;
        pensumMateriaRepository.deleteById(id);
        return true;
    }
    /*
        CRUD PENSUM-MATERIA
    */

    /*
        CRUD PENSUM-ELECTIVA
    */
    public List<ElectivaPensumVO> listarElectivas() {
        return pensumElectivaRepository.findAllElectivas();
    }

    public PensumElectivaEntity buscarElectiva(Long id){
        return pensumElectivaRepository.findById(id).orElse(null);
    }
    public PensumElectivaEntity almacenarElectiva(PensumElectivaInput pensumElectivaInput){
        var pensum = pensumService.encontrarPensum(pensumElectivaInput.getCodPensum());
        var pensumElectiva = PensumElectivaEntity.builder()
                .id(pensumElectivaInput.getId())
                .pensumEntity(pensum)
                .nombre(pensumElectivaInput.getNombre())
                .horas(pensumElectivaInput.getHoras())
                .creditos(pensumElectivaInput.getCreditos())
                .semestre(pensumElectivaInput.getSemestre())
                .build();
        return pensumElectivaRepository.save(pensumElectiva);
    }
    public boolean eliminarElectiva(Long id){
        var electivaDB = buscarElectiva(id);
        if (electivaDB == null) return false;
        pensumElectivaRepository.deleteById(id);
        return true;
    }
    /*
        CRUD PENSUM-ELECTIVA
    */

    /*
        MALLA
    */
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
    /*
        MALLA
    */
}
