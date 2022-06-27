package com.ufps.ingsistemas.pensumapp.repositories;

import com.ufps.ingsistemas.pensumapp.entities.PensumElectivaEntity;
import com.ufps.ingsistemas.pensumapp.vo.ElectivaPensumVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PensumElectivaRepository extends CrudRepository<PensumElectivaEntity,Long> {
    @Query(value = "SELECT pe.id as idElectivaPensum, pe.creditos, pe.horas,pe.nombre,pe.semestre,pe.cod_pensum as codPensum " +
            "FROM pensum_electiva pe", nativeQuery = true)
    List<ElectivaPensumVO> findAllElectivas();
}
