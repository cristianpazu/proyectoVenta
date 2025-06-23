package com.example.proyectoVenta.productos.Services;

import com.example.proyectoVenta.productos.Entity.Productos;

import java.util.List;

public interface productoServicio {


    Productos registrarProductos(Productos productos);

    Productos actualizarProductos(Productos productos);

    String eliminarProductos(Integer id);

    Productos findByIdProductos(Integer id);

    List<Productos> findByAllProductos();



}
