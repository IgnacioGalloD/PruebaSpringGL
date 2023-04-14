package com.nachito.demo.controller;

import com.nachito.demo.dto.UserDTO;
import com.nachito.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private DemoService service;
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
    @RequestMapping(value="/usuario", method = RequestMethod.POST)
    public ResponseEntity<?> agregarUsuario(@RequestBody UserDTO usuario){ // Def un nuevo método que devuelve un obj de tipo responseEntity<UserDTO>
        usuario.setCreated(new Date());
        usuario.setModified(new Date());
        usuario.setLast_login(null);
        usuario.setIsactive(true);
        // Validar mail repetido
        if(service.elMailExiste(usuario.getEmail())) {
            return new ResponseEntity<>("Error: El correo ya registrado", HttpStatus.BAD_REQUEST);
        }
        // Validar pass
        else if(passInvalido(usuario.getPassword())){
            return new ResponseEntity<>("Error: Password debe tener mayúsculas, minúsculas 2 números", HttpStatus.BAD_REQUEST);
        }
        else {
            UserDTO usaurioCreado = service.addUser(usuario);
            return new ResponseEntity<>(usaurioCreado, HttpStatus.CREATED);
            //return new ResponseEntity<>("Usaurio creado", HttpStatus.CREATED);
        }
    }

    // Validar password
    private boolean passInvalido(String password) {
        char[] arr = password.toCharArray();
        int may=0,min=0,num=0;
        for (char c : arr) {
            if (c>='0'&&c<='9') num++;
            else if (c>='a'&&c<='z') min++;
            else if (c>='A'&&c<='Z') may++;
            System.out.println(c);
        }
        if(may==1&&min>0&&num==2) return false;
        else return true;
    }

    // ver un usuario
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUsuario(@RequestParam Integer id){
        return new ResponseEntity<>(service.getUser(id), HttpStatus.OK);
    }

            // Edita usuario
    @PutMapping(value = "/modificarusuario/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Integer id, @RequestBody UserDTO usuarioLeido){
        if(!service.existUser(id)){
            return new ResponseEntity<MensajeDeError>(new MensajeDeError(), HttpStatus.NOT_FOUND);
        }
        else{
            UserDTO usuarioModificado = service.getUser(id);
            usuarioModificado.setId(id);
            usuarioModificado.setNombre(usuarioLeido.getNombre());
            usuarioModificado.setApellido(usuarioLeido.getApellido());
            usuarioModificado.setEdad(usuarioLeido.getEdad());
            usuarioModificado.setEmail(usuarioLeido.getEmail());
            usuarioModificado.setPassword(usuarioLeido.getPassword());
            usuarioModificado.setTelefono(usuarioLeido.getTelefono());
            //usuarioModificado.setCreated(usuarioLeido.getCreated());
            usuarioModificado.setModified(new Date());
            //usuarioModificado.setLast_login(usuarioLeido.getLast_login());
            //return new ResponseEntity<UserDTO>(service.modiUser(usuario), HttpStatus.FOUND);
            usuarioModificado.setIsactive(true); // MODIFICAR ESTO !!
            return new ResponseEntity<UserDTO>(service.addUser(usuarioModificado), HttpStatus.FOUND);
        }
    }

            // Ver todos los usuarios
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public ResponseEntity<?> getUsuarios(){
        List<UserDTO> usuarios = null;
        usuarios = service.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
