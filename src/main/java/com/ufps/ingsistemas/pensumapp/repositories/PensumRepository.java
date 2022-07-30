package com.ufps.ingsistemas.pensumapp.repositories;

import com.ufps.ingsistemas.pensumapp.entities.PensumEntity;
import com.ufps.ingsistemas.pensumapp.vo.PensumVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PensumRepository extends CrudRepository<PensumEntity,String> {
    @Query(value = "SELECT codigo, url, malla_terminada as mallaTerminada, pensum_terminado as pensumTerminado FROM pensums WHERE pensum_terminado AND malla_terminada",
            nativeQuery = true)
    List<PensumVO> findAllPensumsTerminados();

    @Query(value = "SELECT codigo, url, malla_terminada as mallaTerminada, pensum_terminado as pensumTerminado FROM pensums WHERE !pensum_terminado",
            nativeQuery = true)
    List<PensumVO> findAllPensumsNoTerminados();
}
