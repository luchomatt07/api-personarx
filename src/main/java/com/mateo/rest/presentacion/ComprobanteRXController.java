package com.mateo.rest.presentacion;

import com.mateo.rest.dto.ComprobantePagoDTO;
import com.mateo.rest.dto.response.BodyResponse;
import com.mateo.rest.dto.response.ComprobanteResponse;
import com.mateo.rest.entity.ComprobantePago;
import com.mateo.rest.service.ComprobanteRXService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value =  "/api/comprobante")
public class ComprobanteRXController {

    private ComprobanteRXService comprobanteRXService;

    public ComprobanteRXController(ComprobanteRXService comprobanteRXService) {
        this.comprobanteRXService = comprobanteRXService;
    }

    @PostMapping(
            value = "/addcomprobanterx",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ComprobantePago> addComprobante(@RequestBody ComprobantePagoDTO comprobantePago) {
        return comprobanteRXService.addVenta(comprobantePago);
    }

    @GetMapping(
            value = "/fingallcomprobanterx",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Obtener todo los  comprobantes", description = "Mostrar Datos de Comprobante", tags = { "ComprobanteRXController" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ComprobantePago.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = BodyResponse.class)) }) })
    public Flowable<ComprobantePago> getAllComprobante(
            @RequestHeader("idTransaccion") String idTransaccion){
        return comprobanteRXService.getAll();
    }

    @GetMapping(
            value = "/findcomprobante",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<ComprobanteResponse>> getAllComprobante(
            @RequestHeader("idTransaccion") String idTransaccion,
            @RequestParam(value = "precio",defaultValue = "200", required = false) double precio,
            @RequestParam(value = "dni", required = false) String dni,
            @RequestParam(value = "estado", required=false) String estado,
            @RequestParam(value = "cantidad", required = false) Integer cantidad){
        return comprobanteRXService.getAllFilter(idTransaccion,dni,estado,precio,cantidad)
                .subscribeOn(Schedulers.io())
                .map(sus->ResponseEntity.ok(sus));
    }

    @GetMapping(
            value = "/findallcomprobanterxmmap",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flowable<ComprobantePago> getAllComprobanteMap(
            @RequestHeader("idTransaccion") String idTransaccion,
            @RequestParam(value = "precio",defaultValue = "200", required = false) double precio,
            @RequestParam(value = "dni", required = false) String dni,
            @RequestParam(value = "estado", required=false) String estado,
            @RequestParam(value = "cantidad", required = false) Integer cantidad){
         return comprobanteRXService.getAllFilterMap(idTransaccion,dni,estado,precio,cantidad);
    }
}
