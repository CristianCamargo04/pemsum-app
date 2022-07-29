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
@Table(name="pensums")
public class PensumEntity {
    @Id
    private String codigo;
    private String url;
    private boolean pensumTerminado;
    private boolean mallaTerminada;
}
