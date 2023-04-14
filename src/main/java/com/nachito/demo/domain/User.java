package com.nachito.demo.domain;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter @Setter
@Entity
@Table(name = "usuario")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;
    @Column @NotBlank @NotEmpty @NotNull
    private String nombre;
    @Column @NotBlank  @NotEmpty @NotNull
    private String apellido;
    @Column @NotNull
    private Integer edad;
    @Column @Email
    private String email;
    @Column @NotBlank
    private String password;
    @Column @NotNull
    private int telefono;
/*
    @Column
    private List<Telefono> tel = new ArrayList<Telefono>();
 */
    @Column
    private Date created;
    @Column
    private Date modified;
    @Column
    private Date last_login;
    @Column
    private boolean isactive;

/*
    @Column(nullable=false)
    private boolean isHombre;

     */

    /*
    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

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
