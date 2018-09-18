/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.serviceImpl.FormacaoAcademicaManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class ExcluirFormacaoAcademica {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try{

            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Seq_Formacao = Integer.parseInt(request.getParameter("Seq_Formacao"));
            int Cod_Area_Estudo = Integer.parseInt(request.getParameter("Cod_Area_Estudo"));
            
            FormacaoAcademicaManagementImpl FormacaoImpl = new FormacaoAcademicaManagementImpl();  
            
            boolean Formacao = FormacaoImpl.delete(CPF, Seq_Formacao, Cod_Area_Estudo);

            if (Formacao =! false) {
                jsp="";
            } else {
                String Erro = "Ocorreu erro ao Excluir Formacao Academica!";
                jsp="/WEB-Pages/Erro.jsp";
                request.setAttribute("Erro", Erro);
            }
        } catch(Exception e) {
            e.printStackTrace();
            jsp="";
        }
        
        return jsp;
    }
    
}
