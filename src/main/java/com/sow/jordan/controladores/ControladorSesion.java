/*
 * 
 */
package com.sow.jordan.controladores;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

/**
 * Clase se encarga de conectar las vistas con los modelos del sistema, conecta
 * a los usuarios del sistema con las vistas.
 * @author GARCÍA CASTRO HÉCTOR JAVIER
 * @author LARA RAMÍREZ JOSÉ JAVIER
 * @author OLIVOS NAVARRO CESAR JONATHAN
 * @author VILLEGAS MORENO ZEUXIS DANIEL
 */
@Controller("controladorLogeo")
@Scope("request")
public class ControladorSesion implements Serializable {
    
    private boolean sesionIniciada = false;

    private String nombre;

    private String privilegio;

    @PostConstruct
    public void inicia() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken) && (user instanceof UserDetails)) {
            nombre = ((UserDetails) user).getUsername();
            if (auth.getAuthorities().size() > 0) {
                privilegio = auth.getAuthorities().iterator().next().getAuthority();
            }
            sesionIniciada = true;
        } else if (user instanceof String) {
            nombre = user.toString();
            sesionIniciada = false;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrivilegio() {
        return privilegio;
    }
    
    public boolean getSesionIniciada(){
        return sesionIniciada;
    }
    
    public String url(){
        switch (privilegio) {
            case "ROLE_ROOT":
                return "superAdministrador.xhtml";
            case "ROLE_ADMIN":
                return "administrador.xhtml";
        }
        return null;
    }
    
    public boolean esAdministrador(){
        switch (privilegio) {
            case "ROLE_ADMIN":
                return true;
            case "ROLE_ROOT":
                return true;
        }
        return false;
    }
    
}
