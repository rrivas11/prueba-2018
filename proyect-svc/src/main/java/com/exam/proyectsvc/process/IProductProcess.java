package com.exam.proyectsvc.process;

import org.springframework.http.ResponseEntity;

public interface IProductProcess {

    public ResponseEntity<?> process(String accountID);

}
