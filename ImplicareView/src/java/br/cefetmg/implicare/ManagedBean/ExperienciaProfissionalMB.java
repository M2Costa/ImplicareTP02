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

@ManagedBean(name = "ExperienciaProfissional")
@RequestScoped
public class ExperienciaProfissionalMB {
    
    private String JSF;
    
    public String insert(HttpServletRequest request) {
        return JSF;
    }
    
    public String update(HttpServletRequest request) {
        return JSF;
    }
    
    public String delete(HttpServletRequest request) {
        return JSF;
    }
    
    public String getExperienciasProfissionais(HttpServletRequest request) {
        return JSF;
    }
    
    public String getExperienciaProfissionalCod(HttpServletRequest request) {
        return JSF;
    }
    
}
