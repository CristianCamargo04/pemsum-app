package com.ufps.ingsistemas.pensumapp.services;

import com.ufps.ingsistemas.pensumapp.entities.PensumEntity;
import com.ufps.ingsistemas.pensumapp.models.input.PensumInput;
import com.ufps.ingsistemas.pensumapp.repositories.PensumRepository;
import com.ufps.ingsistemas.pensumapp.vo.PensumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PensumService {
    @Autowired
    PensumRepository pensumRepository;

    public PensumEntity almacenarPensum(PensumInput pensumInput){
        var pensumEntity = PensumEntity.builder()
                .codigo(pensumInput.getCodigo())
                .url(pensumInput.getUrl())
                .pensumTerminado(pensumInput.isPensumTerminado())
                .mallaTerminada(pensumInput.isMallaTerminada())
                .build();

        return pensumRepository.save(pensumEntity);
    }

    public List<PensumEntity> listarPensums(){
        return (List<PensumEntity>) pensumRepository.findAll();
    }

    public List<PensumVO> listarPensumsTerminados(){ return pensumRepository.findAllPensumsTerminados(); }

    public List<PensumVO> listarPensumsNoTerminados(){ return pensumRepository.findAllPensumsNoTerminados(); }

    public PensumEntity encontrarPensum(String codigo){
        return pensumRepository.findById(codigo).orElse(null);
    }

    public boolean eliminarPensum(String codigo){
        var pensumDB = encontrarPensum(codigo);
        if (pensumDB == null) return false;
        pensumRepository.deleteById(codigo);
        return true;
    }
}
