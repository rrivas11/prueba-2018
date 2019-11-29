package com.exam.proyectsvc.process;

import com.exam.proyectsvc.build.IParser;
import com.exam.proyectsvc.build.IParserFactory;
import com.exam.proyectsvc.data.product.IProductData;
import com.exam.proyectsvc.pojo.database.PrdProducto;
import com.exam.proyectsvc.pojo.srv.ProductResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductProcess")
public class ProductProcess implements IProductProcess {

    private IProductData data;
    private IParser<ProductResponse, List<PrdProducto>> builder;
    private IParserFactory factory ;

    public ProductProcess(@Qualifier("ProductoDataBean") IProductData data,
                          @Qualifier("parserFactoryBean") IParserFactory factory ) {
        this.data = data;
        this.factory = factory;
    }

    @Override
    public ResponseEntity<?> process(String accountID) {
        List<PrdProducto> prdProductoList;
        ProductResponse response;
        HttpStatus status;

        // primero se obtienen los registros, INT: IProductData
        prdProductoList = data.getProducts(accountID);
        // Luego se obtiene el builder Parser con la Fabrica (Factory) :  INT: IParserFactory
        builder = factory.buildParser("producto");

        // Se valida cuantos registros se obtuvieron, en base a ello se determina el status
        if(prdProductoList.size() > 0) {
            status = HttpStatus.OK;
        }else{
            status = HttpStatus.ACCEPTED;
        }
        // ya con los registros (prdProductoList) y el parser (Builder). INT: IBuilder
        response = builder.BuildResponse(prdProductoList);

        // Se forma la respuesta HttpEntity
        ResponseEntity<?> entity = new ResponseEntity<>(response, status);

        return entity;
    }
}
