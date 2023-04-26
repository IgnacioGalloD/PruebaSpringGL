package com.nachito.demo.dto;

import com.nachito.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @Positive
    private Integer edad;
    @Email
    private String email;
    @NotBlank
    private String password;
    @Positive
    private Integer telefono;
    private Date created;
    private Date modified;


    public static UserDTO toDTO(User entity){   // Metodo estático para copiar entidad al DTO. Recibe User entity y devuelve UserDTO
        UserDTO dto = new UserDTO();            // Creo obj dto del tipo UserDTO
        dto.setId(entity.getIdentificador());
        dto.setApellido(entity.getApellido());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setTelefono(entity.getTelefono());
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
