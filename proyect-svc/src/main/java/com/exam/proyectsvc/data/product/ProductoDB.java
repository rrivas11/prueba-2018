package com.exam.proyectsvc.data.product;

import com.exam.proyectsvc.pojo.database.PrdProducto;
import com.exam.proyectsvc.repository.IproductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductoDB")
public class ProductoDB implements  IProductData {

    private IproductRepository repository;

    public ProductoDB(IproductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PrdProducto> getProducts(String id) {
        return  repository.getProductByIdPropietario(Integer.parseInt(id));
    }
}
