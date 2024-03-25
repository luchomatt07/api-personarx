package com.mateo.rest.dto.response;

import com.mateo.rest.dto.ComprobantePagoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComprobanteResponse {
    private BodyResponse auditResponse;
    private List<ComprobantePagoDTO> listaComProbante;

}
