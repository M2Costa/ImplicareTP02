/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Empresa;
import br.cefetmg.implicare.model.serviceImpl.EmpresaManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class AlterarEmpresa {

    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {

            Long CNPJ = (Long) request.getSession().getAttribute("CPF_CNPJ");
            String Nom_Razao_Social = request.getParameter("Nom_Razao_Social");
            String Nom_Fantasia = request.getParameter("Nom_Fantasia");

            EmpresaManagementImpl EmpresaImpl = new EmpresaManagementImpl();
            Empresa Empr = new Empresa();
            
            Empr.setCNPJ(CNPJ);
            Empr.setNom_Razao_Social(Nom_Razao_Social);
            Empr.setNome_Fantasia(Nom_Fantasia);
            
            boolean Empresa = EmpresaImpl.update(CNPJ, Empr);

            if (Empresa =! false) {
                jsp = "";
                request.setAttribute("Empresa", Empr);
            } else {
                String Erro = "Erro ao Editar Empresa";
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
