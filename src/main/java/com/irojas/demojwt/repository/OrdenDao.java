package com.irojas.demojwt.repository;

import com.irojas.demojwt.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDao extends JpaRepository<Orden, Long> {

    @Query("SELECT o FROM Orden o ORDER BY o.fechaCreacion ASC LIMIT 1")
    Orden findOldestOrden();

    @Query("SELECT o FROM Orden o WHERE o.paciente.fechaNacimiento = (SELECT MIN(p.fechaNacimiento) FROM Paciente p) ORDER BY o.fechaCreacion DESC LIMIT 1")
    Orden findNewestOrdenFromOldestPaciente();
}