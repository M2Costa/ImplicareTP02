/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.CargoInteresse;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CargoInteresseManagement;
import br.cefetmg.inf.implicare.util.AbstractInOut;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class CargoInteresseSocketProxy implements CargoInteresseManagement {
    
    Cliente Cliente;
    
    public CargoInteresseSocketProxy() {
        this.Cliente = Cliente.getInstancia();        
    }
    
    @Override
    public void insert(CargoInteresse CargoInteresse) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CargoInteresse));
        pacoteEnviado = new Pacote(TipoOperacao.INS_CargoInteresse, dados);

        Cliente.requisicao(pacoteEnviado);
    }

    @Override
    public boolean delete(long CPF, int Cod_Cargo) throws PersistenceException {
        Pacote pacoteEnviado;
        boolean pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Cod_Cargo));
        pacoteEnviado = new Pacote(TipoOperacao.DEL_CargoInteresse, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        return pacoteRecebido;
    }

    @Override
    public List<CargoInteresse> getCargosInteresse(long CPF) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CPF));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_CargoInteresse, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<CargoInteresse> CarInteresse = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<CargoInteresse>>() {
                }.getType());
        
        return CarInteresse;
    }

    @Override
    public CargoInteresse getCargoInteresseCod(long CPF, int Cod_Cargo) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(CPF));
        dados.add(gson.toJson(Cod_Cargo));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_CargoInteresse, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        CargoInteresse CarInteresse = gson.fromJson(pacoteRecebido.getDados().get(0), CargoInteresse.class);
        return CarInteresse;
    }
    
}
