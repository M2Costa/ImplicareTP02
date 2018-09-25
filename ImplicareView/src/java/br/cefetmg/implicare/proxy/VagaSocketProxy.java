/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.CargoInteresse;
import br.cefetmg.implicare.model.domain.Vaga;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.VagaManagement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */

public class VagaSocketProxy implements  VagaManagement {
    
    Cliente Cliente;
    
    public VagaSocketProxy() {
        this.Cliente = Cliente.getInstancia();
    }
    
    @Override
    public void insert(Vaga Vaga) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(Vaga));
        pacoteEnviado = new Pacote(TipoOperacao.INS_Vaga, dados);

        Cliente.requisicao(pacoteEnviado);
    }

    @Override
    public boolean update(long CNPJ, int Cod_Cargo, Date Dat_Publicacao, Vaga Vaga) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CNPJ));
        dados.add(gson.toJson(Cod_Cargo));
        dados.add(gson.toJson(Dat_Publicacao));
        dados.add(gson.toJson(Vaga));
        pacoteEnviado = new Pacote(TipoOperacao.UPD_Vaga, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public boolean delete(long CNPJ, int Cod_Cargo, Date Dat_Publicacao) throws PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CNPJ));
        dados.add(gson.toJson(Cod_Cargo));
        dados.add(gson.toJson(Dat_Publicacao));
        pacoteEnviado = new Pacote(TipoOperacao.DEL_Vaga, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public List<Vaga> getVagaCNPJ(long CNPJ) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CNPJ));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_VagasEmpresa, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<Vaga> Vag = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<Vaga>>() {
                }.getType());
        
        return Vag;   
    }

    @Override
    public List<Vaga> getVagaCod_Cargo(List<CargoInteresse> CarInteresse) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CarInteresse));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_VagasCandidato, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<Vaga> Vag = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<Vaga>>() {
                }.getType());
        
        return Vag;  
    }

    @Override
    public Vaga getVagaCod(long CNPJ, int Cod_Cargo, Date Dat_Publicacao) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CNPJ));
        dados.add(gson.toJson(Cod_Cargo));
        dados.add(gson.toJson(Dat_Publicacao));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_Vaga, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Vaga Vag = gson.fromJson(pacoteRecebido.getDados().get(0), Vaga.class);
        return Vag;
    }
    
}
