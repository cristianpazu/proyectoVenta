package com.example.proyectoVenta.reportes.consultaExcel.dto;


public class VentasDIA {


    String nombre;

    String fecha;

    Integer total;





    public VentasDIA(Integer total, String fecha, String nombre) {
        this.total = total;
        this.fecha = fecha;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


}
