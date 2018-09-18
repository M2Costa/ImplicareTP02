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
class AlterarCandidatoVaga {

    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {

            Long CPF = Long.parseLong(request.getParameter("CPF"));
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            Long CNPJ = (Long) request.getSession().getAttribute("CPF_CNPJ");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date Dat_Publicacao = (Date) formato.parse(request.getParameter("Dat_Publicacao"));
            String Status_Vaga = request.getParameter("Status_Vaga");

            CandidatoVagaManagementImpl CandidatoImpl = new CandidatoVagaManagementImpl();
            CandidatoVaga Cand = new CandidatoVaga();
            
            Cand.setCPF(CPF);
            Cand.setCod_Cargo(Cod_Cargo);
            Cand.setCNPJ(CNPJ);
            Cand.setDat_Publicacao(Dat_Publicacao);
            Cand.setStatus_Candidato(Status_Vaga);
            
            boolean Candidato = CandidatoImpl.update(CPF, Cod_Cargo, CNPJ, Dat_Publicacao, Cand);

            if (Candidato =! false) {
                jsp = "";
                request.setAttribute("CandidatoVaga", Cand);
            } else {
                String Erro = "Erro ao Editar Candidato Vaga";
                jsp = "/WEB-Pages/Erro.jsp";
                request.setAttribute("Erro", Erro);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
