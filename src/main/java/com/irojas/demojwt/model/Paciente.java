package com.irojas.demojwt.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

@Entity
@Table(name = "PACIENTE")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 20)
    private String rut;

    private LocalDate fechaNacimiento;

    @Column(length = 120)
    private String email;

    @Column(length = 20)
    private String telefono;

    // 1:N (1 Paciente tiene muchos Exámenes)
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Examen> examenes;

    // N:N (Muchos pacientes atendidos por muchos doctores)
    @ManyToMany
    @JoinTable(
            name = "PACIENTE_DOCTOR",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctores = new HashSet<>();

    // getters y setters
    public Set<Doctor> getDoctores() {
        return doctores;
    }

    public void setDoctores(Set<Doctor> doctores) {
        this.doctores = doctores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
    }
}
