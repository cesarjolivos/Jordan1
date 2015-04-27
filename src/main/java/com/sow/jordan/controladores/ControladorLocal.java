/*
 * Paquete que contiene los controladores del sistema.
 */
package com.sow.jordan.controladores;

import com.sow.jordan.agregarDatos.AgregarDatos;
import com.sow.jordan.modelos.*;
import com.sow.jordan.servicios.ServicioLocal;
import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Clase se encarga de conectar las vistas con los modelos del sistema, conecta
 * los locales y clases relacionadas con las vistas.
 * @author GARCÍA CASTRO HÉCTOR JAVIER
 * @author LARA RAMÍREZ JOSÉ JAVIER
 * @author OLIVOS NAVARRO CESAR JONATHAN
 * @author VILLEGAS MORENO ZEUXIS DANIEL
 */
@Controller("controladorLocal")
@Scope("session")
public class ControladorLocal implements Serializable{

    @Autowired
    private ServicioLocal servicioLocal;
    
    private List<Local> locales;
    private Local local;
    private List<Lugar> lugares;
    private Lugar lugar;
    private List<Servicio> servicios;
    private Servicio servicio;
    private Menu menu;
    private List<Transporte> transportes;
    private Transporte transporte;
    private MapModel mapa;
    private int posicion;
    private int id;
    private int idTransporte;
    private String tipo;
    private Comentario comentario;
    
    //private AgregarDatos agregarDatos;

    public int getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que se ejecuta después de realizar la inyección de dependencias.
     */
    @PostConstruct
    public void inicio() {
        //agregarDatos = new AgregarDatos(servicioLocal);
        servicios = servicioLocal.cargarServicios();
        lugares = servicioLocal.cargarLugares();
        transportes = servicioLocal.cargarTransportes();
        locales = servicioLocal.cargarLocales();
        local.setMenu(new ArrayList<Menu>());
        local.setTransportes(new ArrayList<Transporte>());
        mapa = new DefaultMapModel(); 
    }
    
    /**
     * Método que guarda un local en la base de datos.
     */
    public void guardarLocal() {
        lugar = servicioLocal.buscarLugar(id);
        local.setLugar(lugar);
        servicioLocal.guardarLocal(local);
        locales = servicioLocal.cargarLocales();
        local = new Local();
        local.setMenu(new ArrayList<Menu>() );
        local.setTransportes(new ArrayList<Transporte>() );
        local.setComentario( new ArrayList<Comentario>() );
    }
    
    /**
     * Método que guarda en la base de datos un lugar.
     */
    public void guardarLugar(){
        servicioLocal.guardarLugar(lugar);
        lugares = servicioLocal.cargarLugares();
        //agregarDatos.serializarLugares(lugares);
        lugar = new Lugar();
    }
    
    /**
     * Método que guarda los menús de un local.
     */
    public void guardarMenu() {
        local.getMenu().add(menu);
        menu = new Menu();
    }
    
    public void guardarComentario() {
        //comentario.setUsuario(null);
        local.getComentarios().add(comentario);
        comentario = new Comentario();
    }
    
    /**
     * Métrodo que guarda en la base de datos un servicio.
     */
    public void guardarServicio() {
        servicioLocal.guardarSercivio(servicio);
        servicios = servicioLocal.cargarServicios();
        //agregarDatos.serializarServicios(servicios);
        servicio = new Servicio();
    }
    
    /**
     * Método que agrega un transporte a un local.
     */
    public void guardarTransporte() {
        transporte = servicioLocal.buscarTransporte(idTransporte);
        local.getTransportes().add(transporte);
        transporte = new Transporte();
    }
    
    /**
     * Método que guarda en la base de datos un transporte.
     */
    public void agregarTransporte(){
        servicioLocal.guardarTransporte(transporte);
        transportes = servicioLocal.cargarTransportes();
        //agregarDatos.serializarTransportes(transportes);
        transporte = new Transporte();
    }
    
    /**
     * Método que elimina un local de la base de datos.
     * @param local El local a eliminar.
     */
    public void eliminarLocal(Local local) {
        servicioLocal.eliminarLocal(local);
        locales = servicioLocal.cargarLocales();
    }
    
    /**
     * Método que elimina un menú de un local.
     * @param menu El menú a aliminar.
     */
    public void eliminarMenu(Menu menu) {
        this.local.getMenu().remove(menu);
    }
    
