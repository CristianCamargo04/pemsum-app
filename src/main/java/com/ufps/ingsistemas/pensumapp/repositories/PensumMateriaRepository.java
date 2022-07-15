package com.ufps.ingsistemas.pensumapp.repositories;

import com.ufps.ingsistemas.pensumapp.entities.PensumMateriaEntity;
import com.ufps.ingsistemas.pensumapp.vo.ElectivaMallaVO;
import com.ufps.ingsistemas.pensumapp.vo.MateriaMallaVo;
import com.ufps.ingsistemas.pensumapp.vo.MateriaPensumVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PensumMateriaRepository extends CrudRepository<PensumMateriaEntity,Long> {

    @Query(value = "SELECT pm.id as idMateriaPensum, pm.cod_pensum as codPensum, " +
            "m.id as idMateria, pm.codigo, m.nombre, m.horas, m.creditos, pm.semestre, pm.electiva, " +
            "pm.cod_perrequisito as codPerrequisito, pm.cre_perrequisito as crePerrequisito FROM pensum_materia pm INNER JOIN materias m " +
            "ON pm.id_materia = m.id", nativeQuery = true)
    List<MateriaPensumVO> findAllMaterias();

    @Query(value = "SELECT pm.id as idMateriaPensum, pm.cod_pensum as codPensum, " +
            "m.id as idMateria, pm.codigo, m.nombre, m.horas, m.creditos, pm.semestre, pm.electiva, " +
            "pm.cod_perrequisito as codPerrequisito, pm.cre_perrequisito as crePerrequisito FROM pensum_materia pm INNER JOIN materias m " +
            "ON pm.id_materia = m.id WHERE pm.cod_pensum = :codPensum and pm.semestre < :semestre", nativeQuery = true)
    List<MateriaPensumVO> findAllMateriasPrerrequisitos(String codPensum, Integer semestre);

    @Query(value = "SELECT pm.id, m.id as idMateria, pm.codigo, m.nombre, m.horas, m.creditos, pm.semestre, pm.cod_perrequisito as codPerrequisito, pm.cre_perrequisito as crePerrequisito" +
            "    FROM pensum_materia pm INNER JOIN materias m  ON pm.id_materia = m.id " +
            "    WHERE pm.cod_pensum = :codPensum AND pm.semestre = :semestre AND pm.electiva= 0 " +
            "    UNION " +
            "    SELECT pe.id, null as idMateria, null as codigo, pe.nombre, pe.horas, pe.creditos,pe.semestre, null as cod_perrequisito, null as cre_perrequisito " +
            "    FROM pensum_electiva pe " +
            "    WHERE pe.cod_pensum = :codPensum AND pe.semestre = :semestre", nativeQuery = true)
    List<MateriaMallaVo> findAllMateriasByPensumBySemestre(String codPensum, String semestre);

    @Query(value = "SELECT pm.id, m.id as idMateria, pm.codigo, m.nombre, m.horas, m.creditos, pm.semestre, pm.cod_perrequisito as codPerrequisito, pm.cre_perrequisito as crePerrequisito " +
            "FROM pensum_materia pm INNER JOIN materias m  ON pm.id_materia = m.id " +
            "WHERE pm.cod_pensum = :codPensum AND pm.electiva = 1 AND m.creditos = :creditos", nativeQuery = true)
    List<ElectivaMallaVO> findAllElectivasByPensumByCreditos(String codPensum, String creditos);

}
