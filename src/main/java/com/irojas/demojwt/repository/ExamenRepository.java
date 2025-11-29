package com.irojas.demojwt.repository;

import com.irojas.demojwt.model.Examen;
import com.irojas.demojwt.model.Examen.EstadoExamen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    List<Examen> findByPacienteId(Long pacienteId);

    List<Examen> findByEstadoAndFechaSolicitudBetween(
            EstadoExamen estado,
            LocalDate desde,
            LocalDate hasta
    );
}
