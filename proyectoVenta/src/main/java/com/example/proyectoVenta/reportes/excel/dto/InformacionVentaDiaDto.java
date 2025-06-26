package com.example.proyectoVenta.reportes.excel.dto;

import com.alibaba.excel.annotation.ExcelProperty;

import java.time.LocalDate;

public class InformacionVentaDiaDto {
    @ExcelProperty("Nombre")
    private String nombre;

    @ExcelProperty("fecha")
    private String fecha;

    @ExcelProperty("total")
    private Integer total;

    public InformacionVentaDiaDto(String nombre, String fecha ,Integer total) {
        this.nombre = nombre;
        this.fecha = fecha;
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
}
