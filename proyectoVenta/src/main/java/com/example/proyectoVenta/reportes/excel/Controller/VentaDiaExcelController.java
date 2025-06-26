package com.example.proyectoVenta.reportes.excel.Controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.example.proyectoVenta.reportes.consultaExcel.dto.VentasDIA;
import com.example.proyectoVenta.reportes.consultaExcel.service.Impl.VentaDiaImpl;
import com.example.proyectoVenta.reportes.consultaExcel.service.VentaDiaServicio;
import com.example.proyectoVenta.reportes.excel.dto.InformacionVentaDiaDto;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/export")
public class VentaDiaExcelController {
    @Autowired
    private VentaDiaImpl ventaDiaServicio;


    @GetMapping("/excel/{fecha}")
    public ResponseEntity<byte[]> exportarExcelDesdeServicio(@PathVariable  String fecha) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Obtener usuarios del servicio
            List<Object[]> ventaDia = ventaDiaServicio.obtenerVentaDia(fecha);

            System.out.println("ventaDia>Z>>>>>>>>>>>>>> = " + ventaDia);

            // Convertir al DTO de Excel
            List<InformacionVentaDiaDto> usuariosExcel = ventaDia.stream()
                    .map(obj -> new InformacionVentaDiaDto((String) obj[0],
                             obj[1].toString(),
                            (Integer) obj[2]))
                    .collect(Collectors.toCollection(ArrayList::new));



            // Estilo para títulos
            WriteCellStyle headStyle = new WriteCellStyle();
            WriteFont headFont = new WriteFont();
            headFont.setBold(true);
            headFont.setFontHeightInPoints((short) 12);
            headFont.setColor(IndexedColors.WHITE.getIndex());
            headStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            headStyle.setFillPatternType(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
            headStyle.setWriteFont(headFont);
            headStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Estilo para celdas normales
            WriteCellStyle contentStyle = new WriteCellStyle();
            WriteFont contentFont = new WriteFont();
            contentFont.setFontHeightInPoints((short) 11);
            contentStyle.setWriteFont(contentFont);
            contentStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);

            HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(headStyle, contentStyle);


            // Calcular total
            int sumaTotal = usuariosExcel.stream()
                    .mapToInt(InformacionVentaDiaDto::getTotal)
                    .sum();

// Agregar fila TOTAL
            usuariosExcel.add(new InformacionVentaDiaDto("TOTAL", "", sumaTotal));


            // Escribir el Excel
            EasyExcel.write(out, InformacionVentaDiaDto.class)
                    .registerWriteHandler(styleStrategy)
                    .sheet("ventaDIa")
                    .doWrite(usuariosExcel);

            String nombreArchivo = URLEncoder.encode("usuarios-servicio.xlsx", StandardCharsets.UTF_8);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", nombreArchivo);

            return ResponseEntity.ok().headers(headers).body(out.toByteArray());

        } catch (Exception e) {
            System.out.println(" e.printStackTrace(); = " +  e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