    /**
     * Método que elimina un transporte de un local.
     * @param transporte El transporte a aliminar.
     */
    public void eliminarTransporte(Transporte transporte) {
        this.local.getTransportes().remove(transporte);
    }
    
    /**
     * Método que busca un local en la base de datos para mostrar su información
     * @param idLocal El id del local.
     */
    public void verLocal(int idLocal) {
        this.id = idLocal;
        this.local = servicioLocal.buscarLocal(idLocal);
    }
    
    public List<String> getTipos() {
        return servicioLocal.tipos();
    }
    
    public void buscarTransporte(){
        transportes = servicioLocal.porTipos(this.tipo);
    }
    
    /**
     * Método para guardar la imagen del local.
     * @param event El evento que se activa al agregar la imagen.
     */
    public void imagenLocal(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        this.local.setImagen(file.getContents());
    }
    
    /**
     * Método que guarda la imagen de un servicio.
     * @param event El evento que se activa al agregar la imagen.
     */
    public void imagenServicio(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        this.servicio.setImagen(file.getContents());
    }

    /**
     * Método que regresa la lista de locales.
     * @return Una lista con la información.
     */
    public List<Local> getLocales() {
        return locales;
    }
    
    /**
     * Método para agregar un local a la lista.
     * @param locales Una lista con la información.
     */
    public void setLocales(List<Local> locales) {
        this.locales = locales;
    }

    /**
     * Método que regresa un local.
     * @return Un local.
     */
    public Local getLocal() {
        return local;
    }

    /**
     * Método que asigna un local.
     * @param local Un local.
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * Método que regresa los lugares.
     * @return Una lista con la información.
     */
    public List<Lugar> getLugares() {
        return lugares;
    }

    /**
     * Método que agrega un lugar.
     * @param lugares Una lista con la información.
     */
    public void setLugares(List<Lugar> lugares) {
        this.lugares = lugares;
    }

    /**
     * Método que regresa un lugar.
     * @return Un local.
     */
    public Lugar getLugar() {
        return lugar;
    }

    /**
     * Método que asigna un lugar.
     * @param lugar El nuevo lugar.
     */
    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    /**
     * Método que regresa un menú.
     * @return Un menú.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Método para agregar un menú.
     * @param menu El nuevo menú.
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * Método que regresa los servicios de los locales.
     * @return Una lista con la información.
     */
    public List<Servicio> getServicios() {
        return servicios;
    }

    /**
     * Método que agrega un servicio.
     * @param servicios Una lista con la nueva información
     */
    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    /**
     * Método que regresa un servicio.
     * @return Un servicio.
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * Método que asigna un nuevo servicio.
     * @param servicio El nuevo servicio.
     */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    /**
     * Método que regresa los tranportes que hay en CU.
     * @return Una lista con la información.
     */
    public List<Transporte> getTransportes() {
        return transportes;
    }

    /**
     * Método que agrega un nuevo transporte.
     * @param transportes Una lista con la nueva información
     */
    public void setTransportes(List<Transporte> transportes) {
        this.transportes = transportes;
    }

    /**
     * Método que regresa un transporte.
     * @return Un transporte.
     */
    public Transporte getTransporte() {
        return transporte;
    }

    /**
     * Método que asigna un nuevo transporte.
     * @param transporte El n uevo transporte.
     */
    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    /**
     * Método que regresa la posición que ocupa un local en la lista de top 5.
     * @return Un entero con la información.
     */
    public int getPosicion() {
        return posicion++;
    }
    
    /**
     * Método que regresa un id.
     * @return Un entero con la informcaión.
     */
    public int getId() {
        return id;
    }

    /**
     * Método que guarda un id.
     * @param id El nuevo id.
     */
    public void setId(int id) {
        this.id = id;
    }    
    
    public MapModel getMapa() {
        mapa = new DefaultMapModel(); 
        LatLng coord = new LatLng(local.getLatitud(), local.getLongitud()); 
        mapa.addOverlay(new Marker(coord, local.getNombre()));
        return mapa;
    }

    public void setMapa(MapModel mapa) {
        this.mapa = mapa;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
    
    /**
     * Método que elimina el resgistro.
     */
    public void limpiar(){
        local = new Local();
    }
    
    /**
     * Método que regresa la lista de lugares con mejor calificación.
     * @return Una lista de los mejores 5 locales.
     */
    public List<Local> getTop5() {
        posicion = 1;
        return locales;
    }
    /**
    public void verLocal(String nombreLocal){
        this.local = servicioLocal.buscarLocal(nombreLocal);
    }
    */
}
