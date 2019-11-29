package com.exam.proyectsvc.controller;

import com.exam.proyectsvc.process.IProductProcess;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*  Autor: Ricardo Salvador Rivas Franco
    Descripcion:
    Funciones: -
*/

@RestController
@PropertySource("classpath:application.properties")
@RequestMapping("${service.url.lifebank}")
public class Controller {
    private Logger log;
    private Date date;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private IProductProcess process;

    public Controller(@Qualifier("BeanProductProcess") IProductProcess process) {
        this.log = LoggerFactory.getLogger(getClass());
        this.process = process;
    }

    //Endpoint para obtener los productos de un cliente
    @GetMapping("${app-properties.controller.products}")
    public ResponseEntity<?> getProducts(
            @PathVariable("accountID") String account,
            @RequestHeader("authorization") String token) {
        try {
            date = new Date();
            MDC.put("function", "getProducts");
            MDC.put("date", dateFormat.format(date));

            return process.process(account); //productProcess.process(token);
        }catch (Exception e){
            log.error("Hubo un error en getProducts, en la línea {} en el método {}, detalle del error {}",e.getStackTrace()[0].getLineNumber(),e.getStackTrace()[0].getMethodName(),e);
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

    }




}
