package com.mateo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComprobantePagoDTO {

    private String id;
    private List<DetalleVentaDTO> listaDetalle;
    private String fechaVenta;
    private ClienteDTO cliente;
}
