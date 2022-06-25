package com.ufps.ingsistemas.pensumapp.models.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SemestreOutput {
    private String semestre;
    private List<Object> materias;
}
