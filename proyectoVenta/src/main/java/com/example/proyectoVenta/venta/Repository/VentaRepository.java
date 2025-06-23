package com.example.proyectoVenta.venta.Repository;

import com.example.proyectoVenta.venta.Entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Ventas,Integer> {
}
