/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.FormacaoAcademica;
import br.cefetmg.implicare.model.serviceImpl.FormacaoAcademicaManagementImpl;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class InserirFormacaoAcademica {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try {
            
            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Seq_Formacao = Integer.parseInt(request.getParameter("Seq_Formacao"));
            String Instituicao_Ensino = request.getParameter("Instituicao_Ensino");
            int Cod_Area_Estudo = Integer.parseInt(request.getParameter("Cod_Area_Estudo"));
            String Atividades_Desenvolvidas = request.getParameter("Atividades_Desenvolvidas");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date Data_Inicio = (Date) format.parse(request.getParameter("Data_Inicio"));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date Data_Termino = (Date) formato.parse(request.getParameter("Data_Termino"));
            String Desc_Formacao_Academica = request.getParameter("Desc_Formacao_Academica");
            
            FormacaoAcademicaManagementImpl FormacaoImpl = new FormacaoAcademicaManagementImpl();            
            FormacaoAcademica Formacao = new FormacaoAcademica();
            
            Formacao.setCPF(CPF);
            Formacao.setSeq_Formacao(Seq_Formacao);
            Formacao.setInstituicao_Ensino(Instituicao_Ensino);
            Formacao.setCod_Area_Estudo(Cod_Area_Estudo);
            Formacao.setAtividades_Desenvolvidas(Atividades_Desenvolvidas);
            Formacao.setData_Inicio(Data_Inicio);
            Formacao.setData_Termino(Data_Termino);
            Formacao.setDesc_Formacao_Academica(Desc_Formacao_Academica);
            
            FormacaoImpl.insert(Formacao);

            if (FormacaoImpl.getFormacaoAcademicaCod(CPF, Seq_Formacao, Cod_Area_Estudo) == null) {
                String Erro = "Erro ao inserir Formacao Academica";
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
