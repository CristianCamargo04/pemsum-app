package com.ufps.ingsistemas.pensumapp.models.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PensumElectivaInput {
    private Long id;
    private String codPensum;
    private String nombre;
    private String horas;
    private String creditos;
    private String semestre;
}
