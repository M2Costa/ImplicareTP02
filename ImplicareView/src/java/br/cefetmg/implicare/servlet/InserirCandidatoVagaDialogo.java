/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.CandidatoVagaDialogo;
import br.cefetmg.implicare.model.serviceImpl.CandidatoVagaDialogoManagementImpl;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class InserirCandidatoVagaDialogo {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try {
            
            Long CPF = Long.parseLong(request.getParameter("CPF"));
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            Long CNPJ = Long.parseLong(request.getParameter("CNPJ"));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date Dat_Publicacao = (Date) format.parse(request.getParameter("Dat_Publicacao"));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Timestamp Dat_Dialogo = (Timestamp) formato.parse(request.getParameter("Dat_Dialogo"));
            String Txt_Dialogo = request.getParameter("Txt_Dialogo");
            String Idt_Empresa_Candidato = (String) request.getSession().getAttribute("CPF_CNPJ");
            
            CandidatoVagaDialogoManagementImpl CandidatoDialogoImpl = new CandidatoVagaDialogoManagementImpl();            
            CandidatoVagaDialogo CandVagaDialogo = new CandidatoVagaDialogo();
            
            CandVagaDialogo.setCPF(CPF);
            CandVagaDialogo.setCod_Cargo(Cod_Cargo);
            CandVagaDialogo.setCNPJ(CNPJ);
            CandVagaDialogo.setDat_Publicacao(Dat_Publicacao);
            CandVagaDialogo.setDat_Dialogo(Dat_Dialogo);
            CandVagaDialogo.setTxt_Dialogo(Txt_Dialogo);
            CandVagaDialogo.setIdt_Empresa_Candidato(Idt_Empresa_Candidato);
            
            CandidatoDialogoImpl.insert(CandVagaDialogo);

            if (CandidatoDialogoImpl.getCandidatoVagaDialogoCod(CPF, Cod_Cargo, CNPJ, Dat_Publicacao, Dat_Dialogo) == null) {
                String Erro = "Erro ao inserir Candidato Vaga Dialogo";
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
