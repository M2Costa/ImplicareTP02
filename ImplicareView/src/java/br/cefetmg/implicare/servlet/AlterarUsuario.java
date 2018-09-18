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
class AlterarUsuario {

    static String execute(HttpServletRequest request) {
        String jsp = "";
        try {

            Long CPF_CNPJ = (Long) request.getSession().getAttribute("CPF_CNPJ");
            String Email = request.getParameter("Email");
            String Senha = request.getParameter("Senha");
            String Foto = request.getParameter("Foto");
            Long Cod_Cep = Long.parseLong(request.getParameter("Cod_Cep"));
            String Endereco = request.getParameter("Endereco");
            String Desc_Usuario = request.getParameter("Desc_Usuario");

            UsuarioManagementImpl UsuarioImpl = new UsuarioManagementImpl();
            Usuario User = new Usuario();
            
            User.setCPF_CNPJ(CPF_CNPJ);
            User.setEmail(Email);
            User.setSenha(Senha);
            User.setFoto(Foto);
            User.setCod_CEP(Cod_Cep);
            User.setEndereco(Endereco);
            User.setDesc_Usuario(Desc_Usuario);
            
            boolean Usuario = UsuarioImpl.update(CPF_CNPJ, User);

            if (Usuario =! false) {
                jsp = "";
                request.setAttribute("Usuario", User);
            } else {
                String Erro = "Erro ao Editar Usuario";
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
