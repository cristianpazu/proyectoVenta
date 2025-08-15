package com.example.proyectoVenta.productos.Repository;

import com.example.proyectoVenta.productos.Entity.ProductoResponse;
import com.example.proyectoVenta.productos.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {

    @Query(value = "SELECT pr.nombre, pr.fecha_ingreso FROM productos  pr " +
            "INNER JOIN producto_categoria pc on pr.id_productos = pc.producto_id " +
            "LEFT JOIN stock s on pr.id_productos = s.productos_id", nativeQuery = true)
    List<ProductoResponse> findAllByProductos();

}
