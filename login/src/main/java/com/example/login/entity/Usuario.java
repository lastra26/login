package com.example.login.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
//import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table (name = "usuariosLogin")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    private String apellido;

    @Email
    private String email;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Past
    @NotNull
    @Column (name="create_at")
    //@Temporal (TemporalType.TIMESTAMP) //hace que se transforme el formato con el que se va guardar en la bd existe el time que da la hora
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createAt;

    private String foto;

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public Usuario(Long id, String nombre, String apellido, String email, String username, String password, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.username = username;
        this.password = password;
        this.foto = foto;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void getId(Long id){
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    private static final long serialVersionUID = 1L;
}
