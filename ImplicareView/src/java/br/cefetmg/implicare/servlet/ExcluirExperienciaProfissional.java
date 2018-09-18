/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.serviceImpl.ExperienciaProfissionalManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class ExcluirExperienciaProfissional {

    static String execute(HttpServletRequest request) {
       String jsp="";
        try{

            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Seq_Experiencia = Integer.parseInt(request.getParameter("Seq_Experiencia"));
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            
            ExperienciaProfissionalManagementImpl ExperienciaImpl = new ExperienciaProfissionalManagementImpl();     
            
            boolean Experiencia = ExperienciaImpl.delete(CPF, Seq_Experiencia, Cod_Cargo);

            if (Experiencia =! false) {
                jsp="";
            } else {
                String Erro = "Ocorreu erro ao Excluir ExperienciaProfissional!";
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
