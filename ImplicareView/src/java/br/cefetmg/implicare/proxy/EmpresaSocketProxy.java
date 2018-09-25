/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Empresa;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.EmpresaManagement;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */

public class EmpresaSocketProxy implements EmpresaManagement {
    
    Cliente Cliente;
    
    public EmpresaSocketProxy() {
        this.Cliente = Cliente.getInstancia();
    }
    
    @Override
    public void insert(Empresa Empresa) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(Empresa));
        pacoteEnviado = new Pacote(TipoOperacao.INS_Empresa, dados);

        Cliente.requisicao(pacoteEnviado);
    }

    @Override
    public boolean update(Long CNPJ, Empresa Empresa) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CNPJ));
        dados.add(gson.toJson(Empresa));
        pacoteEnviado = new Pacote(TipoOperacao.UPD_Empresa, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public Empresa getEmpresaCod(Long CNPJ) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CNPJ));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_Empresa, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Empresa Empr = gson.fromJson(pacoteRecebido.getDados().get(0), Empresa.class);
        return Empr;
    }
    
}
