package com.ufps.ingsistemas.pensumapp.models.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PensumMateriaInput {
    private Long id;
    private Long idMateria;
    private String codPensum;
    private String codigo;
    private String semestre;
    private boolean electiva;
    private String codPerrequisito;
    private String crePerrequisito;
}
