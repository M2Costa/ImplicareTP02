/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Estado;
import br.cefetmg.implicare.model.serviceImpl.EstadoManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class PesquisarEstado {
    
    static String execute(HttpServletRequest request) {
        String jsp="";
        try{
            int Cod_Estado = Integer.parseInt(request.getParameter("Cod_Estado"));
            
            EstadoManagementImpl EstadoImpl = new EstadoManagementImpl();
            Estado Est = new Estado();
            
            Est = EstadoImpl.getEstadoCod(Cod_Estado);

            if (Est == null) {
                jsp="/WEB-Pages/Erro.jsp";
                request.setAttribute("Erro","Estado Nao Encontrado");
            } else {
                jsp="/Web-Pages/VisualizarEstoque.jsp";
                request.setAttribute("Cargo", Est);
            }
        } catch(Exception e) {
            e.printStackTrace();
            jsp="";
        }
        return jsp;
    }
    
}
