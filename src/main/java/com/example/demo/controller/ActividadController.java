package com.example.demo.controller;

import com.example.demo.entity.Actividad;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@CrossOrigin
public class ActividadController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity gestionExcepcion(HttpServletRequest request) {

        HashMap<String, Object> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") ) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar una actividad");
        }
        return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
    }

    @Autowired
    ActividadRepository actividadRepository;

    @GetMapping(value = "/actividad",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listarActividades(){
        return new ResponseEntity(actividadRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/actividad", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarActiviad(
            @RequestBody Actividad actividad,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();

        actividadRepository.save(actividad);
        if (fetchId) {
            responseMap.put("id", actividad.getIdactividad());
        }
        responseMap.put("estado", "creado");
        return new ResponseEntity(responseMap, HttpStatus.CREATED);

    }
}
