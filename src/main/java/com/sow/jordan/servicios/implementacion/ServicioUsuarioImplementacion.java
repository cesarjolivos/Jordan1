/*
 * Paquete en el que se implementan los Servicios
 */
package com.sow.jordan.servicios.implementacion;

import com.sow.jordan.modelos.Usuario;
import com.sow.jordan.repositorios.RepositorioUsuario;
import com.sow.jordan.servicios.ServicioUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase que implementa los servicios de usuario.
 * @author GARCÍA CASTRO HÉCTOR JAVIER
 * @author LARA RAMÍREZ JOSÉ JAVIER
 * @author OLIVOS NAVARRO CESAR JONATHAN
 * @author VILLEGAS MORENO ZEUXIS DANIEL
 */
public class ServicioUsuarioImplementacion implements ServicioUsuario {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public void guardarUsuario(Usuario usuario) {
        repositorioUsuario.save(usuario);
    }
    
    @Override
    public List<Usuario> cargarUsuarios() {
        return repositorioUsuario.cargarUsuarios();
    }
    
    @Override
    public void eliminarUsuario(Usuario usuario) {
        repositorioUsuario.delete(usuario);
    }
    
}
