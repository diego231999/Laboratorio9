package com.example.demo.controller;

import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listarProductos(){
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }


}

