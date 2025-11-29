package com.irojas.demojwt.Clase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaseResponse {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String categoriaNombre; // Nombre de la categoría
}
