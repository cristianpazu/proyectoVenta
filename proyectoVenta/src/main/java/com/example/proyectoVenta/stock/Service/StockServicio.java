package com.example.proyectoVenta.stock.Service;

import com.example.proyectoVenta.productos.Entity.Productos;
import com.example.proyectoVenta.stock.Entity.Stock;

import java.util.List;

public interface StockServicio {

    Stock registrarStockProducto(Stock stock);

    Stock actualizarStock(Stock stock);

    Stock finByIdStock(Integer idStock);

    List<Stock> findByAllStock();
}
