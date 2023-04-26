package com.nachito.demo.service;

import com.nachito.demo.dto.UserDTO;
import com.nachito.demo.exceptions.DemoNotFoundException;

import java.util.List;

public interface IDemoService {
    String getUserName();

    UserDTO addUser(UserDTO usuario) throws Exception;

    UserDTO getUser(Integer id) throws DemoNotFoundException;

    UserDTO updateUser(Integer id, UserDTO usuarioLeido) throws DemoNotFoundException;

    List<UserDTO> getAllUsuarios();
}
