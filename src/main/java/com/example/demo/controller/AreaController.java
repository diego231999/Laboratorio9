package com.example.demo.controller;

import com.example.demo.entity.Area;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.UsuarioRepository;
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
public class AreaController {

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity gestionException(HttpServletRequest request) {

        HashMap<String, Object> responseMap = new HashMap<>();

        if (request.getMethod().equals("POST")) {
            responseMap.put("msg", "Debe enviar un area");
            responseMap.put("estado", "error");
        }
        return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/area", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listarAreas() {
        return new ResponseEntity(areaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/area/{id}")
    public ResponseEntity obtenerArea(@PathVariable("id") String idareaStr) {
        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int idarea = Integer.parseInt(idareaStr);
            Optional<Area> areaOpt = areaRepository.findById(idarea);
            if (areaOpt.isPresent()) {
                Area area = areaOpt.get();
                responseMap.put("area", area);
                responseMap.put("estado", "ok");
                return new ResponseEntity(responseMap, HttpStatus.OK);
            } else {
                responseMap.put("msg", "no se encontró el area con id: " + idarea);
                responseMap.put("estado", "error");
                return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            responseMap.put("msg", "el  ID debe ser un número");
            responseMap.put("estado", "error");
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/areaConUsuarios/{id}")
    public ResponseEntity mostrarAreaConUsuarios(@PathVariable("id") String idareaStr){
        HashMap<String, Object> responseMap = new HashMap<>();

        try{
            int idarea = Integer.parseInt(idareaStr);
            // solo devuelve un area y una lista de usuarios de esta area
            responseMap.put("area",areaRepository.areasConUsuarios(idarea));
            return new ResponseEntity(responseMap, HttpStatus.OK);

        }catch (NumberFormatException e){
            responseMap.put("msg", "el  ID debe ser un número");
            responseMap.put("estado", "error");
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);

        }
    }

    //TODO no devuelve el id en base al fetchId <- ver por qué
    @PostMapping(value = "/area", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarArea(@RequestBody Area area,
                                      @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();

        System.out.println(fetchId);
        areaRepository.save(area);
        if (fetchId) {
            responseMap.put("id", area.getIdarea());
        }
        responseMap.put("estado", "creado");


        return new ResponseEntity(responseMap, HttpStatus.CREATED);

    }


    //TODO falta actualizar, borrar y mostrar


}
