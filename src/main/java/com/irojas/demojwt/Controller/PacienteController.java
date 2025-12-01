package com.irojas.demojwt.Controller;

import com.irojas.demojwt.model.Paciente;
import com.irojas.demojwt.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Crear paciente
    @PostMapping
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente) {
        Paciente creado = pacienteService.crearPaciente(paciente);
        return ResponseEntity.ok(creado);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obtenerPorId(id));
    }

    // Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(
            @PathVariable Long id,
            @RequestBody Paciente paciente
    ) {
        Paciente actualizado = pacienteService.actualizarPaciente(id, paciente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }

    // Asignar ORDEN a paciente (1:N)
    @PostMapping("/{pacienteId}/ordenes/{ordenId}")
    public ResponseEntity<Void> asignarOrdenAPaciente(
            @PathVariable Long pacienteId,
            @PathVariable Long ordenId
    ) {
        pacienteService.asignarOrdenAPaciente(pacienteId, ordenId);
        return ResponseEntity.ok().build();
    }
}
