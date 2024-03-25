package com.mateo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetalleVentaDTO {

    private ProductoDTO listaProducto;
    private double precioVenta;
    private Integer cantidad;
    private String estado;
}
