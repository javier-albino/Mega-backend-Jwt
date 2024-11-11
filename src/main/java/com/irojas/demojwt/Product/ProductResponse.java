package com.irojas.demojwt.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String categoriaNombre; // Nombre de la categoría
}
