package com.exam.proyectsvc;

import com.exam.proyectsvc.build.IParserFactory;
import com.exam.proyectsvc.build.ParserFactory;
import com.exam.proyectsvc.build.ParserProductResponse;
import com.exam.proyectsvc.data.product.IProductData;
import com.exam.proyectsvc.data.product.ProductoDB;
import com.exam.proyectsvc.pojo.database.PrdProducto;
import com.exam.proyectsvc.pojo.database.PrdTipoproducto;
import com.exam.proyectsvc.pojo.srv.ProductResponse;
import com.exam.proyectsvc.process.ProductProcess;
import com.exam.proyectsvc.repository.IproductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class productProcessTest {

    @Test
    public void SuccesCase()  {
        IProductData data = mock(ProductoDB.class); // Necesita : ProductoDB Alcance: ProductDB.getProducts() ( Se libera esta seccion)
        Environment env = mock(Environment.class); //  Environment.
        ApplicationContext context = mock(ApplicationContext.class); // ApplicationContext
        List<PrdProducto> list = build(); // Construcción de objeto
         IParserFactory factory  = new ParserFactory(context); // Necesita ApplicationContext Alcance: ParserFactory.buildParser


        // Escenario: Cuando se presenta X situación, devolver Y respuesta...

        when(env.getProperty("appProperties.creditCard"))
                .thenReturn(String.valueOf(1))
        ;
        when(env.getProperty("appProperties.loan"))
                .thenReturn(String.valueOf(2))
        ;
        when(data.getProducts("1"))
                .thenReturn(list)
        ;
        when(context.getBean("ProductResponseParser")).
                thenReturn(new ParserProductResponse(env))
        ;

        ProductProcess process  = new ProductProcess(data, factory); // Se procede a probar ProductoProcess().
        ResponseEntity<?> responseEntity = process.process("1");
        Assert.assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Test
    public void FailCase()  {
        IproductRepository repository = mock(IproductRepository.class);
        IProductData data = new ProductoDB(repository);//mock(ProductoDB.class);
        Environment env = mock(Environment.class);
        ApplicationContext context = mock(ApplicationContext.class);
        List<PrdProducto> list = build();
        IParserFactory factory  = new ParserFactory(context);


        when(env.getProperty("appProperties.creditCard"))
                .thenReturn(String.valueOf(1))
        ;
        when(env.getProperty("appProperties.loan"))
                .thenReturn(String.valueOf(2))
        ;
        when(repository.getProductByIdPropietario(Integer.parseInt("1")))
                .thenReturn(list)
        ;
        when(context.getBean("ProductResponseParser")).
                thenReturn(new ParserProductResponse(env))
        ;

        ProductProcess process  = new ProductProcess(data, factory);
        ResponseEntity<?> responseEntity = process.process("2");
        Assert.assertEquals(202, responseEntity.getStatusCode().value());
    }

    @Test
    public void EmptyProductResponseFactory(){
        Environment env = mock(Environment.class);
        ParserProductResponse builder = new ParserProductResponse(env);
        List<PrdProducto> list = new ArrayList<>();
        ProductResponse response = builder.BuildResponse(list);

        Assert.assertTrue(response.getAccounts().getCreditCard().isEmpty());
        Assert.assertTrue(response.getAccounts().getLoan().isEmpty());
        Assert.assertTrue(response.getAccounts().getPersonal().isEmpty());
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
