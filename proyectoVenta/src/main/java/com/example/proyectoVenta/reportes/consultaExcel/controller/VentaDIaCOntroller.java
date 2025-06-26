package com.example.proyectoVenta.reportes.consultaExcel.controller;

import com.example.proyectoVenta.categoria.Entity.Categoria;
import com.example.proyectoVenta.reportes.consultaExcel.service.Impl.VentaDiaImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exec")
public class VentaDIaCOntroller {

    @Autowired
    private VentaDiaImpl ventaDiaServicio;

    @GetMapping("/exec1/{fechas}")
    public ResponseEntity<Object> findByIca(@PathVariable String fechas){
        List<Object[]> categoriaFindById = ventaDiaServicio.obtenerVentaDia(fechas);
        return  ResponseEntity.status(HttpStatus.OK).body(categoriaFindById);

    }
}
