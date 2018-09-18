/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Cargo;
import br.cefetmg.implicare.model.serviceImpl.CargoManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
public class PesquisarCargo {
    
    static String execute(HttpServletRequest request) {
        String jsp="";
        try{
            int Cod_Cargo = Integer.parseInt(request.getParameter("Cod_Cargo"));
            
            CargoManagementImpl CargoImpl = new CargoManagementImpl();
            Cargo Car = new Cargo();
            
            Car = CargoImpl.getCargoCod(Cod_Cargo);

            if (Car == null) {
                jsp="/WEB-Pages/Erro.jsp";
                request.setAttribute("Erro","Cargo Nao Encontrado");
            } else {
                jsp="/Web-Pages/VisualizarEstoque.jsp";
                request.setAttribute("Cargo", Car);
            }
        } catch(Exception e) {
            e.printStackTrace();
            jsp="";
        }
        return jsp;
    }
    
}
