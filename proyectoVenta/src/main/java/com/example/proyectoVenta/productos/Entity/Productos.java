package com.example.proyectoVenta.productos.Entity;


import com.example.proyectoVenta.categoria.Entity.Categoria;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductos;


    private String nombre;

    private String referencia;

    private Integer precioVenta;

    private LocalDate fechaIngreso;

    private String observacion;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "producto_categoria",
            joinColumns = @JoinColumn(
                    name = "producto_id"
            ), inverseJoinColumns = @JoinColumn(name = "categoria_id")


    )
    Set<Categoria> categorias = new HashSet<>();

    public Productos() {
    }

    public Productos(Integer idProductos, String nombre, String referencia, Integer precioVenta, LocalDate fechaIngreso, String observacion, Set<Categoria> categorias) {
        this.idProductos = idProductos;
        this.nombre = nombre;
        this.referencia = referencia;
        this.precioVenta = precioVenta;
        this.fechaIngreso = fechaIngreso;
        this.observacion = observacion;
        this.categorias = categorias;
    }

    public Integer getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Integer idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
}
