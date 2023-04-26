package com.nachito.demo.controller;

import com.nachito.demo.dto.UserDTO;
import com.nachito.demo.exceptions.DemoNotFoundException;
import com.nachito.demo.service.impl.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class DemoApiController {

    @Autowired
    private DemoServiceImpl service;

    @RequestMapping(value = "/nombre", method = RequestMethod.GET)
    public String getNombre(){
        String returnedValue = service.getUserName();
        return "/api"+returnedValue;
    }

    @RequestMapping(value="/usuario", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> agregarUsuario(@RequestBody UserDTO usuario) throws Exception {
        UserDTO usaurioCreado = service.addUser(usuario);
        return new ResponseEntity<>(usaurioCreado, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUsuario(@RequestParam Integer id) throws DemoNotFoundException {
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }
}
