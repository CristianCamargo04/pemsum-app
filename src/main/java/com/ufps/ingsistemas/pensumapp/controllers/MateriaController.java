package com.ufps.ingsistemas.pensumapp.controllers;

import com.ufps.ingsistemas.pensumapp.entities.MateriaEntity;
import com.ufps.ingsistemas.pensumapp.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/materia")
@CrossOrigin
public class MateriaController {
    @Autowired
    MateriaService materiaService;

    @PostMapping
    public ResponseEntity<Object> crearMateria(@RequestBody MateriaEntity materiaEntity){
        var materiaCreada = materiaService.almacenarMateria(materiaEntity);
        return ResponseEntity.ok(materiaCreada);
    }

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> listarMateria(){
        var materias = materiaService.listarMaterias();
        return ResponseEntity.ok(materias);
    }

}
