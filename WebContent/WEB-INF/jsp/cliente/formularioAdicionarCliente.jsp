<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Adicionar Cliente</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>
<body>
	<!-- Message Erro-->
	<c:import url="/resources/jspImport/msgErro.jsp"/>
	<form method="POST" action="mvc?logica=cliente.AdicionarClienteLogica">
		<p>
			Nome:<input type="text" name="nome">
		</p>
		<p>
			Logradouro:<input type="text" name="logradouro">
		</p>
		<p>
			Numero:<input type="text" name="numero">
		</p>
		<p>
			CEP:<input type="text" name="cep">
		</p>
		<p>
			CNPJ:<input type="text" name="cnpj">
		</p>
		<p>
			Email:<input type="text" name="email">
		</p>
		<input type="hidden" name="logicaAtual" value="cliente.FormularioAdicionarClienteLogica"> 
		<input type="hidden" name="logica" value="cliente.AdicionarClienteLogica">
		<p>
			<input type="submit" value="Adicionar">
		</p>
	</form>
		
	<!-- Import Logout Action -->
	<c:import url="/resources/jspImport/logout.jsp" />
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	
</body>
</html>