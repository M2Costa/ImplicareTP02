/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.Cargo;
import br.cefetmg.implicare.model.domain.CargoAreaEstudo;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CargoManagement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */

public class CargoSocketProxy implements CargoManagement {
    
    Cliente Cliente;
    
    public CargoSocketProxy() {
        try {
            this.Cliente = Cliente.getInstancia();
        } catch (SocketException ex) {
            Logger.getLogger(CargoSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CargoSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Cargo> listAll() throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        pacoteEnviado = new Pacote(TipoOperacao.LIST_Cargos, null);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<Cargo> AreaEst = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<Cargo>>() {
                }.getType());
        
        return AreaEst;
    }

    @Override
    public List<Cargo> getCargos(Set<CargoAreaEstudo> CargoArea) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(CargoArea));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_CargoFormacao, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        List<Cargo> Cargo = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<List<Cargo>>() {
                }.getType());
        
        return Cargo;
    }

    @Override
    public Cargo getCargoCod(int Cod_Cargo) throws PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();

        ArrayList<String> dados = new ArrayList<>();
        
        dados.add(gson.toJson(Cod_Cargo));
        pacoteEnviado = new Pacote(TipoOperacao.PESQ_Cargo, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        Cargo Car = gson.fromJson(pacoteRecebido.getDados().get(0), Cargo.class);
        return Car;
    }
    
}
