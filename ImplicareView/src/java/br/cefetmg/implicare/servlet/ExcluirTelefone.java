/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.serviceImpl.TelefoneManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class ExcluirTelefone {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try{

            Long CPF_CNPJ = (Long) request.getSession().getAttribute("CPF_CNPJ");
            String Num_Telefone = request.getParameter("Num_Telefone");
            TelefoneManagementImpl TelefoneImpl = new TelefoneManagementImpl();        
            boolean Telefone = TelefoneImpl.delete(CPF_CNPJ, Num_Telefone);

            if (Telefone =! false) {
                jsp="";
            } else {
                String Erro = "Ocorreu erro ao Excluir Telefone!";
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
