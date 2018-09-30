/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Morato
 * Created: 23/09/2018
 */

INSERT INTO Cargo (Cod_Cargo, Nom_Cargo) VALUES 
(0, "Oficial de liberdade condiciona")
(1, "Atuário") 
(2, "Engenheiro biomédico")
(3, "Engenheiro de software")
(4, "Otorrinolaringologista")
(5, "Consultor financeiro")
(6, "Higienista dental")
(7, "Terapeuta ocupacional")
(8, "Optometrista")
(9, "Fisioterapeuta")
(10, "Analistas de sistemas da computação")
(11, "Quiroprático")
(12, "Fonoaudiólogo")
(13, "Fisiologista")
(14, "Professor universitário")
(15, "Médico veterinário")
(16, "Nutricionista")
(17, "Farmacêutico")
(18, "Matemático")
(19, "Sociólogo")
(20, "Estatístico")
(21, "Físico")
(22, "Oculista")
(23, "Podólogo")
(24, "Desenvolvedor de web")
(25, "Historiador")
(26, "Engenheiro ambienta")
(27, "Engenheiro de petróleo")
(28, "Meteorologista")
(29, "Geólogo")
(30, "Gerente de recursos humanos")
(31, "Engenheiro civil")
(32, "Ortodontista")
(33, "Terapeuta respiratório")
(34, "Técnico de registros médicos")
(35, "Astrônomo")
(36, "Psiquiatra")
(37, "Programador de computador")
(38, "Gerente de mídias sociais")
(39, "Analista de pesquisa de mercado")
(40, "Assistente Jurídico")
(41, "Dentista")
(42, "Dermatologista")
(43, "Reparador de máquina industria")
(44, "Médico (Clínico geral)")
(45, "Logístico")
(46, "Contador")
(47, "Consultor de gestão")
(48, "Assistente socia")
(49, "Médico assistente");


INSERT INTO Usuario (CPF_CNPJ, Email, Senha, Foto, Cod_CEP, Endereco, Desc_Usuario) VALUES
(23451320354203, "uhhdaf@jdafn.com", "21ad84adv3a", NULL, 3452345234, "Lorem ipsum dolor sit amet", NULL)
(35486431843513, "adf435@adfad.com", "5ad4f1d3f4a", NULL, 4354435543, "Lorem ipsum dolor sit amet", NULL); 

INSERT INTO AreaEstudo (Cod_Area_Estudo, Nom_Area_Estudo) VALUES
(0, "Administração")
(1, "Direito")
(2, "Pedagogia")
(3, "Medicina")
(4, "Educação Física")
(5, "Ciências Biológicas")
(7, "Engenharia Civil")
(8, "Enfermagem")
(9, "Psicologia")
(10, "Ciências Contábeis");

INSERT INTO Competencia (Cod_Competencia, Nom_Competencia) VALUES
(0, "Lorem ipsum dolor sit amet")
(1, "Lorem ipsum dolor sit amet")
(2, "Lorem ipsum dolor sit amet")
(3, "Lorem ipsum dolor sit amet")
(4, "Lorem ipsum dolor sit amet")
(5, "Lorem ipsum dolor sit amet")
(6, "Lorem ipsum dolor sit amet");

INSERT INTO Estado (Cod_Estado, Nom_Estado) VALUES
(0, "Acre")
(1, "Amapá")
(2, "Amazonas")
(3, "Pará")
(4, "Rondônia")
(5, "Roraima")
(6, "Tocantins")
(7, "Alagoas")
(8, "Bahia")
(9, "Ceará")
(10, "Maranhão")
(11, "Paraíba")
(12, "Pernambuco")
(13, "Piauí")
(14, "Rio Grande do Norte")
(15, "Sergipe")
(16, "Goiás")
(17, "Mato Grosso")
(18, "Mato Grosso do Sul")
(19, "Distrito Federal")
(20, "Espírito Santo")
(21, "Minas Gerais")
(22, "São Paulo")
(23, "Rio de Janeiro")
(24, "Paraná")
(25, "Rio Grande do Sul")
(26, "Santa Catarina");

INSERT INTO Cidade (Cod_Estado, Cod_Cidade, Nom_Cidade) VALUES
(21, 0, "Belo Horizonte")
(21, 1, "Uberlândia")
(21, 2, "Contagem")
(21, 3, "Juiz de Fora");

INSERT INTO CEP (Cod_CEP, Cod_Cidade, Cod_Estado) VALUES 
(3452345234, 0, 21)
(4354435543, 0, 21);

INSERT INTO Empresa (CNPJ, Nom_Razao_Social, Nome_Fantasia) VALUES
(23451320354203, "Empresa Teste SA", "Empresa");

INSERT INTO Pessoa_Fisica (CPF, Nome, Data_Nascimento) VALUES 
(35486431843513, "João da Silva", "01-01-1996");

INSERT INTO Proficiencia(Cod_Proficiencia, Nivel_Proficiencia) VALUES
(0, "Iniciante")
(1, "Intermediario")
(2, "Pleno")
(3, "Senior");

INSERT INTO Formacao_Academica(CPF, seq_formacao, Instituicao_Ensino, Cod_Area_Estudo, 
Atividades_Desenvolvidas, Data_Inicio, Data_Termino, Desc_Formacao_Academica) VALUES
(35486431843513, 0, "Lorem ipsum", 5, "Lorem ipsum", "01-02-2008", "01-01-2016", "Lorem ipsum");

INSERT INTO Experiencia_Profissional (CPF, Seq_Experiencia, Nom_Empresa, Cod_Cargo, Cod_CEP,
Data_Inicio, Data_Termino, Desc_Experiencia_Profissional, Cod_Cidade, Cod_Estado) VALUES 
(35486431843513, 0, "Empresa Ficticia", 24, 5168416594, "01-01-2010", "01-01-2011", "Lorem ipsum", 2, 21);

INSERT INTO Competencia_Pessoa_Fisica(CPF, Cod_Competencia, Cod_Proficiencia) VALUES 
(35486431843513, 0, 2);

INSERT INTO Telefone(CPF_CNPJ, Num_Telefone, Tipo_Telefone, DDD, Ramal) VALUES
(35486431843513, "932653269", "C", "31", null)
(23451320354203, "35315894", "F", "31", 2);