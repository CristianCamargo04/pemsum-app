package com.ufps.ingsistemas.pensumapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufps.ingsistemas.pensumapp.entities.PensumEntity;
import com.ufps.ingsistemas.pensumapp.models.input.MateriaInput;
import com.ufps.ingsistemas.pensumapp.models.input.PensumInput;
import com.ufps.ingsistemas.pensumapp.services.PensumService;
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
@RequestMapping("/api/pensum")
@CrossOrigin
public class PensumController {
    @Autowired
    PensumService pensumService;

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
    public ResponseEntity<Object> crearPensum(@Valid @RequestBody PensumInput pensumInput, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        var pensumCreado = pensumService.almacenarPensum(pensumInput);
        return ResponseEntity.ok(pensumCreado);
    }

    @GetMapping
    public ResponseEntity<List<PensumEntity>> listarPensums(){
        var pensums = pensumService.listarPensums();
        if(pensums.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(pensums);
    }

    @GetMapping( value = "/{codigo}")
    public ResponseEntity<Object> encontrarPensum(@PathVariable("codigo") String codigo){
        var pensumDB = pensumService.encontrarPensum(codigo);
        if (null == pensumDB) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pensumDB);
    }

    @DeleteMapping( value = "/{codigo}")
    public ResponseEntity<Object> eliminarPensum(@PathVariable("codigo") String codigo){
        var pensumEliminado = pensumService.eliminarPensum(codigo);
        return pensumEliminado?
                ResponseEntity.ok(pensumEliminado):
                ResponseEntity.notFound().build();
    }
}
