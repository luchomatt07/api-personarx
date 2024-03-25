package com.mateo.rest.repository;

import com.mateo.rest.entity.ComprobantePago;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobantePagoRX2Repository extends RxJava3CrudRepository<ComprobantePago, String> {

    @Query(value = "{ 'listaDetalle.precioVenta' : { $gt : ?0 }}")
    Flowable<ComprobantePago> findComprobantexPrecio(double precioVenta);

}
