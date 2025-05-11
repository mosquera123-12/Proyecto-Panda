package com.panda.pandaapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

/**
 * Entidad que representa un usuario del sistema de facturación e inventario.
 * Esta clase se mapea a la tabla 'usuarios' en la base de datos.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;
    
    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String usuario;
    
    @Column(nullable = false, unique = true, length = 100)
    private String correo;
    
    @Column(nullable = false, length = 60) // Para almacenar contraseñas encriptadas
    private String contrasena;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre, String usuario, String correo, String contrasena) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public int getId_usuario() {
        return this.id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
}

    
   
   