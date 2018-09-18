/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.serviceImpl.CargoInteresseManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class ExcluirCargoInteresse {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try{

            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            CargoInteresseManagementImpl CargoImpl = new CargoInteresseManagementImpl();        
            boolean Cargo = CargoImpl.delete(CPF, Cod_Cargo);

            if (Cargo =! false) {
                jsp="";
            } else {
                String Erro = "Ocorreu erro ao Excluir Cargo Interesse!";
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
