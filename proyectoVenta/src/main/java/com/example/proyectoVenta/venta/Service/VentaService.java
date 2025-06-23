package com.example.proyectoVenta.venta.Service;

import com.example.proyectoVenta.venta.Entity.Ventas;

public interface VentaService {


    Ventas registrarVentas(Integer productoId, int cantidad);
}
