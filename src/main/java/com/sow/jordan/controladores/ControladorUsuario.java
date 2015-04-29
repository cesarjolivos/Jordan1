/*
 * Paquete que contiene los controladores del sistema Jordan
 */
package com.sow.jordan.controladores;

import com.sow.jordan.agregarDatos.AgregarDatos;
import com.sow.jordan.modelos.Comentario;
import com.sow.jordan.modelos.Usuario;
import com.sow.jordan.servicios.ServicioUsuario;
import java.io.*;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Clase se encarga de conectar las vistas con los modelos del sistema, conecta
 * a los usuarios del sistema con las vistas.
 * @author GARCÍA CASTRO HÉCTOR JAVIER
 * @author LARA RAMÍREZ JOSÉ JAVIER
 * @author OLIVOS NAVARRO CESAR JONATHAN
 * @author VILLEGAS MORENO ZEUXIS DANIEL
 */
@Controller("controladorUsuario")
@Scope("session")
public class ControladorUsuario implements Serializable {
    
    @Autowired
    private ServicioUsuario servicioUsuario;
    
    private Usuario usuario;
    
    private List<Usuario> usuarios;
    
    private AgregarDatos agregarDatos;

    @PostConstruct
    public void inicia() {
        agregarDatos = new AgregarDatos(servicioUsuario);
        usuarios = servicioUsuario.cargarUsuarios();
    }
    
    public void guardarUsuario(){
        this.servicioUsuario.guardarUsuario(usuario);
        usuarios = servicioUsuario.cargarUsuarios();
        //agregarDatos.serializarUsuarios(usuarios);
        this.usuario = new Usuario();
    }
    
    public void guardarComentario(String usuario,Comentario comentario) {
        this.usuario = servicioUsuario.buscarUsuario(usuario);
        comentario.setUsuario(this.usuario);
        this.usuario.getComentarios().add(comentario);
        guardarUsuario();
        
    }
    
    public void eliminarUsuario(Usuario usuario){
        this.servicioUsuario.eliminarUsuario(usuario);
        usuarios = servicioUsuario.cargarUsuarios();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
}
