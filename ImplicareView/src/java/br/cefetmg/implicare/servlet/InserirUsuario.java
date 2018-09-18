/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.servlet;

import br.cefetmg.implicare.model.domain.Usuario;
import br.cefetmg.implicare.model.serviceImpl.UsuarioManagementImpl;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Gabriel
 */
class InserirUsuario {

    static String execute(HttpServletRequest request) {
        String jsp="";
        try {
            
            Long CPF_CNPJ = Long.parseLong(request.getParameter("CPF_CNPJ"));
            String Email = request.getParameter("Email");
            String Senha = request.getParameter("Senha");
            String Foto = request.getParameter("Foto");
            Long Cod_Cep = Long.parseLong(request.getParameter("Cod_Cep"));
            String Endereco = request.getParameter("Endereco");
            String Desc_Usuario = request.getParameter("Desc_Usuario");
            
            UsuarioManagementImpl UsuarioImpl = new UsuarioManagementImpl();            
            Usuario Usuario = new Usuario();
            
            Usuario.setCPF_CNPJ(CPF_CNPJ);
            Usuario.setEmail(Email);
            Usuario.setSenha(Senha);
            Usuario.setFoto(Foto);
            Usuario.setCod_CEP(Cod_Cep);
            Usuario.setEndereco(Endereco);
            Usuario.setDesc_Usuario(Desc_Usuario);
            
            UsuarioImpl.insert(Usuario);

            if (UsuarioImpl.getUsuarioCod(CPF_CNPJ) == null) {
                String Erro = "Erro ao inserir Usuario";
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
