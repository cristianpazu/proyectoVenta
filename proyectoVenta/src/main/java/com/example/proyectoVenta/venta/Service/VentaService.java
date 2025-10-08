package com.example.proyectoVenta.venta.Service;

import com.example.proyectoVenta.productos.Entity.Productos;
import com.example.proyectoVenta.venta.Entity.DetalleVenta;
import com.example.proyectoVenta.venta.Entity.Ventas;

import java.util.List;

public interface VentaService {


    Ventas registrarVentas(Ventas ventas);

    Ventas listarVentas(Integer idVentas);

    List<Ventas> listarTodasVentas();
}
