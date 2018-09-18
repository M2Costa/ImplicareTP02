/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Telefone;
import br.cefetmg.implicare.model.serviceImpl.TelefoneManagementImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarTelefone {
    
    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            TelefoneManagementImpl TelefoneImpl = new TelefoneManagementImpl();
            List<Telefone> Telefone = new ArrayList<>();
            
            Long CPF_CNPJ = (Long) request.getSession().getAttribute("CPF_CNPJ");
            
            Telefone = TelefoneImpl.getTelefones(CPF_CNPJ);
 
            if (Telefone != null) {
                request.setAttribute("ListaTelefone", Telefone);
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
