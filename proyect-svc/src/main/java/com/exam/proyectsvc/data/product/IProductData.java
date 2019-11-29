package com.exam.proyectsvc.data.product;

import java.util.List;

public interface IProductData<T> {

    public List<T> getProducts(String id);
}
