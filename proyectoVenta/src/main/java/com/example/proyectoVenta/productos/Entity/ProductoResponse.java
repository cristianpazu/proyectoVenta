package com.example.proyectoVenta.productos.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductoResponse {
    private String nombre;

    private String referencia;

    private Integer precioCompra;

    private Integer precioVenta;

    private LocalDate fechaIngreso;

    private String observacion;



    public ProductoResponse(String nombre, String observacion, LocalDate fechaIngreso, Integer precioVenta, Integer precioCompra, String referencia) {
        this.nombre = nombre;
        this.observacion = observacion;
        this.fechaIngreso = fechaIngreso;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.referencia = referencia;
    }
}
