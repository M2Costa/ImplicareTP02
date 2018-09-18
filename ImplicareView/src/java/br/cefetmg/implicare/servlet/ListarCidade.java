/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Cidade;
import br.cefetmg.implicare.model.serviceImpl.CidadeManagementImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class ListarCidade {

    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            CidadeManagementImpl CidadeImpl = new CidadeManagementImpl();
            List<Cidade> Cidade = new ArrayList<>();
            int Cod_Estado = Integer.parseInt(request.getParameter("Cod_Estado"));
            Cidade = CidadeImpl.getCidades(Cod_Estado);
            
            if (Cidade != null) {
                request.setAttribute("ListaCidade", Cidade);
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
