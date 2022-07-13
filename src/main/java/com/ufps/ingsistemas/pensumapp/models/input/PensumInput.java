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
public class PensumInput {
    @NotEmpty(message="El codigo del pensum es obligatorio")
    private String codigo;

    private String url;
}
