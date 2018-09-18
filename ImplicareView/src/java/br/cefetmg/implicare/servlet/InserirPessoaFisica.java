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
class InserirPessoaFisica {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try {
            
            Long CPF = Long.parseLong(request.getParameter("CPF_CNPJ"));
            String Nome = request.getParameter("Nome");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date Data_Nascimento = (Date) formato.parse(request.getParameter("Data_Nascimento"));
            
            PessoaFisicaManagementImpl PessoaImpl = new PessoaFisicaManagementImpl();            
            PessoaFisica Pessoa = new PessoaFisica();
            
            Pessoa.setCPF(CPF);
            Pessoa.setNome(Nome);
            Pessoa.setData_Nascimento(Data_Nascimento);
            
            PessoaImpl.insert(Pessoa);

            if (PessoaImpl.getPessoaFisicaCod(CPF) == null) {
                String Erro = "Erro ao inserir Pessoa Fisica";
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
