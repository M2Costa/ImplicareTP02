/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Gabriel
 * Created: 19/07/2018
 */

/*
Created: 30/06/2018
Modified: 02/07/2018
Project: TCC_Implicare
Model: PostgreSQL 9.4
Company: CEFET-MG
Author: Gabriel Vinicius dos Santos
Database: PostgreSQL 9.4
*/


-- Create tables section -------------------------------------------------

-- Table Cargo

CREATE TABLE "Cargo"(
 "Cod_Cargo" Integer NOT NULL,
 "Nom_Cargo" Character varying NOT NULL
)
;

-- Add keys for table Cargo

ALTER TABLE "Cargo" ADD CONSTRAINT "Key1" PRIMARY KEY ("Cod_Cargo")
;

-- Table Usuario

CREATE TABLE "Usuario"(
 "CPF_CNPJ" Bigint NOT NULL,
 "Email" Character varying NOT NULL,
 "Senha" Character(44) NOT NULL,
 "Foto" Bytea,
 "Cod_CEP" Bigint,
 "Endereco" Character varying NOT NULL,
 "Desc_Usuario" Character varying
)
;

-- Create indexes for table Usuario

CREATE INDEX "IX_Relationship5" ON "Usuario" ("Cod_CEP")
;

-- Add keys for table Usuario

ALTER TABLE "Usuario" ADD CONSTRAINT "Key3" PRIMARY KEY ("CPF_CNPJ")
;

-- Table Area_Estudo

CREATE TABLE "Area_Estudo"(
 "Cod_Area_Estudo" Integer NOT NULL,
 "Nom_Area_Estudo" Character varying NOT NULL
)
;

-- Add keys for table Area_Estudo

ALTER TABLE "Area_Estudo" ADD CONSTRAINT "Key6" PRIMARY KEY ("Cod_Area_Estudo")
;

-- Table Competencia

CREATE TABLE "Competencia"(
 "Cod_Competencia" Integer NOT NULL,
 "Nom_Competencia" Character varying NOT NULL
)
;

-- Add keys for table Competencia

ALTER TABLE "Competencia" ADD CONSTRAINT "Key7" PRIMARY KEY ("Cod_Competencia")
;

-- Table Estado

CREATE TABLE "Estado"(
 "Cod_Estado" Integer NOT NULL,
 "Nom_Estado" Character varying NOT NULL
)
;

-- Add keys for table Estado

ALTER TABLE "Estado" ADD CONSTRAINT "Key8" PRIMARY KEY ("Cod_Estado")
;

-- Table Cidade

CREATE TABLE "Cidade"(
 "Cod_Estado" Integer NOT NULL,
 "Cod_Cidade" Integer NOT NULL,
 "Nom_Cidade" Character varying NOT NULL
)
;

-- Add keys for table Cidade

ALTER TABLE "Cidade" ADD CONSTRAINT "Key9" PRIMARY KEY ("Cod_Cidade","Cod_Estado")
;

-- Table CEP

CREATE TABLE "CEP"(
 "Cod_CEP" Bigint NOT NULL,
 "Cod_Cidade" Integer,
 "Cod_Estado" Integer
)
;

-- Create indexes for table CEP

CREATE INDEX "IX_Relationship4" ON "CEP" ("Cod_Cidade","Cod_Estado")
;

-- Add keys for table CEP

ALTER TABLE "CEP" ADD CONSTRAINT "Key10" PRIMARY KEY ("Cod_CEP")
;

-- Table Empresa

CREATE TABLE "Empresa"(
 "CNPJ" Bigint NOT NULL,
 "Nom_Razao_Social" Character varying NOT NULL,
 "Nome_Fantasia" Character varying NOT NULL
)
;

-- Add keys for table Empresa

ALTER TABLE "Empresa" ADD CONSTRAINT "Key11" PRIMARY KEY ("CNPJ")
;

