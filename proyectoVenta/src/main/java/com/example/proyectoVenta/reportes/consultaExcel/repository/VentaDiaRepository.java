package com.example.proyectoVenta.reportes.consultaExcel.repository;

import com.example.proyectoVenta.reportes.consultaExcel.dto.VentasDIA;
import com.example.proyectoVenta.venta.Entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaDiaRepository extends JpaRepository<Ventas, Integer> {

@Query(value = "SELECT p.nombre, v.fecha, v.total " +
        "FROM ventas v " +
        "INNER JOIN detalle_venta dv ON v.id = dv.ventas_id " +
        "INNER JOIN productos p ON p.id_productos = dv.productos_id_productos " +
        "WHERE v.fecha = CAST(:fechas AS DATE) " +
        "ORDER BY v.fecha", nativeQuery = true)

    List<Object[]> obtenerVentaDia(@Param("fechas")String fechas);

}
