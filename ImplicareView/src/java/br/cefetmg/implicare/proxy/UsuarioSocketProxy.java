/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Usuario;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.UsuarioManagement;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */

public class UsuarioSocketProxy implements UsuarioManagement{
    
    Cliente Cliente;
    
    public UsuarioSocketProxy() {
        this.Cliente = Cliente.getInstancia();
    }
    
    @Override
    public void insert(Usuario Usuario) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(Usuario));
        pacoteEnviado = new Pacote(TipoOperacao.INS_Usuario, dados);

        Cliente.requisicao(pacoteEnviado);
    }

    @Override
    public boolean update(Long CPF_CNPJ, Usuario Usuario) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF_CNPJ));
        dados.add(gson.toJson(Usuario));
        pacoteEnviado = new Pacote(TipoOperacao.UPD_Usuario, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public Usuario getUsuarioCod(Long CPF_CNPJ) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CPF_CNPJ));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_UsuarioCod, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Usuario Usu = gson.fromJson(pacoteRecebido.getDados().get(0), Usuario.class);
        return Usu;
    }

    @Override
    public Usuario getLogin(Long CPF_CNPJ, String Senha) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CPF_CNPJ));
        dados.add(gson.toJson(Senha));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_Login, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Usuario Usu = gson.fromJson(pacoteRecebido.getDados().get(0), Usuario.class);
        return Usu;
    }
    
}