-- Table Pessoa_Fisica

CREATE TABLE "Pessoa_Fisica"(
 "CPF" Bigint NOT NULL,
 "Nome" Character varying NOT NULL,
 "Data_Nascimento" Date NOT NULL
)
;

-- Add keys for table Pessoa_Fisica

ALTER TABLE "Pessoa_Fisica" ADD CONSTRAINT "Key13" PRIMARY KEY ("CPF")
;

-- Table Proficiencia

CREATE TABLE "Proficiencia"(
 "Cod_Proficiencia" Integer NOT NULL,
 "Nivel_Proficiencia" Character varying NOT NULL
)
;

-- Add keys for table Proficiencia

ALTER TABLE "Proficiencia" ADD CONSTRAINT "Key15" PRIMARY KEY ("Cod_Proficiencia")
;

-- Table Formacao_Academica

CREATE TABLE "Formacao_Academica"(
 "CPF" Bigint NOT NULL,
 "seq_formacao" Smallint NOT NULL,
 "Instituicao_Ensino" Character varying NOT NULL,
 "Cod_Area_Estudo" Integer,
 "Atividades_Desenvolvidas" Character varying,
 "Data_Inicio" Date NOT NULL,
 "Data_Termino" Date NOT NULL,
 "Desc_Formacao_Academica" Character varying
)
;

-- Create indexes for table Formacao_Academica

CREATE INDEX "IX_Relationship20" ON "Formacao_Academica" ("Cod_Area_Estudo")
;

-- Add keys for table Formacao_Academica

ALTER TABLE "Formacao_Academica" ADD CONSTRAINT "Key16" PRIMARY KEY ("CPF","seq_formacao")
;

-- Table Experiencia_Profissional

CREATE TABLE "Experiencia_Profissional"(
 "CPF" Bigint NOT NULL,
 "Seq_Experiencia" Integer NOT NULL,
 "Nom_Empresa" Character varying NOT NULL,
 "Cod_Cargo" Integer NOT NULL,
 "Cod_CEP" Bigint,
 "Data_Inicio" Date NOT NULL,
 "Data_Termino" Date,
 "Desc_Experiencia_Profissional" Character varying,
 "Cod_Cidade" Integer,
 "Cod_Estado" Integer
)
;

-- Create indexes for table Experiencia_Profissional

CREATE INDEX "IX_Relationship24" ON "Experiencia_Profissional" ("Cod_CEP")
;

CREATE INDEX "IX_Relationship45" ON "Experiencia_Profissional" ("Cod_Cidade","Cod_Estado")
;

-- Add keys for table Experiencia_Profissional

ALTER TABLE "Experiencia_Profissional" ADD CONSTRAINT "Key17" PRIMARY KEY ("CPF","Cod_Cargo")
;

-- Table Competencia_Pessoa_Fisica

CREATE TABLE "Competencia_Pessoa_Fisica"(
 "CPF" Bigint NOT NULL,
 "Cod_Competencia" Integer NOT NULL,
 "Cod_Proficiencia" Integer
)
;

-- Create indexes for table Competencia_Pessoa_Fisica

CREATE INDEX "IX_Relationship44" ON "Competencia_Pessoa_Fisica" ("Cod_Proficiencia")
;

-- Add keys for table Competencia_Pessoa_Fisica

ALTER TABLE "Competencia_Pessoa_Fisica" ADD CONSTRAINT "Key18" PRIMARY KEY ("CPF","Cod_Competencia")
;

-- Table Telefone

CREATE TABLE "Telefone"(
 "CPF_CNPJ" Bigint NOT NULL,
 "Num_Telefone" Character(9) NOT NULL,
 "Tipo_Telefone" Character(1) NOT NULL,
 "DDD" Bit(2) NOT NULL,
 "Ramal" Integer
)
;

-- Add keys for table Telefone

