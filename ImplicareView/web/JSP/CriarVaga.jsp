<%-- 
    Document   : CriarVaga
    Created on : 23/08/2018, 22:07:15
    Author     : Gabriel
--%>

<%@page import = "br.cefetmg.implicare.model.domain.Cargo"%>
<%@page import = "java.util.List"%>
<%@page contentType = "text/html" pageEncoding = "UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
        <title> Criar Vaga </title>
    </head>
    <body>
        <h1> Criar Vaga de Emprego </h1>
        <form>
            <label for = "Cargo"> Cargo </label>
            <select name = "Cod_Cargo" id = "Cod_Cargo" id = "Cod_Cargo">
                <%
                     List<Cargo> listAll = (List<Cargo>) request.getAttribute("ListaCargo");
                     for (Cargo Car : listAll) {
                %>
                <option value = "<%Car.getCod_Cargo();%>"> <%Car.getNom_Cargo();%> </option>
                <% } %>
            </select>
            
            <label for = "Num_Vagas"> Número de Vagas </label>
            <input type = "number" name = "Num_Vagas" min = "1">
            
            <label for = "Carga_Horaria"> Carga Horária </label> 
            <input type = "number" name = "Carga_Horaria" min = "1">
            
            <label for = "Remuneracao"> Remuneração </label>
            <input type = "number" name = "Remuneracao" min = "0.01">
            
            <label for = "Desc_Vaga"> Descrição da Vaga </label>
            <input type = "text" name = "Desc_Vaga">
            
            <button type="submit" name="CadastrarBtn" value="Cadastrar"></button>
        </form>
    </body>
</html>
