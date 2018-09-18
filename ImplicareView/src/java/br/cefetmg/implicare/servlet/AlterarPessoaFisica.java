/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.PessoaFisica;
import br.cefetmg.implicare.model.serviceImpl.PessoaFisicaManagementImpl;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class AlterarPessoaFisica {

    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {

            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            String Nome = request.getParameter("Nome");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date Data_Nascimento = (Date) formato.parse(request.getParameter("Data_Nascimento"));

            PessoaFisicaManagementImpl PessoaFisicaImpl = new PessoaFisicaManagementImpl();
            PessoaFisica PessoaFisica = new PessoaFisica();
            
            PessoaFisica.setCPF(CPF);
            PessoaFisica.setNome(Nome);
            PessoaFisica.setData_Nascimento(Data_Nascimento);
            
            boolean Pessoa = PessoaFisicaImpl.update(CPF, PessoaFisica);

            if (Pessoa =! false) {
                jsp = "";
                request.setAttribute("PessoaFisica", PessoaFisica);
            } else {
                String Erro = "Erro ao Editar Pessoa Fisica";
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