ALTER TABLE "Telefone" ADD CONSTRAINT "Key19" PRIMARY KEY ("CPF_CNPJ","Num_Telefone")
;

-- Table Cargo_Interesse

CREATE TABLE "Cargo_Interesse"(
 "CPF" Bigint NOT NULL,
 "Cod_Cargo" Integer NOT NULL
)
;

-- Add keys for table Cargo_Interesse

ALTER TABLE "Cargo_Interesse" ADD CONSTRAINT "Key21" PRIMARY KEY ("Cod_Cargo","CPF")
;

-- Table Vaga

CREATE TABLE "Vaga"(
 "CNPJ" Bigint NOT NULL,
 "Cod_Cargo" Integer NOT NULL,
 "dat_publicacao" Bigint NOT NULL,
 "Num_Vagas" Integer,
 "Carga_Horaria" Bigint NOT NULL,
 "Remuneração" Money,
 "Desc_Vaga" Character varying,
 "Status_Vaga" Bit(1) NOT NULL
)
;

-- Add keys for table Vaga

ALTER TABLE "Vaga" ADD CONSTRAINT "Key23" PRIMARY KEY ("Cod_Cargo","CNPJ","dat_publicacao")
;

-- Table Candidato_Vaga

CREATE TABLE "Candidato_Vaga"(
 "CPF" Bigint NOT NULL,
 "Cod_Cargo" Integer NOT NULL,
 "CNPJ" Bigint NOT NULL,
 "dat_publicacao" Bigint NOT NULL,
 "Status_Candidato" Character(1) NOT NULL
)
;
COMMENT ON COLUMN "Candidato_Vaga"."Status_Candidato" IS 'A aceito
R '
;

-- Add keys for table Candidato_Vaga

ALTER TABLE "Candidato_Vaga" ADD CONSTRAINT "Key24" PRIMARY KEY ("CPF","Cod_Cargo","CNPJ","Attribute1")
;

-- Table Candidato_vaga_dialogo

CREATE TABLE "Candidato_vaga_dialogo"(
 "CPF" Bigint NOT NULL,
 "Cod_Cargo" Integer NOT NULL,
 "CNPJ" Bigint NOT NULL,
 "dat_publicacao" Bigint NOT NULL,
 "dat_dialogo" Timestamp(0) NOT NULL,
 "txt_dialogo" Text NOT NULL,
 "idt_empresa_candidato" Character(1) NOT NULL
)
;

-- Add keys for table Candidato_vaga_dialogo

ALTER TABLE "Candidato_vaga_dialogo" ADD CONSTRAINT "Key25" PRIMARY KEY ("CPF","Cod_Cargo","CNPJ","Attribute1","dat_dialogo")
;

-- Table Cargo_areaestudo

CREATE TABLE "Cargo_areaestudo"(
 "Cod_Area_Estudo" Integer NOT NULL,
 "Cod_Cargo" Integer NOT NULL
)
;

-- Add keys for table Cargo_areaestudo

