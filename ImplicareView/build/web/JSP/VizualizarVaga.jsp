<%-- 
    Document   : VizualizarVaga
    Created on : 23/08/2018, 23:11:20
    Author     : Gabriel
--%>

<%@page contentType = "text/html" pageEncoding = "UTF-8"%>
<%@page import = "br.cefetmg.implicare.model.domain.Vaga"%>
<%@page import = "br.cefetmg.implicare.model.domain.Cargo"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
        <title> Vaga </title>
    </head>
    <body>
        <h1> Vaga </h1>
        <% 
            Vaga Vag = (Vaga) request.getAttribute("Vaga"); 
            Cargo Car = (Cargo) request.getAttribute("Cargo");
        %>
        <h4> Cargo: <% Car.getNom_Cargo(); %> </h4>
        <h4> Data da Publicação: <% Vag.getDat_Publicacao(); %> </h4>
        <h4> Número de Vagas: <% Vag.getNum_Vagas(); %> </h4>
        <h4> Carga Horária: <% Vag.getCarga_Horaria(); %> </h4>
        <h4> Remuneração: <% Vag.getRemuneracao(); %> </h4>
        <h4> Descrição da Vaga: <% Vag.getDesc_Vaga(); %> </h4>
        <h4> Status da Vaga: <% Vag.getStatus_Vaga(); %> </h4>
    </body>
</html>
