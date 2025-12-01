package com.irojas.demojwt.service;

import com.irojas.demojwt.model.Doctor;
import com.irojas.demojwt.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Crear doctor
    public Doctor crearDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor no puede ser nulo");
        }
        return doctorRepository.save(doctor);
    }

    // Obtener todos los doctores
    public List<Doctor> obtenerTodos() {
        return doctorRepository.findAll();
    }

    // Obtener doctor por ID
    public Doctor obtenerPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
    }

    // Actualizar doctor
    public Doctor actualizarDoctor(Long id, Doctor doctorActualizado) {
        Doctor doctor = obtenerPorId(id);
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