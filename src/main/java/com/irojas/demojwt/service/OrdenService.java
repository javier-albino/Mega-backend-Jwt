package com.irojas.demojwt.service;

import com.irojas.demojwt.model.Orden;
import com.irojas.demojwt.repository.OrdenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {

    private final OrdenRepository ordenRepository;

    public OrdenService(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    // Crear orden
    public Orden crearOrden(Orden orden) {
        if (orden == null) {
            throw new IllegalArgumentException("Orden no puede ser nula");
        }
        return ordenRepository.save(orden);
    }

    // Obtener todas las órdenes
    public List<Orden> obtenerTodas() {
        return ordenRepository.findAll();
    }

    // Obtener orden por ID
    public Orden obtenerPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Optional<Orden> orden = ordenRepository.findById(id);
        return orden.orElseThrow(() -> new RuntimeException("Orden no encontrada"));
    }

    // Actualizar orden
    public Orden actualizarOrden(Long id, Orden ordenActualizada) {
        Orden orden = obtenerPorId(id);
        orden.setNumeroOrden(ordenActualizada.getNumeroOrden());
        return ordenRepository.save(orden);
    }

    // Eliminar orden
    public void eliminarOrden(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        ordenRepository.deleteById(id);
    }
}