package com.ufps.ingsistemas.pensumapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name="pensum_electiva")
public class PensumElectivaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_pensum")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PensumEntity pensumEntity;

    private String nombre;
    private String horas;
    private String creditos;
    private String semestre;
}
