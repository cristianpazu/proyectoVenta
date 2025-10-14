package com.example.proyectoVenta.categoria.Controller;

import com.example.proyectoVenta.categoria.Entity.Categoria;
import com.example.proyectoVenta.categoria.Services.CategoriaServicio;
import com.example.proyectoVenta.categoria.Services.Impl.CategoriasImpl;
import com.example.proyectoVenta.productos.Entity.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

 @Autowired
 CategoriasImpl categoriaImpl;

 @PostMapping("/registrarCategoria")
 public ResponseEntity<Object> registrarCategoria(@RequestBody Categoria categoria){

  Categoria categoriaGuardado = categoriaImpl.registrarCategoria(categoria);

  return ResponseEntity.status(HttpStatus.CREATED).body(categoriaGuardado);

 }

 @PutMapping("/actualizar")
 public ResponseEntity<Object> actualizarCategoria(@RequestBody Categoria categoria){

  Categoria categoriaGuardado = categoriaImpl.actualizarCategoria(categoria);

  return ResponseEntity.status(HttpStatus.CREATED).body(categoriaGuardado);

 }


 @GetMapping("/traerporId/{id}")
 public ResponseEntity<Object> findByIdCategoria(@PathVariable Integer id){
  Categoria categoriaFindById = categoriaImpl.findByIdCategoria(id);
  return  ResponseEntity.status(HttpStatus.OK).body(categoriaFindById);

 }

 @GetMapping("/traerporAll")
 public ResponseEntity<Object> traerCategoria(){
  List<Categoria> categoriaFindByAll = categoriaImpl.traerTodoCategoria();
  return  ResponseEntity.status(HttpStatus.OK).body(categoriaFindByAll);

 }


}
