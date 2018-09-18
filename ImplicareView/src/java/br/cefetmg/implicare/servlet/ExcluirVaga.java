/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.proxy.VagaSocketProxy;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class ExcluirVaga {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try{

            Long CNPJ = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date Dat_Publicacao = (Date) formato.parse(request.getParameter("Dat_Publicacao"));
            VagaSocketProxy VagaProxy = new VagaSocketProxy();        
            boolean Vaga = VagaProxy.delete(CNPJ, Cod_Cargo, Dat_Publicacao);

            if (Vaga =! false) {
                jsp="";
            } else {
                String Erro = "Ocorreu erro ao Excluir Vaga!";
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
