package br.com.fiap.opengroup.dto;

import org.springframework.http.ResponseEntity;

public interface ControllerDTO <Request, Response>{

    ResponseEntity<Response> findById(Long id);

    ResponseEntity<Response> save(Request r);

}