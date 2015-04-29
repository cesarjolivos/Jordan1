/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sow.jordan.controladores;

import com.sow.jordan.modelos.Local;
import com.sow.jordan.servicios.ServicioLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author HectorJavier
 */
@Controller
@Scope("request")
public class ConsultaLocalUrl {
    @Autowired
    private ServicioLocal localService;
    
    public Local getLocal(Integer id){
        return  localService.findById(id);
    }
}
