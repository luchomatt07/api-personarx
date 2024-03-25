package com.mateo.rest.service;

import com.mateo.rest.dto.ClienteDTO;
import com.mateo.rest.dto.ComprobantePagoDTO;
import com.mateo.rest.dto.DetalleVentaDTO;
import com.mateo.rest.dto.ProductoDTO;
import com.mateo.rest.dto.response.BodyResponse;
import com.mateo.rest.dto.response.ComprobanteResponse;
import com.mateo.rest.entity.Cliente;
import com.mateo.rest.entity.ComprobantePago;
import com.mateo.rest.entity.DetalleVenta;
import com.mateo.rest.entity.Producto;
import com.mateo.rest.repository.ComprobantePagoRX2Repository;
import com.mateo.rest.util.Constantes;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ComprobanteRXServiceImp implements ComprobanteRXService{

    private ComprobantePagoRX2Repository comprobantePagRX2Repository;

    public ComprobanteRXServiceImp(ComprobantePagoRX2Repository comprobantePagRX2Repository) {
        this.comprobantePagRX2Repository = comprobantePagRX2Repository;
    }

    @Override
    public Single<ComprobantePago> addVenta(ComprobantePagoDTO comprobante) {
        return comprobantePagRX2Repository.save(comprobanteReqToEntity(comprobante));
    }

    @Override
    public Flowable<ComprobantePago> getAll() {
        return comprobantePagRX2Repository.findAll();
    }

    private ComprobantePago comprobanteReqToEntity(ComprobantePagoDTO comprobantePagoReq) {
        ComprobantePago comprobantePago = new ComprobantePago();
        BeanUtils.copyProperties(comprobantePagoReq, comprobantePago);
        comprobantePago.setId(UUID.randomUUID().toString());

        Cliente clien=new Cliente();
        BeanUtils.copyProperties(comprobantePagoReq.getCliente(), clien);
        comprobantePago.setCliente(clien);

        List<DetalleVenta> lis=comprobantePagoReq.getListaDetalle()
                .stream()
                .map(detalle ->{
                    Producto productTarget=new Producto();
                    BeanUtils.copyProperties(detalle.getListaProducto(), productTarget);

                    DetalleVenta detalleTarg=new DetalleVenta();
                    detalleTarg.setCantidad(detalle.getCantidad());
                    detalleTarg.setEstado(detalle.getEstado());
                    detalleTarg.setPrecioVenta(detalle.getPrecioVenta());
                    detalleTarg.setListaProducto(productTarget);
                    return detalleTarg;

                }).toList();
        comprobantePago.setListaDetalle(lis);
        return comprobantePago;
    }

    @Override
    public Flowable<ComprobantePago> getAllFilterMap(String idTransaccion,String dni,String estado,double precio, Integer cantidad) {
        //return  comprobantePagRX2Repository.findComprobantexPrecio(precio);
        System.out.println("Listaa..."+getAllFilterM(idTransaccion,dni,estado,precio,cantidad).toList().blockingGet());
        return  comprobantePagRX2Repository.findAll()
                .filter(filt-> null!=filt.getCliente() && null!=filt.getListaDetalle())
                .filter(client->dni.equals(client.getCliente().getDni()))
                .onErrorReturn(error->{
                    System.out.println("Error:... "+ error.getMessage());
                    return new ComprobantePago();
                });

    }

    public Flowable<List<DetalleVenta>> getAllFilterM(String idTransaccion,String dni,String estado,double precio, Integer cantidad) {
        //return  comprobantePagRX2Repository.findComprobantexPrecio(precio);
        return  comprobantePagRX2Repository.findAll()
                .filter(filt-> null!=filt.getCliente() && null!=filt.getListaDetalle())
                .filter(client->dni.equals(client.getCliente().getDni()))
                .flatMap(resource-> {
                    System.out.println("Iniciando Proceso Asyncrono ");
                   return Flowable.just(resource.getListaDetalle());
                })
                .doOnNext(x->{
                    System.out.println("Probando: "+x.stream().toString());
                });
    }

    public Flowable<List<DetalleVenta>> getAlf(String idTransaccion,String dni,String estado,double precio, Integer cantidad) {
        //return  comprobantePagRX2Repository.findComprobantexPrecio(precio);
        return  comprobantePagRX2Repository.findAll()
                .filter(filt-> null!=filt.getCliente() && null!=filt.getListaDetalle())
                .filter(client->dni.equals(client.getCliente().getDni()))
                .flatMap(resource-> {
                    System.out.println("Iniciando Proceso Asyncrono ");
                    return Flowable.just(resource.getListaDetalle());
                });
    }


    @Override
    public Single<ComprobanteResponse> getAllFilter(String idTransaccion,String dni,String estado,double precio, Integer cantidad) {
        Flowable<ComprobantePago> listaComprobantePago= comprobantePagRX2Repository.findComprobantexPrecio(precio);

        BodyResponse bodyResponse=new BodyResponse();
        bodyResponse.setIdtransaccion(idTransaccion);

        if(null ==listaComprobantePago ){
            bodyResponse.setMensajeRespuesta(Constantes.NOSE_ENCONTRARON_REGISTROS);
            bodyResponse.setCodigoRespuesta("1");

            return Single.create(singleSuscriber ->{
                ComprobanteResponse res=new ComprobanteResponse();
                res.setAuditResponse(bodyResponse);
                singleSuscriber.onSuccess(res);
            });
        }

        bodyResponse.setMensajeRespuesta("OK");
        bodyResponse.setCodigoRespuesta("0");

        return Single.create(singleSuscriber -> {
            ComprobanteResponse res = new ComprobanteResponse();
            res.setAuditResponse(bodyResponse);
            res.setListaComProbante(comprobanteReqToEntityFilter(listaComprobantePago.toList().blockingGet()));
            singleSuscriber.onSuccess(res);
        });
    }

    private  List<ComprobantePagoDTO> comprobanteReqToEntityFilter( List<ComprobantePago> listaComprobantePago) {
        return listaComprobantePago
                .stream()
                .filter(filt-> null!=filt.getCliente() && null!=filt.getListaDetalle())
                .map(listdetalle ->{
                    ComprobantePagoDTO comprobantePagoDTO=new ComprobantePagoDTO();
                    comprobantePagoDTO.setId(listdetalle.getId());
                    comprobantePagoDTO.setFechaVenta(listdetalle.getFechaVenta());

                    ClienteDTO clienteDTO=new ClienteDTO();
                    BeanUtils.copyProperties(listdetalle.getCliente(), clienteDTO);
                    comprobantePagoDTO.setCliente(clienteDTO);
                    comprobantePagoDTO.setListaDetalle(procesarDetalleList(listdetalle.getListaDetalle()));

                    return comprobantePagoDTO;
                })
                .toList();
    }


    public  List<DetalleVentaDTO> procesarDetalleList(List<DetalleVenta> listaDetalle){
        return listaDetalle
                .stream()
                .map(detalle ->{
                    DetalleVentaDTO deta=new DetalleVentaDTO();
                    deta.setPrecioVenta(detalle.getPrecioVenta());
                    deta.setCantidad(detalle.getCantidad());
                    deta.setEstado(detalle.getEstado());

                    ProductoDTO prod=new ProductoDTO();
                    BeanUtils.copyProperties(detalle.getListaProducto(), prod);
                    deta.setListaProducto(prod);
                    return deta;

                })
                .toList();
    }
}
