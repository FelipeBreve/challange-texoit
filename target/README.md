# Teste - *Golden Raspberry Awards*
API resposavel por listar os filmes vencedores do da 
categorias de **Pior Filme** *Golden Raspberry Awards*
(Sendo o produtor com maior intervalo de tempo entre dois prêmios consecutivos e 
o produtor com menor intervalo de tempo entre dois prêmios consecutivos).

# Observacao
> Alguns comandos podem ser executados de forma diferente no linux, dependendo da situacao

# Executando a aplicacao (Sem docker)
Acessar o projeto
```bash
cd texoit-challange
```
Executar o projeto
```bash
.\mvnw spring-boot:run
# ou
./mvnw spring-boot:run #Linux
```

# Executando a aplicacao (Com Docker)

Acessar o projeto
```bash
cd texoit-challange
```

Criar a imagem do docker:
```bash
docker build -t texoit-app .
```

Executar a imagem do docker:
```bash
docker run -p 8081:8081 --name texoit-app texoit-app
```


### Comandos para testes

Para executar os testes
```bash
.\mvnw test
# ou 
./mvnw test; #Linux
```

### Swagger
Para acessar o Swagger [LINK](http://localhost:8081/swagger-ui/index.html)


