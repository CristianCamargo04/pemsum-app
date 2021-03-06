package com.ufps.ingsistemas.pensumapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufps.ingsistemas.pensumapp.entities.MateriaEntity;
import com.ufps.ingsistemas.pensumapp.models.input.MateriaInput;
import com.ufps.ingsistemas.pensumapp.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/materia")
@CrossOrigin
public class MateriaController {
    @Autowired
    MateriaService materiaService;

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @PostMapping
    public ResponseEntity<Object> crearMateria(@Valid @RequestBody MateriaInput materiaInput, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        var materiaCreada = materiaService.almacenarMateria(materiaInput);
        return ResponseEntity.ok(materiaCreada);
    }

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> listarMateria(){
        var materias = materiaService.listarMaterias();
        if (materias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(materias);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> buscarMateria(@PathVariable("id") Long id){
        var materiaDB = materiaService.buscarMateria(id);
        if(null == materiaDB) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(materiaDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> eliminarMateria(@PathVariable("id") Long id){
        var materiaEliminado = materiaService.eliminarMateria(id);
        return materiaEliminado?
                ResponseEntity.ok(materiaEliminado):
                ResponseEntity.notFound().build();
    }

}
