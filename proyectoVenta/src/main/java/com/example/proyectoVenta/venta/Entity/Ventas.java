package com.example.proyectoVenta.venta.Entity;

import com.example.proyectoVenta.productos.Entity.Productos;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter

@Entity

public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;

    private Integer total;

    private String cliente;

    @OneToMany(mappedBy = "ventas", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DetalleVenta> detalles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "producto_pedidos",
            joinColumns = @JoinColumn(
                    name = "producto_id"
            ), inverseJoinColumns = @JoinColumn(name = "venta_id")


    )
    Set<Productos> productos = new HashSet<>();

    public Ventas() {
    }

    public Ventas(Integer id, Set<Productos> productos, List<DetalleVenta> detalles, Integer total, LocalDate fecha, String cliente) {
        this.id = id;
        this.productos = productos;
        this.detalles = detalles;
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Productos> getProductos() {
        return productos;
    }

    public void setProductos(Set<Productos> productos) {
        this.productos = productos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    /*
    public Ventas() {
    }

    public Ventas(Integer id, LocalDate fecha, Integer total, List<DetalleVenta> detalles) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.detalles = detalles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    } */
}
