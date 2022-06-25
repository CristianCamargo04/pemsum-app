package com.ufps.ingsistemas.pensumapp.controllers;

import com.ufps.ingsistemas.pensumapp.models.output.SemestreOutput;
import com.ufps.ingsistemas.pensumapp.services.SemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/semestre")
public class SemestreController {
    @Autowired
    SemestreService semestreService;

    @GetMapping
    public ResponseEntity<List<SemestreOutput>> listarSemestres(){
        var semestres = semestreService.listarSemestres();
        return ResponseEntity.ok(semestres);
    }
}
