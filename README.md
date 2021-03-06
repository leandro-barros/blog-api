# Execução da aplicação blog-api

No arquivo **application.properties** altere os valores das configurações **spring.datasource.username** e **spring.datasource.password** de acordo com o usuário e senha do postgre instalado em sua máquina e crie um banco de dados com a descriçaõ **blog_api**. Após isso, baixe as dependências do projeto e, em seguida, execute a classe principal *BlogApiApplication.java*.


**SEGURANÇA**

Para acessar as rotas da API é necessário estar autenticado, com excessão da rota http://localhost:8080/users.
Então o primeiro passo é cadastrar um usuário da seguinte forma:

**USUÁRIOS**

<ul>
  <li>
    POST http://localhost:8080/users (Cadastra um usuário)

Objeto request:
```json
{
  "name": "Leandro Barros",
  "login": "admin",
  "password": "admin"
}
```
  </li>
</ul>

Para fazer a autenticação, deve-se solicitar um token JWT, para isso faça a seguinte chamada: 

POST http://localhost:8080/oauth/token

<ul>
  <li>
    No body da requisição, no Form URL Encoded (x-www-form-urlencoded), passe as seguintes chaves com seus respectivos valores:

| KEY        | VALUE     |
|------------|-----------|
| client     | framework |
| grant_type | password  |
| username   | admin(Usuário cadastrado acima)     | 
| password   | admin (Senha do usuário cadastrado acima     | 

  </li>

  <li>
    No header, passe as seguintes chaves com seus respectivos valores:

| KEY           | VALUE                             |
|---------------|-----------------------------------|
| Content-Type  | application/x-www-form-urlencoded |
| Authorization | Basic ZnJhbWV3b3JrOmZpcnN0        |

  </li>
</ul>

Ao realizar a autenticação, caso os dados informados estejam corretos, a API retornará objeto semelhante a este:
```json
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjY5MDc5OTksInVzZXJfbmFtZSI6InRlc3RlMSIsImp0aSI6Ijk3YmQyNjQzLTNmNTAtNDlkOC1iZTIyLTRjMGQ2ZWVlYjBlOSIsImNsaWVudF9pZCI6ImZyYW1ld29yayIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.GftyR8_mc-dYyFCdxdfn_ex0Z7nIEolnS6D1gttaCUQ",
  "token_type": "bearer",
  "expires_in": 1799,
  "scope": "read write",
  "jti": "97bd2643-3f50-49d8-be22-4c0d6eeeb0e9"
}
```
O valor retornado no atributo "access_token" é o token que deverá ser utilizado nas chamadas que necessitam de autenticação. No header dessas requisições, passe a chave "Authorization" e no valor dela passe o token precedido pela palavra Bearer (ex.: "Bearer eyJhbGciOiJIUzI1..."). Obs.: o token tem duração de 1 dia.


**POSTS**

<ul>
 <li>
    GET http://localhost:8080/posts (Lista todos os posts)
 </li>
 <li>
    POST http://localhost:8080/posts (Cadastra um post)
	
No body, deve-se utilizar o Multipart Form, sendo que obrigatoriamente deve passar a chave "post" com o valor do tipo "Text (Multi-line)". No valor da chave "post" deve passar um objeto como o abaixo:
Obs: No "Text (Multi-line)" selecione o "Editor syntax" como "JSON"

```json
{
	"text": "String",
	"title": "String",
	"links" : [
		{
			"link": "String"
		}
	]
}
```
Para fazer o envio das imagens, deve-se passar a chave "files" com o valor do tipo "File" e anexar a imagem. Obs.: todas as imagens anexadas devem ter como chave a palavra "files".
	 
A seguir é exibido um exemplo de como a requisição deve ser feita.

![post](https://user-images.githubusercontent.com/13985064/126583005-7e7e1ac1-d3b1-44e0-894a-179f8a8be7a2.jpeg)
  </li>
  <li>
    DELETE http://localhost:8080/posts/{id} (Exclui um determinado post)
  </li>
</ul>

**COMENTÁRIOS**

<ul>
  <li>
    POST http://localhost:8080/posts/{idPost}/comment (Cadastra um comentário para um determinado post)

Objeto request:
```json
{
  "description": "String"
}
```
  </li>
  <li>
    DELETE http://localhost:8080/comments/{id} (Exclui um determinado comentário)
  </li>
</ul>

**ÁLBUNS DE FOTOS**

<ul>
  <li>
    GET http://localhost:8080/albuns (Lista todos os álbuns)
  </li>
  <li>
    POST http://localhost:8080/albuns (Cadastra um álbum de foto)
  
No body, deve-se utilizar o Multipart Form, sendo que obrigatoriamente deve passar a chave "album" com o valor do tipo "Text (Multi-line)". No valor da chave "album" deve passar um objeto como o abaixo:
Obs: No "Text (Multi-line)" selecione o "Editor syntax" como "JSON"
	  
```json
{
	"description": "String"
}
```
Para fazer o envio das imagens, deve-se passar a chave "files" com o valor do tipo "File" e anexar a imagem. Obs.: todas as imagens anexadas devem ter como chave a palavra "files".
	  
A seguir é exibido um exemplo de como a requisição deve ser feita.
	  
![album](https://user-images.githubusercontent.com/13985064/126583242-e7086e20-0ea8-4a57-abac-db85249775ba.jpeg)
  </li>
  <li>
    DELETE http://localhost:8080/albuns/{id} (Exclui um determinado álbum)
  </li>
</ul>
