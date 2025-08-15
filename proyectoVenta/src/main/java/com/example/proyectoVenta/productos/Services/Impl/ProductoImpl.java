package com.example.proyectoVenta.productos.Services.Impl;

import com.example.proyectoVenta.categoria.Entity.Categoria;
import com.example.proyectoVenta.categoria.Repository.CategoriaRepository;
import com.example.proyectoVenta.productos.Entity.ProductoResponse;
import com.example.proyectoVenta.productos.Entity.Productos;
import com.example.proyectoVenta.productos.Repository.ProductosRepository;
import com.example.proyectoVenta.productos.Services.productoServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@Service
public class ProductoImpl implements productoServicio {


    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private  CategoriaRepository categoriaRepository;

    @Override
    public Productos registrarProductos(Productos productos) {
        /*if (productosRepository.findById(productos.getIdProductos()).isPresent()) {
            System.out.println("El producto ya registrado");
        } */

        Set<Categoria> categoriasList = productos.getCategorias().stream()
                .map(modulo -> categoriaRepository.findById(modulo.getIdCategoria()).orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Categoría no encontrada con ID: " + modulo.getIdCategoria()
                        ))
                )
                .collect(Collectors.toSet());
        System.out.println("categoriasListZZZZZZZZZZ " + categoriasList);

        try{
            LocalDate fechaIngreso = productos.getFechaIngreso();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String fechaIngre = fechaIngreso.format(formatter);


            LocalDate nuevaFechaIngreso = LocalDate.parse(fechaIngre, formatter);
            productos.setFechaIngreso(nuevaFechaIngreso);
            



            productos.setCategorias(categoriasList);



            return productosRepository.save(productos);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public Productos actualizarProductos(Productos productos) {

        Productos actualizarProductos = productosRepository.findById(productos.getIdProductos()).orElseThrow();
        Set<Categoria> categoriasList = productos.getCategorias().stream()
                .map(modulo -> categoriaRepository.findById(modulo.getIdCategoria()).orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Categoría no encontrada con ID: " + modulo.getIdCategoria()
                        ))
                )
                .collect(Collectors.toSet());

        try{
            actualizarProductos.setNombre(productos.getNombre());
            actualizarProductos.setReferencia(productos.getReferencia());
            actualizarProductos.setPrecioVenta(productos.getPrecioVenta());
            actualizarProductos.setFechaIngreso(productos.getFechaIngreso());
            actualizarProductos.setObservacion(productos.getObservacion());
            actualizarProductos.setCategorias(categoriasList);



            return productosRepository.save(actualizarProductos);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public String eliminarProductos(Integer id) {
        return "";
    }

    @Override
    public Productos findByIdProductos(Integer id) {
        try {
             Productos productos = productosRepository.findById(id).orElseThrow();

            return productos;


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductoResponse> findByAllProductos() {
        try{
            List<ProductoResponse> Product = productosRepository.findAllByProductos();
            if (Product.isEmpty()) {

                throw new RuntimeException("La lista esta vacia");
            }
            return Product;
        }catch  (Exception e){
            throw new RuntimeException(e);

        }
    }
}
