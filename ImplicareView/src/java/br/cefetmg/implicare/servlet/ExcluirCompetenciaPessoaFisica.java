/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.serviceImpl.CompetenciaPessoaFisicaManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class ExcluirCompetenciaPessoaFisica {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try{

            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Cod_Competencia = Integer.parseInt(request.getParameter("Cod_Competencia"));
            CompetenciaPessoaFisicaManagementImpl CompetenciaPessoaImpl = new CompetenciaPessoaFisicaManagementImpl();        
            boolean Competencia = CompetenciaPessoaImpl.delete(CPF, Cod_Competencia);

            if (Competencia =! false) {
                jsp="";
            } else {
                String Erro = "Ocorreu erro ao Excluir Competencia Pessoa Fisica!";
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
