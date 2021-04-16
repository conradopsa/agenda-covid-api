# Agenda Covid API

Documento da API:
> https://docs.google.com/document/d/1-yxedPdzrNA2zEM2sgXS8XVSvbzMFY4iOzJCjUoPOUc/edit?usp=sharing

Esse projeto está sendo usado o IBM Open Liberty como servidor Java EE.
Não se preocupe, deixamos um tutorial de como rodá-lo sem problemas no Getting Started.

Bem vindo!

## Getting Started
1. Cerfique que a versão do seu JDK é a 1.8
> $ java -version

2. Certifique de estar com o Maven instalado em seu ambiente

Obs.: Utilizamos a versão 3.6.3 nesse projeto.
> $ mvn -version

1. Na pasta raíz do projeto (onde fica o pom.xml), instale os pacotes do maven:
> $ mvn package

4. Execute o comando maven do liberty:
> $ mvn liberty:run

Seu servidor está pronto! :)

http://localhost:9080/api/patient

## Informações Uteis

Nesses projeto está sendo usado o Mongo DB, deixamos um cluster rodando 24h para essa aplicação. Mas se for necessário, é possível executar um banco localmente. Para isso, o passo a passo é:

1. Tenha o Mongo DB instalado

Obs.: Utilizamos a versão 4.4.3

2. Configure o server.env do projeto

Obs.: Ele fica em: .\src\main\liberty\config\server.env

Obs.: Basta por a URI e a Database do MongoDB.


## Endpoints em funcionamento

- GET localhost:9080/api/patients


### Que a força esteja com você!

