package com.example.proyectoVenta.categoria.Repository;

import com.example.proyectoVenta.categoria.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
