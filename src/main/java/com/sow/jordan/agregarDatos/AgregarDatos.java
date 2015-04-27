/*
 * Paquete que contiene a la clase que se encarga de agregar datos del sistema.
 */
package com.sow.jordan.agregarDatos;

import com.sow.jordan.modelos.*;
import com.sow.jordan.servicios.ServicioLocal;
import com.sow.jordan.servicios.ServicioUsuario;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.*;

/**
 * Clase que se encagra de agregar datos.
 * @author OLivos Navarro Cesar J.
 */
public class AgregarDatos {
    
    private ServicioLocal servicioLocal;
    private ServicioUsuario servicioUsuario;
    private String ruta;
    
    public AgregarDatos(ServicioLocal servicioLocal) {
        this.servicioLocal = servicioLocal;
        ruta = "/home/olivos/Documentos/SistemaJordan/";
        cargarServiciosSerializados(ruta);
        cargarLugaresSerializados(ruta);
        cargarTransportesSerializados(ruta);
    }
    
    public AgregarDatos(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
        ruta = "/home/olivos/Documentos/SistemaJordan/";
        cargarUsuariosSerializados(ruta);
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    /**
     * Método que serializa los datos.
     * @param servicios
     */
    public void serializarServicios(List<Servicio> servicios){
        try {
            FileOutputStream file = new FileOutputStream(ruta+"/Servicios.dat");
            ObjectOutputStream escritor;
            escritor = new ObjectOutputStream(file);
            Iterator<Servicio> it = servicios.iterator();
            while (it.hasNext()) {
                escritor.writeObject(it.next());
            }
            escritor.close();
        } catch (IOException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido actualizar los datos correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
        }
    }
    
    public void serializarLugares(List<Lugar> lugares){
        try {
            FileOutputStream file = new FileOutputStream(ruta+"/Lugares.dat");
            ObjectOutputStream escritor;
            escritor = new ObjectOutputStream(file);
            Iterator<Lugar> it = lugares.iterator();
            while (it.hasNext()) {
                escritor.writeObject(it.next());
            }
            escritor.close();
        } catch (IOException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido actualizar los datos correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
        }
    }
    
    public void serializarTransportes(List<Transporte> transportes){
        try {
            FileOutputStream file = new FileOutputStream(ruta+"/Transportes.dat");
            ObjectOutputStream escritor;
            escritor = new ObjectOutputStream(file);
            Iterator<Transporte> it = transportes.iterator();
            while (it.hasNext()) {
                escritor.writeObject(it.next());
            }
            escritor.close();
        } catch (IOException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido actualizar los datos correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
        }
    }
    
    public void serializarUsuarios(List<Usuario> usuarios){
        try {
            FileOutputStream file = new FileOutputStream(ruta+"/Usuarios.dat");
            ObjectOutputStream escritor;
            escritor = new ObjectOutputStream(file);
            Iterator<Usuario> it = usuarios.iterator();
            while (it.hasNext()) {
                escritor.writeObject(it.next());
            }
            escritor.close();
        } catch (IOException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido actualizar los datos correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
        }
    }
    
    public void cargarServiciosSerializados(String ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta+"/Servicios.dat");
            ObjectInputStream lector;
            lector = new ObjectInputStream(file);
            while (true) {
                try {
                    Servicio servicio = (Servicio) lector.readObject();
                    servicioLocal.guardarSercivio(servicio);
                } catch (EOFException eof) {
                    break;
                } catch (ClassNotFoundException cne) {
                    System.err.println("Error de clase no encontrada");
                }
            }
            lector.close();
        } catch (FileNotFoundException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido cargar  datos de los usuarios correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
            System.exit(1);
        } catch (IOException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido cargar los empleados correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    public void cargarLugaresSerializados(String ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta+"/Lugares.dat");
            ObjectInputStream lector;
            lector = new ObjectInputStream(file);
            while (true) {
                try {
                    Lugar lugar = (Lugar) lector.readObject();
                    servicioLocal.guardarLugar(lugar);
                } catch (EOFException eof) {
                    break;
                } catch (ClassNotFoundException cne) {
                    System.err.println("Error de clase no encontrada");
                }
            }
            lector.close();
        } catch (FileNotFoundException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido cargar  datos de los usuarios correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
            System.exit(1);
        } catch (IOException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido cargar los empleados correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    public void cargarTransportesSerializados(String ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta+"/Transportes.dat");
            ObjectInputStream lector;
            lector = new ObjectInputStream(file);
            while (true) {
                try {
                    Transporte transporte = (Transporte) lector.readObject();
                    servicioLocal.guardarTransporte(transporte);
                } catch (EOFException eof) {
                    break;
                } catch (ClassNotFoundException cne) {
                    System.err.println("Error de clase no encontrada");
                }
            }
            lector.close();
        } catch (FileNotFoundException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido cargar  datos de los usuarios correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
            System.exit(1);
        } catch (IOException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido cargar los empleados correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    public void cargarUsuariosSerializados(String ruta) {
        try {
            FileInputStream file = new FileInputStream(ruta+"/Usuarios.dat");
            ObjectInputStream lector;
            lector = new ObjectInputStream(file);
            while (true) {
                try {
                    Usuario usuario = (Usuario) lector.readObject();
                    servicioUsuario.guardarUsuario(usuario);
                } catch (EOFException eof) {
                    break;
                } catch (ClassNotFoundException cne) {
                    System.err.println("Error de clase no encontrada");
                }
            }
            lector.close();
        } catch (FileNotFoundException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido cargar  datos de los usuarios correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
            System.exit(1);
        } catch (IOException ex) {
            showMessageDialog(null,
                    "Ha ocurrido un error, no se han podido cargar los empleados correctamente"
                    + "\nAsegurate de que el archivo exista y se encuentre en el directorio correcto.",
                    "Error irrecuperable", ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
}