/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.CargoInteresse;
import br.cefetmg.implicare.model.serviceImpl.CargoInteresseManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class InserirCargoInteresse {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try {
            
            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            
            CargoInteresseManagementImpl CarInteresseImpl = new CargoInteresseManagementImpl();            
            CargoInteresse Car = new CargoInteresse();
            
            Car.setCPF(CPF);
            Car.setCod_Cargo(Cod_Cargo);
            
            CarInteresseImpl.insert(Car);

            if (CarInteresseImpl.getCargoInteresseCod(CPF, Cod_Cargo) == null) {
                String Erro = "Erro ao inserir Cargo Interesse";
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
