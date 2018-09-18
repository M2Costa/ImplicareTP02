/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Vaga;
import br.cefetmg.implicare.proxy.VagaSocketProxy;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class InserirVaga {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try {
            
            Long CNPJ = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date Dat_Publicacao = (Date) formato.parse(request.getParameter("Dat_Publicacao"));
            int Num_Vagas = Integer.parseInt(request.getParameter("Num_Vagas"));
            int Carga_Horaria = Integer.parseInt(request.getParameter("Carga_Horaria"));
            double Remuneracao = Double.parseDouble(request.getParameter("Remuneracao"));
            String Desc_Vaga = request.getParameter("Desc_Vaga");
            int Status_Vaga = Integer.parseInt(request.getParameter("Status_Vaga"));
            
            VagaSocketProxy VagaProxy = new VagaSocketProxy();            
            Vaga Vaga = new Vaga();
            
            Vaga.setCNPJ(CNPJ);
            Vaga.setCod_Cargo(Cod_Cargo);
            Vaga.setDat_Publicacao(Dat_Publicacao);
            Vaga.setNum_Vagas(Num_Vagas);
            Vaga.setCarga_Horaria(Carga_Horaria);
            Vaga.setRemuneracao(Remuneracao);
            Vaga.setDesc_Vaga(Desc_Vaga);
            Vaga.setStatus_Vaga(Status_Vaga);
            
            VagaProxy.insert(Vaga);

            if (VagaProxy.getVagaCod(CNPJ, Cod_Cargo, Dat_Publicacao) == null) {
                String Erro = "Erro ao inserir Vaga";
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
