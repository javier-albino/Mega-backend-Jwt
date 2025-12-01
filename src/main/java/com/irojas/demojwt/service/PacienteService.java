package com.irojas.demojwt.service;

import com.irojas.demojwt.model.Orden;
import com.irojas.demojwt.model.Paciente;
import com.irojas.demojwt.repository.OrdenRepository;
import com.irojas.demojwt.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final OrdenRepository ordenRepository;

    public PacienteService(PacienteRepository pacienteRepository,
                           OrdenRepository ordenRepository) {
        this.pacienteRepository = pacienteRepository;
        this.ordenRepository = ordenRepository;
    }

    @Transactional
    public Paciente crearPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new EntityNotFoundException("Paciente no puede ser nulo");
        }

        // Si el paciente viene con órdenes, seteamos la relación inversa
        if (paciente.getOrdenes() != null) {
            for (Orden orden : paciente.getOrdenes()) {
                orden.setPaciente(paciente);
            }
        }

        return pacienteRepository.save(paciente);
    }

    /**
     * Asignar una Orden existente a un Paciente existente
     */
    @Transactional
    public void asignarOrdenAPaciente(Long pacienteId, Long ordenId) {
        if (pacienteId == null || ordenId == null) {
            throw new EntityNotFoundException("IDs no pueden ser nulos");
        }

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada"));

        // Relación dueño: Orden → Paciente
        orden.setPaciente(paciente);
        ordenRepository.save(orden);

        // Opcional: mantener la colección en memoria coherente
        if (paciente.getOrdenes() != null && !paciente.getOrdenes().contains(orden)) {
            paciente.getOrdenes().add(orden);
        }
    }

    @Transactional(readOnly = true)
    public Paciente obtenerPorId(Long id) {
        if (id == null) {
            throw new EntityNotFoundException("Id no puede ser nulo");
        }
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
    }

    @Transactional
    public Paciente actualizarPaciente(Long id, Paciente pacienteActualizado) {
        Paciente paciente = obtenerPorId(id);
        paciente.setNombre(pacienteActualizado.getNombre());
        paciente.setRut(pacienteActualizado.getRut());
        paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        paciente.setEmail(pacienteActualizado.getEmail());

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public void eliminarPaciente(Long id) {
        Paciente paciente = obtenerPorId(id);
        if (paciente != null) {
            pacienteRepository.delete(paciente);
        }
    }
}
