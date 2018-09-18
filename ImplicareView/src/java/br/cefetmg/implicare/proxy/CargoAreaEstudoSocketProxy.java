/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.proxy;

import br.cefetmg.implicare.model.domain.FormacaoAcademica;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CargoAreaEstudoManagement;
import br.cefetmg.inf.implicare.util.AbstractInOut;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morato
 */
public class CargoAreaEstudoSocketProxy implements CargoAreaEstudoManagement {
    
    private String serverAddress;
    private int serverPort;
    
    public CargoAreaEstudoSocketProxy(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;        
    }
       
    public CargoAreaEstudoSocketProxy() {
        this("localhost", 2223);
    }
    @Override
    public Set<br.cefetmg.implicare.model.domain.CargoAreaEstudo> CargoAreaEstudo(List<FormacaoAcademica> FormAcad) throws BusinessException, PersistenceException {
        Set<br.cefetmg.implicare.model.domain.CargoAreaEstudo> result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("CargoAreaEstudo(FormAcad)");
            writer.writeObject(FormAcad);
            
            result = null;
            
            try {
                result = (Set<br.cefetmg.implicare.model.domain.CargoAreaEstudo>)reader.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CargoAreaEstudoSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CargoAreaEstudoSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return result;
    }
    
}
