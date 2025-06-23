package com.example.proyectoVenta.productos.Controller;


import com.example.proyectoVenta.categoria.Entity.Categoria;
import com.example.proyectoVenta.productos.Entity.Productos;
import com.example.proyectoVenta.productos.Services.Impl.ProductoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/productos")
public class ProductosController {
@Autowired
    private ProductoImpl productoImpl;

    @PostMapping("/registrarProductos")
    public ResponseEntity<Object> registrarProducto(@RequestBody Productos productos){

        Productos productoGuardado = productoImpl.registrarProductos(productos);
        System.out.println("productoGuardado" + productoGuardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);

    }

    @GetMapping("/actualizar")
    public ResponseEntity<Object> actualizarProducto(@RequestBody Productos productos){

        Productos productoGuardado = productoImpl.actualizarProductos(productos);

        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);

    }


    @GetMapping("/traerporId/{id}")
    public ResponseEntity<Object> findByIdProducto(@PathVariable Integer id){
        Productos productosFindById = productoImpl.findByIdProductos(id);
        return  ResponseEntity.status(HttpStatus.OK).body(productosFindById);

    }

    @GetMapping("/traerporAll")
    public ResponseEntity<Object> traerProducto(){
        List<Productos> productosFindByAll = productoImpl.findByAllProductos();
        return  ResponseEntity.status(HttpStatus.OK).body(productosFindByAll);

    }


}
