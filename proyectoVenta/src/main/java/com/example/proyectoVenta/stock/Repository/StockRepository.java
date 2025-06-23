package com.example.proyectoVenta.stock.Repository;

import com.example.proyectoVenta.stock.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,   Integer> {


}
