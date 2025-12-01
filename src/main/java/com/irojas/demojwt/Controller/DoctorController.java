package com.irojas.demojwt.Controller;

import com.irojas.demojwt.model.Doctor;
import com.irojas.demojwt.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Crear doctor
    @PostMapping
    public ResponseEntity<Doctor> crearDoctor(@RequestBody Doctor doctor) {
        Doctor creado = doctorService.crearDoctor(doctor);
        return ResponseEntity.ok(creado);
    }

    // Listar todos los doctores
    @GetMapping
    public ResponseEntity<List<Doctor>> obtenerTodos() {
        return ResponseEntity.ok(doctorService.obtenerTodos());
    }

    // Obtener doctor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.obtenerPorId(id));
    }

    // Actualizar doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> actualizarDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor
    ) {
        Doctor actualizado = doctorService.actualizarDoctor(id, doctor);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDoctor(@PathVariable Long id) {
        doctorService.eliminarDoctor(id);
        return ResponseEntity.noContent().build();
    }
}