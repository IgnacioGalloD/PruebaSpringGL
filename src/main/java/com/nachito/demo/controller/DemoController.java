package com.nachito.demo.controller;

import com.nachito.demo.dto.UserDTO;
import com.nachito.demo.exceptions.DemoNotFoundException;
import com.nachito.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private IDemoService service;
/*
    @GetMapping(value = "/salala")
    public ResponseEntity<String> pruebaTexto(){
        //String textoDevuelto = "Esto es un texto que voy a devolver";
        //return textoDevuelto;
        return new ResponseEntity<String>("Esto es un texto", HttpStatus.OK);
    }

    @GetMapping(value = "/testerror")
    public ResponseEntity<?> dameNombre(@RequestParam Integer id){
        if(!service.existUser(id)){
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return new ResponseEntity<MensajeDeError>(new MensajeDeError(), HttpStatus.BAD_REQUEST);  // FEO
        }
        else {
            return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
        }
    }
*/


    @RequestMapping(value = "/nombre", method = RequestMethod.GET)
    public String getNombre(){                                          // Defino un nuevo método. No me importa mucho el nombre
        String returnedValue = service.getUserName();                   // La variable returnedValue adquiere en nombre de usuario
        return returnedValue;                                           // Y lo retorna
    }

            // Alta usuario
    @PostMapping(value="/usuario")
    public ResponseEntity<UserDTO> agregarUsuario(@RequestBody @Valid UserDTO usuario) throws Exception { // Def un nuevo método que devuelve un obj de tipo responseEntity<UserDTO>
        return new ResponseEntity<>(service.addUser(usuario), HttpStatus.CREATED);
    }

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<UserDTO> getUsuarioPorId(@PathVariable Integer id) throws DemoNotFoundException {
        return new ResponseEntity<UserDTO>(service.getUser(id), HttpStatus.OK);
    }


    // ver un usuario
    @GetMapping(value = "/usuario")
    public ResponseEntity<UserDTO> getUsuario(@RequestParam Integer id) throws DemoNotFoundException {
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }

            // Edita usuario
    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Integer id, @RequestBody @Valid UserDTO usuarioLeido) throws Exception {
       return new ResponseEntity<UserDTO>(service.updateUser(id, usuarioLeido), HttpStatus.OK);
    }

            // Ver todos los usuarios
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public ResponseEntity<?> getUsuarios(){
        List<UserDTO> usuarios = null;
        usuarios = service.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @ExceptionHandler(DemoNotFoundException.class)
    public ResponseEntity<String> handleDemoNotFoundException(DemoNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
