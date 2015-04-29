/*
 * Paquete que contiene los servicios.
 */
package com.sow.jordan.servicios;

import com.sow.jordan.modelos.Usuario;
import java.util.List;

/**
 * Interfaz de los servicios de los usuarios.
 * @author GARCÍA CASTRO HÉCTOR JAVIER
 * @author LARA RAMÍREZ JOSÉ JAVIER
 * @author OLIVOS NAVARRO CESAR JONATHAN
 * @author VILLEGAS MORENO ZEUXIS DANIEL
 */
public interface ServicioUsuario {
    
    public void guardarUsuario(Usuario usuario);
    
    List<Usuario> cargarUsuarios();
    
    public void eliminarUsuario(Usuario usuario);
    
    Usuario buscarUsuario(String id);
    
}
