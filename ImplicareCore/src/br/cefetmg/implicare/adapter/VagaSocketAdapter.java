/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.implicare.adapter;

import br.cefetmg.implicare.model.domain.Vaga;
import br.cefetmg.implicare.model.exception.BusinessException;
import br.cefetmg.implicare.model.exception.PersistenceException;
import br.cefetmg.implicare.model.service.VagaManagement;
import br.cefetmg.implicare.model.serviceImpl.VagaManagementImpl;
import br.cefetmg.inf.implicare.util.AbstractInOut;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class VagaSocketAdapter implements  Runnable {
    
    private final Socket Socket;
    private final VagaManagement VagaMan;
    
    public VagaSocketAdapter(Socket Socket) {
        this.Socket = Socket;
        this.VagaMan = new VagaManagementImpl();
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
                case "insert(Vaga)":
                    Vaga Vaga = (Vaga) reader.readObject();            
                    VagaMan.insert(Vaga);
                    break;                
                    
                case "update(CNPJ, Cod_Vaga, Dat_Publicacao)":
                    Long CNPJ = reader.readLong();
                    int Cod_Cargo = reader.readInt(); 
                    Date Dat_Publicacao = (Date) reader.readObject();
                    Vaga = (Vaga) reader.readObject();
                    boolean result = VagaMan.update(CNPJ, Cod_Cargo, Dat_Publicacao, Vaga);
                    writer.writeBoolean(result);
                    break;
                    
                case "delete(CNPJ, Cod_Cargo, Dat_Publicacao)": 
                    CNPJ = reader.readLong();
                    Cod_Cargo = reader.readInt();
                    Dat_Publicacao = (Date) reader.readObject();
                    result = VagaMan.delete(CNPJ, Cod_Cargo, Dat_Publicacao);
                    writer.writeBoolean(result);
                    break;
                    
                case "getVagaCNPJ(long CNPJ":

                    break;   
                    
                case "getVagaCod_Cargo(List<CargoInteresse> CarInteresse)": 

                    break;
                    
                case "getVagaCod(long CNPJ, int Cod_Cargo, Date Dat_Publicacao)": 

                    break;
            }
            
            writer.flush();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(VagaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        } catch (BusinessException ex) {
            Logger.getLogger(VagaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistenceException ex) {
            Logger.getLogger(VagaSocketAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
