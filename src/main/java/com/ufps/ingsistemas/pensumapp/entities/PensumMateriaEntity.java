package com.ufps.ingsistemas.pensumapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pensum_materia")
public class PensumMateriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_pensum")
    private PensumEntity pensumEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_materia")
    private MateriaEntity materiaEntity;

    private String codigo;
    private String semestre;
    private boolean electiva;
    private String codPerrequisito;
    private String crePerrequisito;
}
