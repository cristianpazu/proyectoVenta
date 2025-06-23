package com.example.proyectoVenta.stock.Controller;

import com.example.proyectoVenta.productos.Entity.Productos;
import com.example.proyectoVenta.stock.Entity.Stock;
import com.example.proyectoVenta.stock.Service.Impl.StockImpl;
import com.example.proyectoVenta.stock.Service.StockServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockImpl stockImpl;

    @PostMapping("/registrarProductos")
    public ResponseEntity<Object> registrarStock(@RequestBody Stock stock) {

        Stock stockGuardado = stockImpl.registrarStockProducto(stock);

        return ResponseEntity.status(HttpStatus.CREATED).body(stockGuardado);

    }

    @PutMapping("/actualizar")
    public ResponseEntity<Object> stockStock(@RequestBody Stock stock) {

        Stock StockActualizado = stockImpl.actualizarStock(stock);

        return ResponseEntity.status(HttpStatus.CREATED).body(StockActualizado);

    }

    @GetMapping("/traerporId/{id}")
    public ResponseEntity<Object> findByIdstock(@PathVariable Integer id) {
        Stock stockFindById = stockImpl.finByIdStock(id);
        return ResponseEntity.status(HttpStatus.OK).body(stockFindById);

    }

    @GetMapping("/traerporAll")
    public ResponseEntity<Object> traerStock() {
        List<Stock> stocksFindByAll = stockImpl.findByAllStock();
        return ResponseEntity.status(HttpStatus.OK).body(stocksFindByAll);

    }
}
