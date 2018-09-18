/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.adapter;

import br.cefetmg.implicare.model.domain.FormacaoAcademica;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.CargoAreaEstudoManagement;
import br.cefetmg.implicare.model.serviceImpl.CargoAreaEstudoMangementImpl;
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
public class CargoAreaEstudoSocketAdapter implements Runnable{

    private final Socket Socket;
    private final CargoAreaEstudoManagement CargoAreaEstudoMan;
    
    public CargoAreaEstudoSocketAdapter(Socket Socket) {
        this.Socket = Socket;
        this.CargoAreaEstudoMan = new CargoAreaEstudoMangementImpl();
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
                case "CargoAreaEstudo(FormAcad)":
                    List<FormacaoAcademica> FormAcad = (List<FormacaoAcademica>) reader.readObject();            
                    CargoAreaEstudoMan.CargoAreaEstudo(FormAcad);
                    break;
        }
            writer.flush();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CandidatoVagaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } catch (BusinessException ex) {
            Logger.getLogger(CandidatoVagaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenceException ex) {
            Logger.getLogger(CandidatoVagaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
