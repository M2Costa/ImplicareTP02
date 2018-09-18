/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.adapter;

import br.cefetmg.implicare.model.domain.CargoInteresse;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CargoInteresseManagement;
import br.cefetmg.implicare.model.serviceImpl.CargoInteresseManagementImpl;
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
public class CargoInteresseSocketAdapter implements Runnable {
    
    private final Socket Socket;
    private final CargoInteresseManagement CargoInteresseMan;
    
    public CargoInteresseSocketAdapter(Socket Socket) {
        this.Socket = Socket;
        this.CargoInteresseMan = new CargoInteresseManagementImpl();
    }
    
    private Socket getSocket(){
        return Socket;
    }

    @Override
    public void run() {
        ObjectOutputStream writer;
        ObjectInputStream reader;
        
        try {
            writer = AbstractInOut.getObjectWriter(this.getSocket());
            reader = AbstractInOut.getObjectReader(this.getSocket());            
            
            String command;
            
            command = reader.readUTF();
        switch(command) {
                case "insert(CargoInteresse)":
                    CargoInteresse CargoInteresse = (CargoInteresse) reader.readObject();            
                    CargoInteresseMan.insert(CargoInteresse);
                    break;                
                    
                case "delete(CPF, Cod_Cargo)":
                    Long CPF = reader.readLong();
                    int Cod_Cargo = reader.readInt();
                    boolean result = CargoInteresseMan.delete(CPF, Cod_Cargo);
                    writer.writeBoolean(result);
                    break;
                 
                case "getCargosInteresse(CPF)":
                    CPF = reader.readLong();
                    List<CargoInteresse> resultado = CargoInteresseMan.getCargosInteresse(CPF);
                    writer.writeObject(resultado);
                    break;
                    
                case "getCargoInteresseCod(CPF, Cod_Cargo)":
                    CPF = reader.readLong();
                    Cod_Cargo = reader.readInt();
                    CargoInteresse resultado1 = CargoInteresseMan.getCargoInteresseCod(CPF, Cod_Cargo);
                    writer.writeObject(resultado1);
                    break;   
            }
            
            writer.flush();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CargoInteresseSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } catch (BusinessException ex) {
            Logger.getLogger(CargoInteresseSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenceException ex) {
            Logger.getLogger(CargoInteresseSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
