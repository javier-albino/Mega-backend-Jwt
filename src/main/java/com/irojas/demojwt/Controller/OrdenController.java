package com.irojas.demojwt.Controller;

import com.irojas.demojwt.model.Orden;
import com.irojas.demojwt.service.OrdenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    // Crear doctor
    @PostMapping
    public ResponseEntity<Orden> crearOrden(@RequestBody Orden orden) {
        Orden creado = ordenService.crearOrden(orden);
        return ResponseEntity.ok(creado);
    }

    // Listar todos los doctores
    @GetMapping
    public ResponseEntity<List<Orden>> obtenerTodos() {
        return ResponseEntity.ok(ordenService.obtenerTodas());
    }

    // Obtener doctor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.obtenerPorId(id));
    }

 
}