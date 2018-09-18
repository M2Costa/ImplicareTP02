/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Competencia;
import br.cefetmg.implicare.model.serviceImpl.CompetenciaManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class PesquisarCompetencia {
    
    static String execute(HttpServletRequest request) {
        String jsp="";
        try{
            int Cod_Competencia = Integer.parseInt(request.getParameter("Cod_Competencia"));
            
            CompetenciaManagementImpl CompetenciaImpl = new CompetenciaManagementImpl();
            Competencia Comp = new Competencia();
            
            Comp = CompetenciaImpl.getCompetenciaCod(Cod_Competencia);

            if (Comp == null) {
                jsp="/WEB-Pages/Erro.jsp";
                request.setAttribute("Erro","Competencia Nao Encontrado");
            } else {
                jsp="/Web-Pages/VisualizarEstoque.jsp";
                request.setAttribute("Competencia", Comp);
            }
        } catch(Exception e) {
            e.printStackTrace();
            jsp="";
        }
        return jsp;
    }
    
}
