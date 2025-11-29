package com.irojas.demojwt.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EXAMEN")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String tipoExamen;

    private LocalDate fechaSolicitud;
    private LocalDate fechaResultado;

    @Column(length = 500)
    private String resultado;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EstadoExamen estado;

    // Muchos exámenes pertenecen a un paciente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    public enum EstadoExamen {
        PENDIENTE,
        PROCESADO,
        ENTREGADO
    }

    // getters y setters
    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDate getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDate fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoExamen getEstado() {
        return estado;
    }

    public void setEstado(EstadoExamen estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
