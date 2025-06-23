package com.example.proyectoVenta.categoria.Services;

import com.example.proyectoVenta.categoria.Entity.Categoria;

import java.util.List;

public interface CategoriaServicio {

    Categoria registrarCategoria(Categoria categoria);

    Categoria actualizarCategoria(Categoria categoria);

    String eliminarCategoria(Integer id);

    Categoria findByIdCategoria(Integer id);

    List<Categoria> traerTodoCategoria();
}
