package com.irojas.demojwt.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // No es necesario agregar métodos adicionales, ya que JpaRepository proporciona los métodos CRUD .
}
