package com.exam.proyectsvc.repository;

import com.exam.proyectsvc.pojo.database.PrdProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IproductRepository extends CrudRepository<PrdProducto, String> {


    //  ver los productos de un clientes
    //List<PrdProducto> findByprdIdpropietario(int id_cliente);

    @Query(value="SELECT * FROM prd_producto WHERE prd_idpropietario = :id", nativeQuery = true)
    public List<PrdProducto> getProductByIdPropietario(@Param("id") int id);

}
