package com.ufps.ingsistemas.pensumapp.models.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class  MateriaInput {
    @NotEmpty(message="El nombre de la materia es obligatorio")
    private String nombre;

    @NotEmpty(message="Las horas de la materia son obligatorias")
    private String horas;

    @NotEmpty(message="Los creditos de la materia son obligatorios")
    private String creditos;

    private byte[] microcurriculo;
}
