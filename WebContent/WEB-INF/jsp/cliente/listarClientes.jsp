<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Clientes</title>
</head>
<body>
	<table border="1px">
		<tr>
			<th>Nome Cliente</th>
			<th>Logradouro</th>
			<th>CEP</th>
			<th>Numero</th>
			<th>CNPJ</th>
			<th>E-mail</th>
			<th>A��es</th>
		</tr>

		<c:forEach var="cliente" items="${clientes}">
			<tr>
				
				<td>${cliente.nome}</td>
				<td>${cliente.logradouro}</td>
				<td>${cliente.cep}</td>
				<td>${cliente.numero}</td>
				<td>${cliente.cnpj}</td>
				<td>${cliente.email}</td>


				<td><a
					href="mvc?logica=cliente.RemoverClienteLogica&id=${cliente.id}">Remover</a>
				</td>						 
				<td><a
					href="mvc?logica=cliente.FormularioAlterarClienteLogica&id=${cliente.id}">Alterar</a>
				</td>
				<td>
					<a href="mvc?logica=concorrente.ListarConcorrentePorClienteLogica&id=${cliente.id}">Listar Concorrentes</a>
				</td>						
			</tr>
		</c:forEach>

	</table>
	<br>
	<p>
		<a href="mvc?logica=cliente.FormularioAdicionarClienteLogica">Adicionar
			um novo cliente</a>
	</p>
	<br>
	<p><a href="index.html">Retornar a pagina inicial</a><p>
</body>
</html>