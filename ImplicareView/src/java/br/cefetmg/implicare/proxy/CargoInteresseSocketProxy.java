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
 * @author Morato
 */
public class CargoInteresseSocketProxy implements CargoInteresseManagement {
    
    private String serverAddress;
    private int serverPort;
    
    public CargoInteresseSocketProxy(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;        
    }
       
    public CargoInteresseSocketProxy() {
        this("localhost", 2223);
    }
    
    @Override
    public void insert(CargoInteresse CargoInteresse) throws BusinessException, PersistenceException {
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("insert(CargoInteresse)");
            writer.writeObject(CargoInteresse);           
            writer.flush();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CargoInteresseSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public boolean delete(long CPF, int Cod_Cargo) throws PersistenceException {
        Boolean result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("delete(CPF, Cod_Cargo)");
            writer.writeLong(CPF); 
            writer.writeInt(Cod_Cargo);
            writer.flush();
            
            result = reader.readBoolean();
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CargoInteresseSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        return result;
    }

    @Override
    public List<CargoInteresse> getCargosInteresse(long CPF) throws PersistenceException {
        List<CargoInteresse> result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("getCargosInteresse(CPF)");
            writer.writeLong(CPF);          
            writer.flush();
            
            result = null;
            
            try {
                result = (List<CargoInteresse>)reader.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CargoInteresseSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CargoInteresseSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return result;
    }

    @Override
    public CargoInteresse getCargoInteresseCod(long CPF, int Cod_Cargo) throws PersistenceException {
        CargoInteresse result;
        Socket socket;
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            socket = new Socket(this.serverAddress, this.serverPort);            
            writer = AbstractInOut.getObjectWriter(socket);
            reader = AbstractInOut.getObjectReader(socket);                 
            
            writer.writeUTF("getCargoInteresseCod(CPF, Cod_Cargo)");
            writer.writeLong(CPF);
            writer.writeInt(Cod_Cargo);          
            writer.flush();
            
            result = null;
            
            try {
                result = (CargoInteresse) reader.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CargoInteresseSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(CargoInteresseSocketProxy.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
        
        return result;
    }
    
}
