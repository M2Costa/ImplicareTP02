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

@ManagedBean(name = "CandidatoVagaDialogo")
@RequestScoped
public class CandidatoVagaDialogoMB {
    
    String JSP;
    
    public String insert(HttpServletRequest request){
        return JSP;
    }
    
    public String getCandidatoVagaDialogo(HttpServletRequest request){
        return JSP;
    }
    
    public String getCandidatoVagaDialogoCod(HttpServletRequest request){
        return JSP;
    }
    

}
