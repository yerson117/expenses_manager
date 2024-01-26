package com.test.expenses_manager.dto;

import com.test.expenses_manager.enums.ExpenseStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
public class ExpenseDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre solo debe contener caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El apellido solo debe contener caracteres")
    private String apellido;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono solo debe contener números")
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "El CURP es obligatorio")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El CURP debe ser alfanumérico")
    private String curp;

    @NotBlank(message = "El RFC es obligatorio")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El RFC debe ser alfanumérico")
    private String rfc;

    @NotBlank(message = "El nombre del gasto es obligatorio")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre del gasto solo debe contener caracteres")
    private String nombreDelGasto;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "La descripción solo debe contener caracteres")
    private String descripcion = null;

    private LocalDate fechaInicio = null;

    private LocalDate fechaFin = null;

    private ExpenseStatus estado = null;

    private Double monto = null;

    public ExpenseDTO(Long id, String nombre, String apellido, String telefono, String email, String curp, String rfc, String nombreDelGasto, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, ExpenseStatus estado, Double monto) {
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

    public ExpenseDTO() {
    }
}
