package com.irojas.demojwt.service;

import com.irojas.demojwt.model.Examen;
import com.irojas.demojwt.model.Paciente;
import com.irojas.demojwt.repository.ExamenRepository;
import com.irojas.demojwt.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class ExamenService {

    private final ExamenRepository examenRepository;
    private final PacienteRepository pacienteRepository;

    public ExamenService(ExamenRepository examenRepository,
                         PacienteRepository pacienteRepository) {
        this.examenRepository = examenRepository;
        this.pacienteRepository = pacienteRepository;
    }
    @Transactional
    public Examen crearExamen(Long pacienteId, Examen examen) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));

        examen.setPaciente(paciente);
        examen.setFechaSolicitud(LocalDate.now());
        examen.setEstado(Examen.EstadoExamen.PENDIENTE);

        return examenRepository.save(examen);
    }
}
