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

@ManagedBean(name = "Usuario")
@RequestScoped
public class UsuarioMB {
    
    private String JSF;
    
    public String insert(HttpServletRequest request) {
        return JSF;
    }
    
    public String update(HttpServletRequest request) {
        return JSF;
    }
    
    public String getUsuarioCod(HttpServletRequest request) {
        return JSF;
    }
    
    public String getLogin(HttpServletRequest request) {
        return JSF;
    }
    
}
