/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Estado;
import br.cefetmg.implicare.model.serviceImpl.EstadoManagementImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class ListarEstado {

    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            EstadoManagementImpl EstadoImpl = new EstadoManagementImpl();
            List<Estado> listAll = new ArrayList<>();
            listAll = EstadoImpl.listAll();
            
            if (listAll != null) {
                request.setAttribute("ListaEstado", listAll);
                jsp = "/listarcategoria.jsp";
            } else {
                String erro = "Nao existe registro!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
