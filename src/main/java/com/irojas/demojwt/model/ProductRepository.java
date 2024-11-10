package com.irojas.demojwt.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    // Ejemplo de método personalizado para buscar un producto por nombre
    Optional<Product> findByNombre(String nombre);


}
