package com.mateo.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BodyResponse {

    private String idtransaccion;
    private String codigoRespuesta;
    private String mensajeRespuesta;
}

