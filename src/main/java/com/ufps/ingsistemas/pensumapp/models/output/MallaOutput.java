package com.ufps.ingsistemas.pensumapp.models.output;

import com.ufps.ingsistemas.pensumapp.vo.ElectivaMallaVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MallaOutput {
    private String codPensum;
    private List<SemestreOutput> semestres;
    private List<ElectivaMallaVO> profesionales;
    private List<ElectivaMallaVO> sociohumanisticas;
}
