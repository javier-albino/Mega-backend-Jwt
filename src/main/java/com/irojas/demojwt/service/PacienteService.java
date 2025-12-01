package com.irojas.demojwt.service;

import com.irojas.demojwt.model.Doctor;
import com.irojas.demojwt.model.Paciente;
import com.irojas.demojwt.repository.DoctorRepository;
import com.irojas.demojwt.repository.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final DoctorRepository doctorRepository;

    public PacienteService(PacienteRepository pacienteRepository,
                           DoctorRepository doctorRepository) {
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public Paciente crearPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new EntityNotFoundException("Paciente no puede ser nulo");
        }
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public void asignarDoctorAPaciente(Long pacienteId, Long doctorId) {
        if (pacienteId == null || doctorId == null) {
            throw new EntityNotFoundException("IDs no pueden ser nulos");
        }
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor no encontrado"));

        paciente.getDoctores().add(doctor);
        doctor.getPacientes().add(paciente);
        // gracias al @Transactional y al mapeo ManyToMany, se persiste la relación
    }

    @Transactional(readOnly = true)
    public Paciente obtenerPorId(Long id) {
        if (id == null) {
            throw new EntityNotFoundException("Id no puede ser nulo");
        }
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
    }

   /*  @Transactional(readOnly = true)
    public List<Paciente> obtenerTodos() {
        return pacienteRepository.findAll();
    }*/

    @Transactional
    public Paciente actualizarPaciente(Long id, Paciente pacienteActualizado) {
        Paciente paciente = obtenerPorId(id);
        paciente.setNombre(pacienteActualizado.getNombre());
        paciente.setRut(pacienteActualizado.getRut());
        paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
        paciente.setEmail(pacienteActualizado.getEmail());
        paciente.setTelefono(pacienteActualizado.getTelefono());
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
