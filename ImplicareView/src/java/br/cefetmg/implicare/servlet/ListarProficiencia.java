/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Proficiencia;
import br.cefetmg.implicare.model.serviceImpl.ProficienciaManagementImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class ListarProficiencia {

    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            ProficienciaManagementImpl ProficienciaImpl = new ProficienciaManagementImpl();
            List<Proficiencia> listAll = new ArrayList<>();
            listAll = ProficienciaImpl.listAll();
            
            if (listAll != null) {
                request.setAttribute("ListaProficiencia", listAll);
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
