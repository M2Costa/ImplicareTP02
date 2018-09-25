/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.FormacaoAcademica;
import br.cefetmg.implicare.model.domain.CargoAreaEstudo;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CargoAreaEstudoManagement;
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

public class CargoAreaEstudoSocketProxy implements CargoAreaEstudoManagement {
    
     Cliente Cliente;
       
    public CargoAreaEstudoSocketProxy() {
         try {
             this.Cliente = Cliente.getInstancia();
         } catch (SocketException ex) {
             Logger.getLogger(CargoAreaEstudoSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
         } catch (UnknownHostException ex) {
             Logger.getLogger(CargoAreaEstudoSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    @Override
    public Set<CargoAreaEstudo> CargoAreaEstudo(List<FormacaoAcademica> FormAcad) throws BusinessException, PersistenceException {
        Pacote pacoteEnviado;
        Pacote pacoteRecebido;

        Gson gson = new Gson();
        
        ArrayList<String> dados = new ArrayList<>();

        dados.add(gson.toJson(FormAcad));
        pacoteEnviado = new Pacote(TipoOperacao.LIST_CargoAreaEstudo, dados);

        pacoteRecebido = Cliente.requisicao(pacoteEnviado);
        
        Set<CargoAreaEstudo> CarAreaEst = gson.fromJson(pacoteRecebido.getDados().get(0),
                new TypeToken<Set<CargoAreaEstudo>>() {
                }.getType());
        
        return CarAreaEst;
    }
    
}
