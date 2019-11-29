package com.exam.proyectsvc.data.product;

import com.exam.proyectsvc.pojo.database.PrdProducto;
import com.exam.proyectsvc.pojo.database.PrdTipoproducto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ProductStatic")
public class ProductStatic implements  IProductData {


    @Override
    public List<PrdProducto> getProducts(String id) {

        List<PrdProducto> productoList = new ArrayList<>();
        PrdProducto producto = new PrdProducto();
        PrdTipoproducto tipoproducto = new PrdTipoproducto();

        if(id.equalsIgnoreCase("1")){
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
        }
        return productoList;

    }
}
