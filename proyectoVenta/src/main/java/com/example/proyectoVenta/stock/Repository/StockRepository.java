package com.example.proyectoVenta.stock.Repository;

import com.example.proyectoVenta.productos.Entity.Productos;
import com.example.proyectoVenta.stock.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {

    List<Stock> findByProductosIn(Set<Productos> producto);
//findByProductos
    Optional<Stock> findByProductos_IdProductos(Integer idProductos);

}
