package com.exam.proyectsvc;

import com.exam.proyectsvc.build.ParserProductResponse;
import com.exam.proyectsvc.pojo.database.PrdProducto;
import com.exam.proyectsvc.pojo.database.PrdTipoproducto;
import com.exam.proyectsvc.pojo.srv.ProductResponse;
import com.exam.proyectsvc.process.ProductProcess;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
class ProductSvcApplicationTests {

	@Autowired
	private ProductProcess process;

	@Autowired
	private ParserProductResponse responseFactory;

	private Logger log;

	@Before
	public void setup(){
		this.log = LoggerFactory.getLogger(getClass());
	}

	@Test
	void SuccesCase() {
		ResponseEntity<?> responseEntity = process.process("1");

		Assert.assertEquals(200, responseEntity.getStatusCode().value());
	}

	@Test
	void ErrorCase() {
		ResponseEntity<?> responseEntity = process.process("2");

		Assert.assertEquals(202, responseEntity.getStatusCode().value());
	}

	@Test
	void FactoryNullCase() {

		Boolean b;
		List<PrdProducto> list = null;
		ProductResponse responseR = new ProductResponse();
		ProductResponse response = responseFactory.BuildResponse(list);

		Assert.assertEquals(responseR.getAccounts(),response.getAccounts());
	}


	public List<PrdProducto> build(){

		List<PrdProducto> productoList = new ArrayList<>();
		PrdProducto producto = new PrdProducto();
		PrdTipoproducto tipoproducto = new PrdTipoproducto();

		producto.setPrdIdproducto("0888882");
		producto.setPrdIdpropietario(1);
		producto.setPrdNombre("VISA SELECTOS");
		producto.setPrdMonto(12.23);
		producto.setPrdTasa(0.19);
		tipoproducto.setTipoId(1);
		tipoproducto.setTipoNombre("Tarjeta de credito");
		producto.setPrdTipoproducto(tipoproducto);
		productoList.add(producto);


		producto = new PrdProducto();
		tipoproducto = new PrdTipoproducto();
		producto.setPrdIdproducto("0777772");
		producto.setPrdIdpropietario(1);
		producto.setPrdNombre("DEBITO VISA");
		producto.setPrdMonto(120.00);
		producto.setPrdTasa(0.0);

		tipoproducto.setTipoId(3);
		tipoproducto.setTipoNombre("Cuenta de ahorro");
		producto.setPrdTipoproducto(tipoproducto);

		productoList.add(producto);
		return productoList;
	}

}
