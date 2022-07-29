package com.ufps.ingsistemas.pensumapp.controllers;

import com.ufps.ingsistemas.pensumapp.entities.PensumElectivaEntity;
import com.ufps.ingsistemas.pensumapp.entities.PensumMateriaEntity;
import com.ufps.ingsistemas.pensumapp.models.input.MateriaInput;
import com.ufps.ingsistemas.pensumapp.models.input.PensumElectivaInput;
import com.ufps.ingsistemas.pensumapp.models.input.PensumMateriaInput;
import com.ufps.ingsistemas.pensumapp.models.output.MallaOutput;
import com.ufps.ingsistemas.pensumapp.services.MallaService;
import com.ufps.ingsistemas.pensumapp.services.PensumService;
import com.ufps.ingsistemas.pensumapp.vo.ElectivaPensumVO;
import com.ufps.ingsistemas.pensumapp.vo.MateriaMallaVo;
import com.ufps.ingsistemas.pensumapp.vo.MateriaPensumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/malla")
@CrossOrigin
public class MallaController {
    @Autowired
    MallaService mallaService;
    @Autowired
    PensumService pensumService;

    @GetMapping(value = "/{codPensum}")
    public ResponseEntity<MallaOutput> obtenerMalla(@PathVariable("codPensum") String codPensum){
        var pensumDB = pensumService.encontrarPensum(codPensum);
        if (null == pensumDB) return ResponseEntity.notFound().build();
        if (!pensumDB.isMallaTerminada()) return ResponseEntity.notFound().build();
        var malla = mallaService.obtenerMalla(codPensum);
        return ResponseEntity.ok(malla);
    }
    /*
        CRUD PENSUM-MATERIA
    */
    @GetMapping(value = "/materia")
    public ResponseEntity<List<MateriaPensumVO>> listarMaterias(){
        var materias = mallaService.listarMaterias();
        return ResponseEntity.ok(materias);
    }

    @PostMapping(value = "/materia")
    public ResponseEntity<PensumMateriaEntity> listarMaterias(@RequestBody PensumMateriaInput pensumMateriaInput){
        var materiaCreada = mallaService.almacenarMateria(pensumMateriaInput);
        return ResponseEntity.ok(materiaCreada);
    }

    @GetMapping(value = "/materia/{id}")
    public ResponseEntity<Object> buscarMateria(@PathVariable("id") Long id){
        var materiaDB = mallaService.buscarMateria(id);
        if(null == materiaDB) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(materiaDB);
    }

    @DeleteMapping(value = "/materia/{id}")
    public ResponseEntity<Object> eliminarMateria(@PathVariable("id") Long id){
        var materiaEliminado = mallaService.eliminarMateria(id);
        return materiaEliminado?
                ResponseEntity.ok(materiaEliminado):
                ResponseEntity.notFound().build();
    }
    /*
        CRUD PENSUM-MATERIA
    */

    /*
        CRUD PENSUM-ELECTIVA
    */
    @GetMapping(value = "/electiva")
    public ResponseEntity<List<ElectivaPensumVO>> ListarElectivas(){
        var electivas = mallaService.listarElectivas();
        return ResponseEntity.ok(electivas);
    }

    @PostMapping(value = "/electiva")
    public ResponseEntity<PensumElectivaEntity> listarMaterias(@RequestBody PensumElectivaInput pensumElectivaInput){
        var electivaCreada = mallaService.almacenarElectiva(pensumElectivaInput);
        return ResponseEntity.ok(electivaCreada);
    }

    @GetMapping(value = "/electiva/{id}")
    public ResponseEntity<Object> buscarElectiva(@PathVariable("id") Long id){
        var electivaDB = mallaService.buscarElectiva(id);
        if(null == electivaDB) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(electivaDB);
    }

    @DeleteMapping(value = "/electiva/{id}")
    public ResponseEntity<Object> eliminarElectiva(@PathVariable("id") Long id){
        var electivaEliminado = mallaService.eliminarElectiva(id);
        return electivaEliminado?
                ResponseEntity.ok(electivaEliminado):
                ResponseEntity.notFound().build();
    }
    /*
        CRUD PENSUM-ELECTIVA
    */
    @GetMapping(value = "/materia/{codPensum}/{semestre}")
    public ResponseEntity<List<MateriaMallaVo>> listarMateriasPorPensumYSemestre(@PathVariable("codPensum") String codPensum,@PathVariable("semestre") String semestre){
        var materias = mallaService.listarMateriasPorPensumYSemestre(codPensum,semestre);
        return ResponseEntity.ok(materias);
    }

    @GetMapping(value = "/prerrequisitos/{codPensum}/{semestre}")
    public ResponseEntity<List<MateriaPensumVO>> listarPrerrequisitos(@PathVariable("codPensum") String codPensum,@PathVariable("semestre") Integer semestre){
        var materias = mallaService.listarPrerrequisitos(codPensum,semestre);
        return ResponseEntity.ok(materias);
    }
}
