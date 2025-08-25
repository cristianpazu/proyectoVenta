package com.example.proyectoVenta.productos.Repository;

import com.example.proyectoVenta.productos.Entity.ProductoResponse;
import com.example.proyectoVenta.productos.Entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer> {

    @Query(value = """
    SELECT 
        p.id_productos,
        p.nombre,
        p.referencia,
        p.precio_venta,
        p.fecha_ingreso,
        s.cantidad_stock,
        p.observacion,
        STRING_AGG(c.nombre_categoria, ',') AS categoriasConcat
    FROM productos p
    LEFT JOIN stock s ON p.id_productos = s.productos_id
    LEFT JOIN producto_categoria pc ON pc.producto_id = p.id_productos
    LEFT JOIN categoria c ON c.id_categoria = pc.categoria_id
    GROUP BY p.id_productos, s.cantidad_stock
    """, nativeQuery = true)
    List<ProductoResponse> findAllByProductos();

}
