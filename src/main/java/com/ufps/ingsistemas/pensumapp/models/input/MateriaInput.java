package com.ufps.ingsistemas.pensumapp.models.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class  MateriaInput {
    @NotEmpty(message="El código de la materia es obligatorio")
    @NumberFormat
    @Size(min = 7, max = 7, message="El código de la materia tiene que tener entre 6 caracteres")
    private String codigo;

    @NotEmpty(message="El nombre de la materia es obligatorio")
    private String nombre;

    @NotEmpty(message="Las horas de la materia son obligatorias")
    private String horas;

    @NotEmpty(message="Los creditos de la materia son obligatorios")
    private String creditos;

    @NotEmpty(message="El semestre de la materia es obligatorio")
    private String semestre;

    private String codPerrequisito;

    private String crePerrequisito;
}
