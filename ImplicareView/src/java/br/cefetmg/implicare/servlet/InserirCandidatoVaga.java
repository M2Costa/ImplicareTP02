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
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class InserirCandidatoVaga {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try {
            
            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            Long CNPJ = Long.parseLong(request.getParameter("CNPJ"));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date Dat_Publicacao = (Date) formato.parse(request.getParameter("Dat_Publicacao"));
            String Status_Candidato = request.getParameter("Status_Candidato");
            
            CandidatoVagaManagementImpl CandidatoImpl = new CandidatoVagaManagementImpl();            
            CandidatoVaga CandVaga = new CandidatoVaga();
            
            CandVaga.setCPF(CPF);
            CandVaga.setCod_Cargo(Cod_Cargo);
            CandVaga.setCNPJ(CNPJ);
            CandVaga.setDat_Publicacao(Dat_Publicacao);
            CandVaga.setStatus_Candidato(Status_Candidato);

            CandidatoImpl.insert(CandVaga);

            if (CandidatoImpl.getCandidatoVagaCod(CPF, Cod_Cargo, CNPJ, Dat_Publicacao) == null) {
                String Erro = "Erro ao inserir Candidato Vaga";
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
