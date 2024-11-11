package com.irojas.demojwt.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String nombre;
    private String descripcion;
    private Integer categoriaId; // ID de la categoría
}

