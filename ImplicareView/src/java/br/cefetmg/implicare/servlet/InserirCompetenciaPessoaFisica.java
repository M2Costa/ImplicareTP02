/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.CompetenciaPessoaFisica;
import br.cefetmg.implicare.model.serviceImpl.CompetenciaPessoaFisicaManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class InserirCompetenciaPessoaFisica {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try {
            
            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Cod_Competencia = Integer.parseInt(request.getParameter("Cod_Competencia"));
            int Cod_Proficiencia = Integer.parseInt(request.getParameter("Cod_Proficiencia"));
            
            CompetenciaPessoaFisicaManagementImpl CompetenciaPessoaImpl = new CompetenciaPessoaFisicaManagementImpl();            
            CompetenciaPessoaFisica Comp = new CompetenciaPessoaFisica();
            
            Comp.setCPF(CPF);
            Comp.setCod_Competencia(Cod_Competencia);
            Comp.setCod_Proficiencia(Cod_Proficiencia);
            
            CompetenciaPessoaImpl.insert(Comp);

            if (CompetenciaPessoaImpl == null) {
                String Erro = "Erro ao inserir Competencia Pessoa Fisica";
                jsp="/WEB-Pages/Erro.jsp";
                request.setAttribute("Erro", Erro);
            } else {
                jsp="";
            }
        } catch(Exception e) {
            e.printStackTrace();
            jsp="";
        }
        
        return jsp;
    }
    
}
