/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.FormacaoAcademica;
import br.cefetmg.implicare.model.serviceImpl.FormacaoAcademicaManagementImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarFormacaoAcademica {
    
    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            FormacaoAcademicaManagementImpl FormacaoAcademicaImpl = new FormacaoAcademicaManagementImpl();
            List<FormacaoAcademica> Formacao = new ArrayList<>();
            
            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            
            Formacao = FormacaoAcademicaImpl.getFormacaoAcademica(CPF);
 
            if (Formacao != null) {
                request.setAttribute("ListaFormacaoAcademica", Formacao);
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
