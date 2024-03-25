package com.mateo.rest.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
@ToString
public class ComprobantePago {

    @Id
    private String id;
    private List<DetalleVenta> listaDetalle;
    private String fechaVenta;
    private Cliente cliente;
}
