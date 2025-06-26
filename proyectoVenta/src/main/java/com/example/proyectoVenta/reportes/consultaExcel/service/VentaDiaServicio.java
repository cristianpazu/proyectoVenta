package com.example.proyectoVenta.reportes.consultaExcel.service;

import com.example.proyectoVenta.reportes.consultaExcel.dto.VentasDIA;

import java.time.LocalDate;
import java.util.List;

public interface VentaDiaServicio {

    List<Object[]> obtenerVentaDia(String fecha);
}
