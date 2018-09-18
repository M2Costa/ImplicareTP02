/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.ExperienciaProfissional;
import br.cefetmg.implicare.model.serviceImpl.ExperienciaProfissionalManagementImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarExperienciaProfissional {

    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            ExperienciaProfissionalManagementImpl ExperienciaImpl = new ExperienciaProfissionalManagementImpl();
            List<ExperienciaProfissional> Exp = new ArrayList<>();
            
            Long CPF= (Long) request.getSession().getAttribute("CPF_CNPJ");
            
            Exp = ExperienciaImpl.getExperienciasProfissionais(CPF);
 
            if (Exp != null) {
                request.setAttribute("ListaExperienciaProfissional", Exp);
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
