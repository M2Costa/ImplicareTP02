package br.cefetmg.implicare.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gabriel
 */
public class ImplicareServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
        request.setCharacterEncoding("UTF-8");

        String jsp;
        String action = request.getParameter("action");

        switch (action) {
            case "AlterarCandidatoVaga":
                jsp = AlterarCandidatoVaga.execute(request);
                break;
            case "AlterarEmpresa":
                jsp = AlterarEmpresa.execute(request);
                break;
            case "AlterarExperienciaProfissional":
                jsp = AlterarExperienciaProfissional.execute(request);
                break;
            case "AlterarFormacaoAcademica":
                jsp = AlterarFormacaoAcademica.execute(request);
                break;
            case "AlterarPessoaFisica":
                jsp = AlterarPessoaFisica.execute(request);
                break;
            case "AlterarTelefone":
                jsp = AlterarTelefone.execute(request);
                break;
            case "AlterarUsuario":
                jsp = AlterarUsuario.execute(request);
                break;
            case "AlterarVaga":
                jsp = AlterarVaga.execute(request);
                break;
            case "ExcluirCandidatoVaga":
                jsp = ExcluirCandidatoVaga.execute(request);
                break;
            case "ExcluirCargoInteresse":
                jsp = ExcluirCargoInteresse.execute(request);
                break;
            case "ExcluirCompetenciaPessoaFisica":
                jsp = ExcluirCompetenciaPessoaFisica.execute(request);
                break;
            case "ExcluirExperienciaProfissional":
                jsp = ExcluirExperienciaProfissional.execute(request);
                break;
            case "ExcluirFormacaoAcademica":
                jsp = ExcluirFormacaoAcademica.execute(request);
                break;
            case "ExcluirTelefone":
                jsp = ExcluirTelefone.execute(request);
                break;
            case "ExcluirVaga":
                jsp = ExcluirVaga.execute(request);
                break;
            case "InserirCandidatoVaga":
                jsp = InserirCandidatoVaga.execute(request);
                break;
            case "InserirCandidatoVagaDialogo":
                jsp = InserirCandidatoVagaDialogo.execute(request);
                break;
            case "InserirCargoInteresse":
                jsp = InserirCargoInteresse.execute(request);
                break;
            case "InserirCompetenciaPessoaFisica":
                jsp = InserirCompetenciaPessoaFisica.execute(request);
                break;
            case "InserirEmpresa":
                jsp = InserirEmpresa.execute(request);
                break;
            case "InserirExperienciaProfissional":
                jsp = InserirExperienciaProfissional.execute(request);
                break;
            case "InserirFormacaoAcademica":
                jsp = InserirFormacaoAcademica.execute(request);
                break;
            case "InserirPessoaFisica":
                jsp = InserirPessoaFisica.execute(request);
                break;
            case "InserirTelefone":
                jsp = InserirTelefone.execute(request);
                break;
            case "InserirUsuario":
                jsp = InserirUsuario.execute(request);
                break;
            case "InserirVaga":
                jsp = InserirVaga.execute(request);
                break;
            case "ListarAreaEstudo":
                jsp = ListarAreaEstudo.execute(request);
                break;
            case "ListarCandidatoVaga":
                jsp = ListarCandidatoVaga.execute(request);
                break;
            case "ListarCandidatoVagaDialogo":
                jsp = ListarCandidatoVagaDialogo.execute(request);
                break;
            case "ListarCargo":
                jsp = ListarCargo.execute(request);
                break;
            case "ListarCargoInteresse":
                jsp = ListarCargoInteresse.execute(request);
                break;
            case "ListarCargoPessoaFisica":
                jsp = ListarCargoPessoaFisica.execute(request);
                break;
            case "ListarCidade":
                jsp = ListarCidade.execute(request);
                break;
            case "ListarCompetencia":
                jsp = ListarCompetencia.execute(request);
                break;
            case "ListarEstado":
                jsp = ListarEstado.execute(request);
                break;
            case "ListarExperienciaProfissional":
                jsp = ListarExperienciaProfissional.execute(request);
                break;
            case "ListarFormacaoAcademica":
                jsp = ListarFormacaoAcademica.execute(request);
                break;
            case "ListarProficiencia":
                jsp = ListarProficiencia.execute(request);
                break;
            case "ListarTelefone":
                jsp = ListarTelefone.execute(request);
                break;
            case "ListarVaga":
                jsp = ListarVaga.execute(request);
                break;
            case "ListarVagaPessoaFisica":
                jsp = ListarVagaPessoaFisica.execute(request);
                break;
            case "Login":
                jsp = Login.execute(request);
                break;
            case "PesquisarAreaEstudo":
                jsp = PesquisarAreaEstudo.execute(request);
                break;
            case "PesquisarCargo":
                jsp = PesquisarCargo.execute(request);
                break;
            case "PesquisarCidade":
                jsp = PesquisarCidade.execute(request);
                break;
            case "PesquisarCompetencia":
                jsp = PesquisarCompetencia.execute(request);
                break;
            case "PesquisarEmpresa":
                jsp = PesquisarEmpresa.execute(request);
                break;
            case "PesquisarEstado":
                jsp = PesquisarEstado.execute(request);
                break;
            case "PesquisarPessoaFisica":
                jsp = PesquisarPessoaFisica.execute(request);
                break;
            case "PesquisarProficiencia":
                jsp = PesquisarProficiencia.execute(request);
                break;
            case "PesquisarUsuario":
                jsp = PesquisarUsuario.execute(request);
                break;
            default:
                jsp = "/WEB-Pages/Erro.jsp";
                request.setAttribute("Erro", "A solicitação feita ao servlet é inválida ou ainda não foi implementada.");
        }
        
        //Cria um dispatcher da solicita,c~ao.
        RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
        //Redireciona a p´agina.
        dispatcher.forward(request, response);
    }
}