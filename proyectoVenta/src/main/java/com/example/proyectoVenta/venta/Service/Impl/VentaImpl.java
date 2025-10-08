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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Ventas registrarVentas(Ventas ventas) {



        Set<Productos> productosList = ventas.getProductos().stream()
                .map(producto -> productosRepository.findById(producto.getIdProductos()).orElseThrow(
                        () -> new RuntimeException("Stock no encontrado")))
                .collect(Collectors.toSet());
        /*
        Productos producto = productosRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")); */

        List<Stock> stockList = stockRepository.findByProductosIn(productosList);

// Crear un mapa de producto -> stock para buscar fácilmente después
        Map<Productos, Stock> stockMap = stockList.stream()
                .collect(Collectors.toMap(Stock::getProductos, stock -> stock));




        List<Productos> ped = ventas.getProductos().stream().collect(Collectors.toList());

        try{

            int total = 0;
            List<DetalleVenta> detallesVenta = new ArrayList<>();
            Ventas venta = new Ventas();
            venta.setFecha(LocalDate.now());

            for (Productos pedido : ped) {
                // Buscar el producto real desde productosList (por ID)
                Productos productoBase = productosList.stream()
                        .filter(p -> p.getIdProductos().equals(pedido.getIdProductos()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + pedido.getIdProductos()));

                // Buscar el stock correspondiente
                Stock stock = stockMap.get(productoBase);
                if (stock == null) {
                    throw new RuntimeException("Stock no encontrado para el producto: " + productoBase.getNombre());
                }

                // Validar stock
                if (stock.getCantidadStock() < pedido.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente para el producto: " + productoBase.getNombre());
                }

                // Descontar stock
                int stockActualizado = stock.getCantidadStock() - pedido.getCantidad();
                stock.setCantidadStock(stockActualizado);
                stockRepository.save(stock);

                // Calcular total
                int subtotal = productoBase.getPrecioVenta() * pedido.getCantidad();
                total += subtotal;

                // Crear detalle
                DetalleVenta detalle = new DetalleVenta();
                detalle.setVentas(venta);
                detalle.setProductos(productoBase);
                detalle.setCantidad(pedido.getCantidad());
                detalle.setPrecioUnitario(productoBase.getPrecioVenta());
                detallesVenta.add(detalle);
            }

            venta.setTotal(total);
            venta.setDetalles(detallesVenta);

            Ventas ventaRegistrada = ventaRepository.save(venta);
            return ventaRegistrada;

// Verificar stock y calcular total
     /*   int total = 0;
            Productos productoPedido = new Productos();
            List<DetalleVenta> detallesVenta = new ArrayList<>();
            Ventas venta = new Ventas();
            venta.setFecha(LocalDate.now());

        for (Productos producto : productosList) {
            productoPedido = producto;
            Stock stock = stockMap.get(productoPedido);

            System.out.println("productoPedido.getIdProductos() = " + productoPedido.getIdProductos());
            
            Productos ped2 = null;
            for (Productos pr2 : ped) {
                ped2 = pr2;

                if (stock == null) {
                    throw new RuntimeException("Stock no encontrado para el producto: " + producto.getNombre());
                }


                if (productoPedido.getIdProductos().equals(pr2.getIdProductos())) {
                    if (stock.getCantidadStock() < productoPedido.getCantidad()) {

                        throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
                    }
                }
                if (stock.getProductos().getIdProductos().equals(pr2.getIdProductos())) {
                    int stockActualizado = stock.getCantidadStock() -  ped2.getCantidad();
                    stock.setCantidadStock(stockActualizado);
                    stockRepository.save(stock);
                    
                    
                }
                System.out.println("producto.getPrecioVenta() = " + producto.getPrecioVenta());
                System.out.println("ped2.getCantidad() = " + ped2.getCantidad());
                System.out.println("total = " + total);
                total += productoPedido.getPrecioVenta() * ped2.getCantidad();
            }





            DetalleVenta detalle = new DetalleVenta();
            detalle.setVentas(venta);
            detalle.setProductos(productoPedido);
            detalle.setCantidad(productoPedido.getCantidad());
            detalle.setPrecioUnitario(productoPedido.getPrecioVenta());
            detallesVenta.add(detalle);


        }

            venta.setTotal(total);
            venta.setDetalles(detallesVenta);


           Ventas vr =   ventaRepository.save(venta);

            return vr; */
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
