/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.ManagedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */

@ManagedBean(name = "AreaEstudo")
@RequestScoped
public class AreaEstudoMB {
    
    private String JSF;
    
    public String listAll(HttpServletRequest request){
        return JSF;
    }
    
    public String getAreaEstudoCod(HttpServletRequest request){
        return JSF;
    }
    
}
