package com.ufps.ingsistemas.pensumapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="materias")
public class MateriaEntity {
    @Id
    private String codigo;
    private String nombre;
    private String horas;
    private String creditos;
    private String semestre;
    private boolean electiva;
    private String codPerrequisito;
    private String crePerrequisito;
}
