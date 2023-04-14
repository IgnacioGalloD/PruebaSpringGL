package com.nachito.demo.service;

import com.nachito.demo.domain.User;
import com.nachito.demo.dto.UserDTO;
import com.nachito.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoService {

    @Autowired
    private UserRepository repo;

    public String getUserName() {
        return repo.findById(1).orElseThrow(RuntimeException::new).getNombre();
    }

    //  AGREGAR NUEVO USUARIO   // EDITAR USUARIO
    public UserDTO addUser(UserDTO usuario) {
        User usuarioGuardado = repo.save(UserDTO.toEntity(usuario));
        return UserDTO.toDTO(usuarioGuardado);
    }

    //  EXISTENCIA DE USUARIO
    public boolean existUser(Integer id){
        return repo.existsById(id);
    }

    //  OBTENER USUARIO
    public UserDTO getUser(Integer id) {
        User usuario = repo.findById(id).orElseThrow(RuntimeException::new);
        return UserDTO.toDTO(usuario);
    }
/*
    // EDITAR USUARIO
    public UserDTO modiUser(UserDTO usuario){
        User usuarioModificado = repo.save(UserDTO.toEntity(usuario));
        return UserDTO.toDTO(usuarioModificado);
    }

 */

/*
    public List<UserDTO> getUsuariosQueContengan(String nombre) {
        List<User> users = repo.findByNombreContainingOrderByEdadAsc(nombre);
        List<UserDTO> usuarios = new ArrayList<>();
        for(User user:users){
            usuarios.add(UserDTO.toDTO(user));
        }
        return usuarios;
    }
*/


    public List<UserDTO> getAllUsuarios() {
        //es lo mismo que hace el for de arriba
        return repo.findAll().stream().map(UserDTO::toDTO).collect(Collectors.toList());        // AY CARAMBA !
    }

    public boolean elMailExiste(String elMail) {
        if(repo.findByEmail(elMail).isEmpty()){
            // No encontró el mail
            //System.out.println("Vacío: "+repo.findByEmail(elMail).isEmpty());
            return false;
        }
        else {
            //Encontró el mail
            //System.out.println("Vacío: "+repo.findByEmail(elMail).isEmpty());
            return true;
        }
    }


}
