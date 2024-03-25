package com.mateo.rest.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@ToString
public class DetalleVenta {

    @Id
    private String id;
    private Producto listaProducto;
    private double precioVenta;
    private Integer cantidad;
    private String estado;

}
