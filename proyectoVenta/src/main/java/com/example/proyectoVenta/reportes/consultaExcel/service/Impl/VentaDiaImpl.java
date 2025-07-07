package com.example.proyectoVenta.reportes.consultaExcel.service.Impl;

import com.example.proyectoVenta.reportes.consultaExcel.dto.VentasDIA;
import com.example.proyectoVenta.reportes.consultaExcel.repository.VentaDiaRepository;
import com.example.proyectoVenta.reportes.consultaExcel.service.VentaDiaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Service
public class VentaDiaImpl implements VentaDiaServicio {

    @Autowired
    VentaDiaRepository ventaDiaRepository;

    @Override
    public List<Object[]> obtenerVentaDia(String fecha) {
        try{

            //log.info("Fecha enviada a consulta: {}", fecha);

            System.out.println("fecha = " + fecha);
            List<Object[]> venta = ventaDiaRepository.obtenerVentaDia(fecha);
            System.out.println("venta = " + venta.size());

            for (Object[] fila : venta) {
                System.out.println("Fila: " + Arrays.toString(fila));
            }
            return venta;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
}
