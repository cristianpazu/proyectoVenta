package com.example.proyectoVenta.stock.Service.Impl;

import com.example.proyectoVenta.categoria.Entity.Categoria;
import com.example.proyectoVenta.productos.Entity.Productos;
import com.example.proyectoVenta.productos.Repository.ProductosRepository;
import com.example.proyectoVenta.stock.Entity.Stock;
import com.example.proyectoVenta.stock.Repository.StockRepository;
import com.example.proyectoVenta.stock.Service.StockServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@Service
public class StockImpl implements StockServicio {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private  ProductosRepository productosRepository;
    @Override
    public Stock registrarStockProducto(Stock stock) {
        Productos productos = productosRepository.findById(stock.getProductos().getIdProductos()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Categoría no encontrada con ID: " ));

        Optional<Stock> stockExistente = stockRepository.findByProductos_IdProductos(productos.getIdProductos());
        if (stockExistente.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un stock registrado para el producto con ID: " + productos.getIdProductos()
            );
        }
        try{



            stock.setProductos( productos);



            return stockRepository.save(stock);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Stock actualizarStock(Stock stock) {

        System.out.println("stock.getCantidadStock() = " + stock.getCantidadStock());

        System.out.println("stock.getProductos().getIdProductos = " + stock.getProductos().getIdProductos());
        
        Productos productos = productosRepository.findById(stock.getProductos().getIdProductos()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Categoría no encontrada con ID: " ));


        try{

            stock.setProductos( productos);

            return stockRepository.save(stock);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Stock finByIdStock(Integer idStock) {
        try{
            return stockRepository.findById(idStock).orElseThrow();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Stock> findByAllStock() {
        List<Stock> stockList = stockRepository.findAll();

        try{
            return stockList;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
