package com.example.proyectoVenta.venta.Controller;

import com.example.proyectoVenta.venta.Entity.Ventas;
import com.example.proyectoVenta.venta.Service.Impl.VentaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaImpl ventaImpl;

    @PostMapping("/registrarVenta/{productoId}/{cantidad}")
    public ResponseEntity<Object> registrarVenta(@PathVariable Integer productoId,
                                                 @PathVariable Integer cantidad) {
       Ventas vs =  ventaImpl.registrarVentas(productoId, cantidad);
        return ResponseEntity.status(HttpStatus.OK).body(vs);
    }

}
