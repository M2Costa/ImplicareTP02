/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.CargoInteresse;
import br.cefetmg.implicare.model.serviceImpl.CargoInteresseManagementImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class ListarCargoInteresse {
    
    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            CargoInteresseManagementImpl CarInteresseImpl = new CargoInteresseManagementImpl();
            List<CargoInteresse> CargoInt = new ArrayList<>();
            
            Long CPF = (Long) request.getSession().getAttribute("CPF_CNPJ");
            
            CargoInt = CarInteresseImpl.getCargosInteresse(CPF);
 
            if (CargoInt != null) {
                request.setAttribute("ListaCargoInteresse", CargoInt);
                jsp = "/listarcategoria.jsp";
            } else {
                String erro = "Nao existe registro!";
                request.setAttribute("erro", erro);
                jsp = "/erro.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
