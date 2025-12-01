package com.irojas.demojwt.Controller;

import com.irojas.demojwt.model.Orden;
import com.irojas.demojwt.service.OrdenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class OrdenController {

    private final OrdenService doctorService;

    public OrdenController(OrdenService doctorService) {
        this.doctorService = doctorService;
    }

    // Crear doctor
    @PostMapping
    public ResponseEntity<Orden> crearDoctor(@RequestBody Orden doctor) {
        Orden creado = doctorService.crearDoctor(doctor);
        return ResponseEntity.ok(creado);
    }

    // Listar todos los doctores
    @GetMapping
    public ResponseEntity<List<Orden>> obtenerTodos() {
        return ResponseEntity.ok(doctorService.obtenerTodos());
    }

    // Obtener doctor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.obtenerPorId(id));
    }

    // Actualizar doctor
    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizarDoctor(
            @PathVariable Long id,
            @RequestBody Orden doctor
    ) {
        Orden actualizado = doctorService.actualizarDoctor(id, doctor);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDoctor(@PathVariable Long id) {
        doctorService.eliminarDoctor(id);
        return ResponseEntity.noContent().build();
    }
}