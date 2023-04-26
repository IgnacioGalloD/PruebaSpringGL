package com.nachito.demo.service.impl;

import com.nachito.demo.domain.User;
import com.nachito.demo.dto.UserDTO;
import com.nachito.demo.exceptions.DemoNotFoundException;
import com.nachito.demo.repository.UserRepository;
import com.nachito.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoServiceImpl implements IDemoService {

    private final UserRepository repo;

    @Autowired
    public DemoServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String getUserName() {
        return repo.findById(1).orElseThrow(RuntimeException::new).getNombre();
    }

    //  AGREGAR NUEVO USUARIO   // EDITAR USUARIO
    @Override
    public UserDTO addUser(UserDTO usuario) throws Exception {

        // Validar mail repetido
        if(elMailExiste(usuario.getEmail())) {
            throw new Exception("El email ya existe");
        }
        // Validar pass
        if(passInvalido(usuario.getPassword())){
            throw new Exception("El pass es incorrecto");
        }

        User usuarioGuardado = repo.save(UserDTO.toEntity(usuario));
        return UserDTO.toDTO(usuarioGuardado);
    }

    @Override
    public UserDTO updateUser(Integer id, UserDTO usuario) throws DemoNotFoundException {

        User usuarioParaActualizar = repo.findById(id).orElseThrow(() -> new DemoNotFoundException("usuario no encontrado"));

        usuarioParaActualizar.setNombre(usuario.getNombre());
        usuarioParaActualizar.setEdad(usuario.getEdad());
        usuarioParaActualizar.setEmail(usuario.getEmail());
        usuarioParaActualizar.setPassword(usuario.getPassword());

        return UserDTO.toDTO(usuarioParaActualizar);
    }


    //  EXISTENCIA DE USUARIO
    private boolean existUser(Integer id){
        return repo.existsById(id);
    }

    //  OBTENER USUARIO
    @Override
    public UserDTO getUser(Integer id) throws DemoNotFoundException {
        User usuario = repo.findById(id).orElseThrow(() -> new DemoNotFoundException("usuario no encontrado"));
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

    @Override
    public List<UserDTO> getAllUsuarios() {
        //es lo mismo que hace el for de arriba
        return repo.findAll().stream().map(UserDTO::toDTO).collect(Collectors.toList());        // AY CARAMBA !
    }

    private boolean elMailExiste(String elMail) {
        return repo.findByEmail(elMail).isPresent();
    }

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


}
