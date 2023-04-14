package com.nachito.demo.dto;

import com.nachito.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String email;
    private String password;
    private Integer telefono;
    private Date created;
    private Date modified;
    private Date last_login;
    private boolean isactive;


    public static UserDTO toDTO(User entity){   // Metodo estático para copiar entidad al DTO. Recibe User entity y devuelve UserDTO
        UserDTO dto = new UserDTO();            // Creo obj dto del tipo UserDTO
        dto.setId(entity.getIdentificador());
        dto.setApellido(entity.getApellido());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setTelefono(entity.getTelefono());
        dto.setCreated(entity.getCreated());
        dto.setModified(entity.getModified());
        dto.setLast_login(entity.getLast_login());
        dto.setIsactive(entity.isIsactive());
        return dto;                             // Devuelvo el objeto dto
    }

    public static User toEntity(UserDTO dto){   // Copio DTO a la entidad
        User entity = new User();
        entity.setIdentificador(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setEdad(dto.getEdad());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setTelefono(dto.getTelefono());
        entity.setCreated(dto.getCreated());
        entity.setModified(dto.getModified());
        entity.setLast_login(dto.getLast_login());
        entity.setIsactive(dto.isIsactive());
        return entity;
    }

        /*
    public UserDTO(String nombre, String apellido, Integer edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public UserDTO() {}

     */


    /*
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

 */
}
