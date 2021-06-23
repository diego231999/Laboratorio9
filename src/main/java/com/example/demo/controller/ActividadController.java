package com.example.demo.controller;

import com.example.demo.entity.Actividad;
import com.example.demo.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;

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

    @PutMapping(value = "/actividad",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editarActividad(@RequestBody Actividad actividad){
        HashMap<String, Object> responseMap = new HashMap<>();

        if(actividad.getIdproyecto() != null){

        }else{
            responseMap.put("estado","error");
            responseMap.put("msg","El id de la actividad no puede ser ");
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }

        Optional<Actividad> actividadOpt = actividadRepository.findById(actividad.getIdactividad());

        if(actividadOpt.isPresent()){
            Actividad actividadVal = actividadOpt.get();

            if(actividadVal.getNombreactividad() != null){
                actividadVal.setNombreactividad(actividad.getNombreactividad());
            }
            if(actividadVal.getEstado() != null){
                actividadVal.setEstado(actividad.getEstado());
            }
            if(actividadVal.getDescripcion() != null){
                actividadVal.setDescripcion(actividad.getDescripcion());
            }
            if(actividadVal.getPeso() != null){
                actividadVal.setPeso(actividad.getPeso());
            }
            if(actividadVal.getUsuarioOwner() != null){
                actividadVal.setUsuarioOwner(actividad.getUsuarioOwner());
                responseMap.put("msg","El id del dueño no puede ser nulo");
                return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
            }
            if(actividadVal.getIdproyecto() != null){
                actividadVal.setIdproyecto(actividad.getIdproyecto());
                responseMap.put("msg","El id del proyecto no puede ser nulo");
                return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
            }

            actividadRepository.save(actividadVal);
            responseMap.put("estado",actividadVal.getNombreactividad()+" Actualizado");
            return new ResponseEntity(responseMap, HttpStatus.OK);
        }else{
            responseMap.put("estado","error");
            responseMap.put("msg","La actividad no existe");
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/actividad/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity borrarActividad(@PathVariable("id") String idStr) {

        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            if (actividadRepository.existsById(id)) {
                actividadRepository.deleteById(id);
                responseMap.put("estado", "borrado exitoso");
                return new ResponseEntity(responseMap, HttpStatus.OK);
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "no se encontró la actividad con id: " + id);
                return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "El ID debe ser un número");
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }
    }
}
