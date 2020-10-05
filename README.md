# Teste Desenvolvedor BackEnd - Petz

A solução apresentada foi desenvolvida pensando no desacoplamento das entidades para que não sejam acessíveis diretamente nos serviços. Foram criadas APIs REST para os CRUDs (GET, POST, PUT e DELETE verbs) das entidades Cliente e Pet.
As seguintes tecnologias foram utilizadas:
- Spring Boot (release 2.3.4), 
- Spring Data - abstração do Hibernate/JPA, 
- Spring Security - JWT,
- Lombok, 
- Banco de Dados H2 (em memória).

# CI/CD

O deploy está configurado para executar automaticamente no ambiente do Heroku após push nesse projeto do GitHub.  
Obs.: URL do Heroku: https://teste-simone-petz.herokuapp.com/

# Como acessar os serviços

A execução poderá ser feita através de collection que foi criada para o Postman:
- Importar no Postman a URL enviada por e-mail
- Foram criados dois "environments": PROD (já configurado com a URL do Heroku) e LOCAL (caso queiram baixar o código, subindo os serviços localmente, já foi configurado com URL localhost:8080). Dessa forma, basta escolher o "environment" desejado
- Como é necessária autenticação, executar primeiramente o request "login", assim será gerado token com expiração após 15 minutos







