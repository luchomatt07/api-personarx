package com.mateo.rest.service;

import com.mateo.rest.dto.ComprobantePagoDTO;
import com.mateo.rest.dto.response.ComprobanteResponse;
import com.mateo.rest.entity.ComprobantePago;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface ComprobanteRXService {

    Single<ComprobantePago> addVenta(ComprobantePagoDTO comprobante);
    Flowable<ComprobantePago> getAll();

    public Flowable<ComprobantePago> getAllFilterMap(String idTransaccion,String dni,String estado,double precio, Integer cantidad);
    Single<ComprobanteResponse> getAllFilter(String idTransaccion, String dni, String estado, double precio, Integer cantidad);
}