ALTER TABLE "Cargo_areaestudo" ADD CONSTRAINT "Key26" PRIMARY KEY ("Cod_Area_Estudo","Cod_Cargo")
;
-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE "Cidade" ADD CONSTRAINT "Relationship1" FOREIGN KEY ("Cod_Estado") REFERENCES "Estado" ("Cod_Estado") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "CEP" ADD CONSTRAINT "Relationship4" FOREIGN KEY ("Cod_Cidade", "Cod_Estado") REFERENCES "Cidade" ("Cod_Cidade", "Cod_Estado") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Usuario" ADD CONSTRAINT "Relationship5" FOREIGN KEY ("Cod_CEP") REFERENCES "CEP" ("Cod_CEP") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Empresa" ADD CONSTRAINT "Relationship6" FOREIGN KEY ("CNPJ") REFERENCES "Usuario" ("CPF_CNPJ") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Pessoa_Fisica" ADD CONSTRAINT "Relationship9" FOREIGN KEY ("CPF") REFERENCES "Usuario" ("CPF_CNPJ") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Formacao_Academica" ADD CONSTRAINT "Relationship19" FOREIGN KEY ("CPF") REFERENCES "Pessoa_Fisica" ("CPF") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Formacao_Academica" ADD CONSTRAINT "Relationship20" FOREIGN KEY ("Cod_Area_Estudo") REFERENCES "Area_Estudo" ("Cod_Area_Estudo") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Experiencia_Profissional" ADD CONSTRAINT "Relationship21" FOREIGN KEY ("CPF") REFERENCES "Pessoa_Fisica" ("CPF") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Experiencia_Profissional" ADD CONSTRAINT "Relationship22" FOREIGN KEY ("Cod_Cargo") REFERENCES "Cargo" ("Cod_Cargo") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Experiencia_Profissional" ADD CONSTRAINT "Relationship24" FOREIGN KEY ("Cod_CEP") REFERENCES "CEP" ("Cod_CEP") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Competencia_Pessoa_Fisica" ADD CONSTRAINT "Relationship25" FOREIGN KEY ("CPF") REFERENCES "Pessoa_Fisica" ("CPF") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Competencia_Pessoa_Fisica" ADD CONSTRAINT "Relationship26" FOREIGN KEY ("Cod_Competencia") REFERENCES "Competencia" ("Cod_Competencia") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Telefone" ADD CONSTRAINT "Relationship28" FOREIGN KEY ("CPF_CNPJ") REFERENCES "Usuario" ("CPF_CNPJ") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Cargo_Interesse" ADD CONSTRAINT "Relationship33" FOREIGN KEY ("Cod_Cargo") REFERENCES "Cargo" ("Cod_Cargo") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Cargo_Interesse" ADD CONSTRAINT "Relationship34" FOREIGN KEY ("CPF") REFERENCES "Pessoa_Fisica" ("CPF") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Vaga" ADD CONSTRAINT "Relationship39" FOREIGN KEY ("Cod_Cargo") REFERENCES "Cargo" ("Cod_Cargo") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Candidato_Vaga" ADD CONSTRAINT "Relationship42" FOREIGN KEY ("CPF") REFERENCES "Pessoa_Fisica" ("CPF") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Candidato_Vaga" ADD CONSTRAINT "Relationship43" FOREIGN KEY ("Cod_Cargo", "CNPJ", "Attribute1") REFERENCES "Vaga" ("Cod_Cargo", "CNPJ", "dat_publicacao") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Competencia_Pessoa_Fisica" ADD CONSTRAINT "Relationship44" FOREIGN KEY ("Cod_Proficiencia") REFERENCES "Proficiencia" ("Cod_Proficiencia") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Experiencia_Profissional" ADD CONSTRAINT "Relationship45" FOREIGN KEY ("Cod_Cidade", "Cod_Estado") REFERENCES "Cidade" ("Cod_Cidade", "Cod_Estado") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Vaga" ADD CONSTRAINT "Relationship46" FOREIGN KEY ("CNPJ") REFERENCES "Empresa" ("CNPJ") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Candidato_vaga_dialogo" ADD CONSTRAINT "Relationship47" FOREIGN KEY ("CPF", "Cod_Cargo", "CNPJ", "Attribute1") REFERENCES "Candidato_Vaga" ("CPF", "Cod_Cargo", "CNPJ", "Attribute1") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Cargo_areaestudo" ADD CONSTRAINT "Relationship48" FOREIGN KEY ("Cod_Area_Estudo") REFERENCES "Area_Estudo" ("Cod_Area_Estudo") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Cargo_areaestudo" ADD CONSTRAINT "Relationship49" FOREIGN KEY ("Cod_Cargo") REFERENCES "Cargo" ("Cod_Cargo") ON DELETE NO ACTION ON UPDATE NO ACTION
;

