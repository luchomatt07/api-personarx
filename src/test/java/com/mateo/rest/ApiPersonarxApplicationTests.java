package com.mateo.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mateo.rest.entity.ComprobantePago;
import com.mateo.rest.repository.ComprobantePagoRX2Repository;
import com.mateo.rest.service.ComprobanteRXServiceImp;
import io.reactivex.rxjava3.core.Single;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ApiPersonarxApplicationTests {

	@InjectMocks
	private ComprobanteRXServiceImp comprobanteRXServiceImp;

	@Mock
	private ComprobantePagoRX2Repository comprobantePagoRX2Repository;

	private ComprobantePago comprobantePago=new ComprobantePago();


	@Before
	public void setUp() throws JsonParseException, JsonMappingException, IOException {

		comprobantePago = new ObjectMapper().readValue(new ClassPathResource("dto/ComprobantePago.json")
				.getInputStream(), ComprobantePago.class);
	}

	@Test
	void contextLoads() {
	}


	@Test
	public void addVenta(){

		//Single<ComprobantePago> employee= Single.just(comprobantePago);

		Single<ComprobantePago> employ3= Single.just(comprobantePago);
		rx.Single<String> dat= rx.Single.just("OK");
		rx.Single<ComprobantePago> dat2= rx.Single.just(comprobantePago);

		Mockito.when(comprobantePagoRX2Repository.save(Mockito.any()))
				.thenReturn(employ3);


		//given(comprobantePagoRX2Repository.save(Mockito.any())).willReturn(Single.just(comprobantePago));


	}


}
