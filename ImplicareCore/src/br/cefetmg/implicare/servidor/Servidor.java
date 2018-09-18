package br.cefetmg.implicare.servidor;

import br.cefetmg.implicare.adapter.AreaEstudoSocketAdapter;
import br.cefetmg.implicare.adapter.CandidatoVagaDialogoSocketAdapter;
import br.cefetmg.implicare.adapter.CandidatoVagaSocketAdapter;
import br.cefetmg.implicare.adapter.CargoAreaEstudoSocketAdapter;
import br.cefetmg.implicare.adapter.CargoInteresseSocketAdapter;
import br.cefetmg.implicare.adapter.CargoSocketAdapter;
import br.cefetmg.implicare.adapter.CepSocketAdapter;
import br.cefetmg.implicare.adapter.CidadeSocketAdapter;
import br.cefetmg.implicare.adapter.CompetenciaPessoaFisicaSocketAdapter;
import br.cefetmg.implicare.adapter.CompetenciaSocketAdapter;
import br.cefetmg.implicare.adapter.EmpresaSocketAdapter;
import br.cefetmg.implicare.adapter.EstadoSocketAdapter;
import br.cefetmg.implicare.adapter.ExperienciaProfissionalSocketAdapter;
import br.cefetmg.implicare.adapter.FormacaoAcademicaSocketAdapter;
import br.cefetmg.implicare.adapter.PessoaFisicaSocketAdapter;
import br.cefetmg.implicare.adapter.ProficienciaSocketAdapter;
import br.cefetmg.implicare.adapter.TelefoneSocketAdapter;
import br.cefetmg.implicare.adapter.UsuarioSocketAdapter;
import br.cefetmg.implicare.adapter.VagaSocketAdapter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Gabriel
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        ServerSocket server = null;
        
        try {
            server = new ServerSocket(2223);

            while(true) {
                Socket socket = server.accept();
                
                AreaEstudoSocketAdapter AreaEstudo = new AreaEstudoSocketAdapter(socket);
                Thread Thread1 = new Thread(AreaEstudo);
                Thread1.start();
                
                CandidatoVagaDialogoSocketAdapter CandidatoVagaDialogo = new CandidatoVagaDialogoSocketAdapter(socket);
                Thread Thread2 = new Thread(CandidatoVagaDialogo);
                Thread2.start();
                
                CandidatoVagaSocketAdapter CandidatoVaga = new CandidatoVagaSocketAdapter(socket);
                Thread Thread3 = new Thread(CandidatoVaga);
                Thread3.start();
                
                CargoAreaEstudoSocketAdapter CargoAreaEstudo = new CargoAreaEstudoSocketAdapter(socket);
                Thread Thread4 = new Thread(CargoAreaEstudo);
                Thread4.start();
                
                CargoInteresseSocketAdapter CargoInteresse = new CargoInteresseSocketAdapter(socket);
                Thread Thread5 = new Thread(CargoInteresse);
                Thread5.start();
                
                CargoSocketAdapter Cargo = new CargoSocketAdapter(socket);
                Thread Thread6 = new Thread(Cargo);
                Thread6.start();
                
                CepSocketAdapter Cep = new CepSocketAdapter(socket);
                Thread Thread7 = new Thread(Cep);
                Thread7.start();
                
                CidadeSocketAdapter Cidade = new CidadeSocketAdapter(socket);
                Thread Thread8 = new Thread(Cidade);
                Thread8.start();
                
                CompetenciaSocketAdapter Competencia = new CompetenciaSocketAdapter(socket);
                Thread Thread9 = new Thread(Competencia);
                Thread9.start();
                
                CompetenciaPessoaFisicaSocketAdapter CompetenciaPessoaFisica = new CompetenciaPessoaFisicaSocketAdapter(socket);
                Thread Thread10 = new Thread(CompetenciaPessoaFisica);
                Thread10.start();
                
                EmpresaSocketAdapter Empresa = new EmpresaSocketAdapter(socket);
                Thread Thread11 = new Thread(Empresa);
                Thread11.start();
                
                EstadoSocketAdapter Estado = new EstadoSocketAdapter(socket);
                Thread Thread12 = new Thread(Estado);
                Thread12.start();
                
                ExperienciaProfissionalSocketAdapter ExperienciaProfissional = new ExperienciaProfissionalSocketAdapter(socket);
                Thread Thread13 = new Thread(ExperienciaProfissional);
                Thread13.start();
                
                FormacaoAcademicaSocketAdapter FormacaoAcademica = new FormacaoAcademicaSocketAdapter(socket);
                Thread Thread14 = new Thread(FormacaoAcademica);
                Thread14.start();
                
                PessoaFisicaSocketAdapter PessoaFisica = new PessoaFisicaSocketAdapter(socket);
                Thread Thread15 = new Thread(PessoaFisica);
                Thread15.start();
                
                ProficienciaSocketAdapter Proficiencia = new ProficienciaSocketAdapter(socket);
                Thread Thread16 = new Thread(Proficiencia);
                Thread16.start();
                
                TelefoneSocketAdapter Telefone = new TelefoneSocketAdapter(socket);
                Thread Thread17 = new Thread(Telefone);
                Thread17.start();
                
                UsuarioSocketAdapter Usuario = new UsuarioSocketAdapter(socket);
                Thread Thread18 = new Thread(Usuario);
                Thread18.start();
                
                VagaSocketAdapter Vaga = new VagaSocketAdapter(socket);
                Thread Thread19 = new Thread(Vaga);
                Thread19.start();
            }
        }
        catch(Exception e) {
            if (server != null)
                server.close();
        }
        
    }
    
}
