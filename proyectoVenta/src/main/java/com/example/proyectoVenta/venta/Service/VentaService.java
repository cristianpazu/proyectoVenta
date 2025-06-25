package com.example.proyectoVenta.venta.Service;

import com.example.proyectoVenta.productos.Entity.Productos;
import com.example.proyectoVenta.venta.Entity.Ventas;

import java.util.List;

public interface VentaService {


    Ventas registrarVentas(Integer productoId, int cantidad);

    Ventas listarVentas(Integer idVentas);

    List<Ventas> listarTodasVentas();
}
