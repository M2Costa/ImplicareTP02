/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Vaga;
import br.cefetmg.implicare.model.serviceImpl.VagaManagementImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarVaga {
    
    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            VagaManagementImpl VagaImpl = new VagaManagementImpl();
            List<Vaga> Vaga = new ArrayList<>();
            
            Long CNPJ = (Long) request.getSession().getAttribute("CPF_CNPJ");
            
            Vaga = VagaImpl.getVagaCNPJ(CNPJ);
 
            if (Vaga != null) {
                request.setAttribute("ListaVagaEmpresa", Vaga);
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
