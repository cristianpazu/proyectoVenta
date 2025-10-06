package com.example.proyectoVenta.stock.Entity;

import com.example.proyectoVenta.productos.Entity.Productos;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idStock;

    Integer cantidadStock;

    @OneToOne
    @JoinColumn(name = "productos_id")
    private Productos productos;

    public Stock() {
    }

    public Stock(Integer idStock, Integer cantidadStock, Productos productos) {
        this.idStock = idStock;
        this.cantidadStock = cantidadStock;
        this.productos = productos;
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
}
