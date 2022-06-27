package com.ufps.ingsistemas.pensumapp.controllers;

import com.ufps.ingsistemas.pensumapp.models.output.MallaOutput;
import com.ufps.ingsistemas.pensumapp.services.MallaService;
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

    @GetMapping(value = "/{codPensum}")
    public ResponseEntity<MallaOutput> obtenerMalla(@PathVariable("codPensum") String codPensum){
        var malla = mallaService.obtenerMalla(codPensum);
        return ResponseEntity.ok(malla);
    }
    @GetMapping(value = "/materia")
    public ResponseEntity<List<MateriaPensumVO>> listarMaterias(){
        var materias = mallaService.listarMaterias();
        return ResponseEntity.ok(materias);
    }

    @GetMapping(value = "/electiva")
    public ResponseEntity<List<ElectivaPensumVO>> ListarElectivas(){
        var electivas = mallaService.listarElectivas();
        return ResponseEntity.ok(electivas);
    }

    @GetMapping(value = "/materia/{codPensum}/{semestre}")
    public ResponseEntity<List<MateriaMallaVo>> listarMateriasPorPensumYSemestre(@PathVariable("codPensum") String codPensum,@PathVariable("semestre") String semestre){
        var materias = mallaService.listarMateriasPorPensumYSemestre(codPensum,semestre);
        return ResponseEntity.ok(materias);
    }
}
