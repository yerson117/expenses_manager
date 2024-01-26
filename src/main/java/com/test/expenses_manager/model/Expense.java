package com.test.expenses_manager.model;

import com.test.expenses_manager.enums.ExpenseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "curp", nullable = false)
    private String curp;

    @Column(name = "rfc", nullable = false)
    private String rfc;

    @Column(name = "nombre_gasto", nullable = false)
    private String nombreDelGasto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private ExpenseStatus estado;

    @Column(name = "monto", nullable = false)
    private Double monto;

    public Expense(Long id, String nombre, String apellido, String telefono, String email, String curp, String rfc, String nombreDelGasto, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, ExpenseStatus estado, Double monto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.curp = curp;
        this.rfc = rfc;
        this.nombreDelGasto = nombreDelGasto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.monto = monto;
    }

    public Expense() {
    }
}
