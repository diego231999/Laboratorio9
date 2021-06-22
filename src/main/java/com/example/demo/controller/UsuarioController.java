package com.example.demo.controller;

import com.example.demo.entity.Usuario;
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
public class UsuarioController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity gestionExcepcion(HttpServletRequest request) {

        HashMap<String, Object> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un usuario");
        }
        return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
    }


    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listarProductos(){
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarProducto(
            @RequestBody Usuario usuario,
            @RequestParam(value = "fetchCorreo", required = false) boolean fetchCorreo) {

        HashMap<String, Object> responseMap = new HashMap<>();

        usuarioRepository.save(usuario);
        if (fetchCorreo) {
            responseMap.put("id", usuario.getCorreo());
        }
        responseMap.put("estado", "creado");
        return new ResponseEntity(responseMap, HttpStatus.CREATED);

    }

    @PutMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity actualizarProducto(@RequestBody Usuario usuario) {

        HashMap<String, Object> responseMap = new HashMap<>();

        if (!usuario.getCorreo().isEmpty()) {
            Optional<Usuario> opt = usuarioRepository.findByCorreoEquals(usuario.getCorreo());
            if (opt.isPresent()) {
                usuarioRepository.save(usuario);
                responseMap.put("estado", "actualizado");
                return new ResponseEntity(responseMap, HttpStatus.OK);
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "El correo del usuario a actualizar no existe");
                return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
            }
        } else {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe indicar el correo del usuario");
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/usuario/{correo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity borrarProducto(@PathVariable("correo") String correo) {

        HashMap<String, Object> responseMap = new HashMap<>();


            if (usuarioRepository.existsByCorreoEquals(correo)) {
                Optional<Usuario> opt = usuarioRepository.findByCorreoEquals(correo);
                Usuario usuario = opt.get();
                usuarioRepository.delete(usuario);
                responseMap.put("estado", "borrado exitoso");
                return new ResponseEntity(responseMap, HttpStatus.OK);
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "no se encontró el usuario con correo: " + correo);
                return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
            }

    }





}

