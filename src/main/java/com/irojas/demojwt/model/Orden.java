package com.irojas.demojwt.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ORDEN")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    private String numeroOrden; // Campo para almacenar el número de la orden

    private LocalDate fechaOrden; 

    @ManyToMany(mappedBy = "ordenes")
    private Set<Paciente> pacientes = new HashSet<>();

    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    // Getter y Setter para fechaOrden
    public LocalDate getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(LocalDate fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    // Getter y Setter para numeroOrden
    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }
}
