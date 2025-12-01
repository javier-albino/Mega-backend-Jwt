package com.irojas.demojwt.service;

import com.irojas.demojwt.model.Orden;
import com.irojas.demojwt.repository.OrdenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {

    private final OrdenRepository doctorRepository;

    public OrdenService(OrdenRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Crear doctor
    public Orden crearDoctor(Orden doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor no puede ser nulo");
        }
        return doctorRepository.save(doctor);
    }

    // Obtener todos los doctores
    public List<Orden> obtenerTodos() {
        return doctorRepository.findAll();
    }

    // Obtener doctor por ID
    public Orden obtenerPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<Orden> doctor = doctorRepository.findById(id);
        return doctor.orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
    }

    // Actualizar doctor
    public Orden actualizarDoctor(Long id, Orden doctorActualizado) {
        Orden doctor = obtenerPorId(id);
        doctor.setNombre(doctorActualizado.getNombre());
        doctor.setEspecialidad(doctorActualizado.getEspecialidad());
        doctor.setEmail(doctorActualizado.getEmail());
        return doctorRepository.save(doctor);
    }

    // Eliminar doctor
    public void eliminarDoctor(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        doctorRepository.deleteById(id);
    }
}