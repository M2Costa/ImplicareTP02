/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Proficiencia;
import br.cefetmg.implicare.model.serviceImpl.ProficienciaManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class PesquisarProficiencia {
    
    static String execute(HttpServletRequest request) {
        String jsp="";
        try{
            int Cod_Proficiencia = Integer.parseInt(request.getParameter("Cod_Proficiencia"));
            
            ProficienciaManagementImpl ProficienciaImpl = new ProficienciaManagementImpl();
            Proficiencia Prof = new Proficiencia();
            
            Prof = ProficienciaImpl.getProficienciaCod(Cod_Proficiencia);

            if (Prof == null) {
                jsp="/WEB-Pages/Erro.jsp";
                request.setAttribute("Erro","Proficiencia Nao Encontrado");
            } else {
                jsp="/Web-Pages/VisualizarEstoque.jsp";
                request.setAttribute("Proficiencia", Prof);
            }
        } catch(Exception e) {
            e.printStackTrace();
            jsp="";
        }
        return jsp;
    }
    
}
