package com.example.proyectoVenta.venta.Service.Impl;

import com.example.proyectoVenta.productos.Entity.Productos;
import com.example.proyectoVenta.productos.Repository.ProductosRepository;
import com.example.proyectoVenta.stock.Entity.Stock;
import com.example.proyectoVenta.stock.Repository.StockRepository;
import com.example.proyectoVenta.venta.Entity.DetalleVenta;
import com.example.proyectoVenta.venta.Entity.Ventas;
import com.example.proyectoVenta.venta.Repository.VentaRepository;
import com.example.proyectoVenta.venta.Service.VentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class VentaImpl implements VentaService {


    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductosRepository productosRepository;

    @Autowired
    private VentaRepository ventaRepository;
    @Override
    public Ventas registrarVentas(Integer productoId, int cantidad) {


        Productos producto = productosRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Stock stock = stockRepository.findByProductos(producto)
                .orElseThrow(() -> new RuntimeException("Stock no encontrado"));

        if (stock.getCantidadStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        try{
            Ventas venta = new Ventas();
            venta.setFecha(LocalDate.now());
            venta.setTotal(producto.getPrecioVenta() * cantidad);

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVentas(venta);
            detalle.setProductos(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(producto.getPrecioVenta());

            venta.setDetalles(List.of(detalle));

            // Guardar venta y detalle
          Ventas vr =   ventaRepository.save(venta);

            // Actualizar stock
            stock.setCantidadStock(stock.getCantidadStock() - cantidad);
            stockRepository.save(stock);
            return vr;
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public Ventas listarVentas(Integer idVentas) {

        Ventas ventas = ventaRepository.findById(idVentas)
                .orElseThrow(() -> new RuntimeException("venta no encontrado"));

        try{



            return ventas;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ventas> listarTodasVentas() {

        try{
            List<Ventas> ventas = ventaRepository.findAll();
            if (ventas.isEmpty()) {

                throw new RuntimeException("La lista esta vacia");
            }
            return ventas;
        }catch  (Exception e){
            throw new RuntimeException(e);

        }
    }
}
