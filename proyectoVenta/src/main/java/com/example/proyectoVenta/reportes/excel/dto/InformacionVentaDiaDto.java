package com.example.proyectoVenta.reportes.excel.dto;

import com.alibaba.excel.annotation.ExcelProperty;

import java.time.LocalDate;

public class InformacionVentaDiaDto {
    @ExcelProperty("Nombre")
    private String nombre;

    @ExcelProperty("fecha")
    private String fecha;

    @ExcelProperty("PrecioVenta")
    private Integer precioVenta;

    @ExcelProperty("PrecioCompra")
    private Integer precioCompra;

    @ExcelProperty("GananciasProducto")
    private Integer gananciasProducto;

    @ExcelProperty("PorcentajeGanancia")
    private Integer porcentajeGanancia;

    @ExcelProperty("cantidadProductoVenta")
    private Integer cantidadProducto;

    @ExcelProperty("total")
    private Integer total;



    public InformacionVentaDiaDto(
            String nombre,
            String fecha ,
            Integer precioVenta,
            Integer precioCompra,
            Integer gananciasProducto,
            Integer porcentajeGanancia,
            Integer cantidadProductoVenta,
            Integer total ) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.gananciasProducto = gananciasProducto;
        this.porcentajeGanancia = porcentajeGanancia;
        this.cantidadProducto = cantidadProductoVenta;
        this.total = total;

    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Integer precioCompra) {
        this.precioCompra = precioCompra;
    }


    public Integer getGananciasProducto() {
        return gananciasProducto;
    }

    public void setGananciasProducto(Integer gananciasProducto) {
        this.gananciasProducto = gananciasProducto;
    }

    public Integer getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(Integer porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }


    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
