/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.CandidatoVaga;
import br.cefetmg.implicare.model.serviceImpl.CandidatoVagaManagementImpl;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarCandidatoVaga {

    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            CandidatoVagaManagementImpl CandidatoVagaImpl = new CandidatoVagaManagementImpl();
            List<CandidatoVaga> CandVaga = new ArrayList<>();
            
            Long CNPJ = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date Dat_Publicacao = (Date) formato.parse(request.getParameter("Dat_Publicacao"));
            
            CandVaga = CandidatoVagaImpl.getCandidatosVaga(Cod_Cargo, CNPJ, Dat_Publicacao);
 
            if (CandVaga != null) {
                request.setAttribute("ListaCandidatoVaga", CandVaga);
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
