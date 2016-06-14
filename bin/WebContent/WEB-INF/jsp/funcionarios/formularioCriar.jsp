<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form method="post" action="mvc?logica=funcionario.InserirFuncionarioLogica">

		Nome:<input type="text" name=nome> 
		
		Telefone: <input type="text" name="telefone">
		
		Cargos:<select name="cargo">
			<c:forEach items="${cargos}" var="cargos">
				<option value="${cargos.nomeCargo}">${cargos.nomeCargo}</option>
			</c:forEach>
		</select>

		<br>
		Tecnologias
		<c:forEach items="${tecnologias}" var="tecnologias">
			<br>
			<input type="checkbox" name="tecnologiasArray[]"value="${tecnologias.nomeTecnologia}" />${tecnologias.nomeTecnologia}
			<br>
		</c:forEach>
		
		Email <input type="email" name="email">
		
		Nome de usuario: <input type="text" name="usuario">

		<input type="hidden" name="logica" value="funcionario.AdicionarFuncionarioLogica" />

		<input type="submit" value="Adicionar" />
	</form>

</body>
</html>