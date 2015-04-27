/*
 * Paquete en el que se almacenan los modelos del sistema jordan.
 */
package com.sow.jordan.modelos;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Clase que servirá como un catalogo de los tranportes que hay dentro de Ciudad
 * Universitaria, crea una tabla para guardar la información en la base de datos.
 * @author GARCÍA CASTRO HÉCTOR JAVIER
 * @author LARA RAMÍREZ JOSÉ JAVIER
 * @author OLIVOS NAVARRO CESAR JONATHAN
 * @author VILLEGAS MORENO ZEUXIS DANIEL
 */
@Entity
@Table(name="Transporte")
public class Transporte implements Serializable {
    
    /**
     * Variable que indica el id del transporte, dicho valor se auto-incrementa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;    
    
    /**
     * Variable que almacena el tipo de transporte, dicho valor no puede ser nulo.
     */
    @NotNull
    private String tipo;
    
    /**
     * Variable que almacena la estación del tranporte, dicho valor no puede ser nulo.
     */
    @NotNull
    private String estacion;

    /**
     * Método que regresa el id del tranporte.
     * @return un entero con la información.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que asigna un nuevo id.
     * @param id El nuevo valor del id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método que regresa el tipo de transporte.
     * @return Una cadena con la información.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método que asigna un nuevo tipo de transporte.
     * @param tipo La nueva categoria.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Método que regresa la estación del tranporte.
     * @return Una cadena con la informaión.
     */
    public String getEstacion() {
        return estacion;
    }

    /**
     * Método que asisgna la nueva estación.
     * @param estacion La nueva estación.
     */
    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.tipo);
        hash = 61 * hash + Objects.hashCode(this.estacion);
        return hash;
    }
    
}