<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar Concorrente</title>
</head>
<body>

<form method="post" action="mvc">
	C�digo: <input type = "text" name = "id" value="${listarconcorrente.id}"/><br/>
	Nome Concorrente: <input type = "text" name = "nome" value="${listarconcorrente.nome}" /><br/>
	Descri��o: <input type = "text" name = "descricao" value= "${listarconcorrente.descricao}"/><br/>
	
	<input type="hidden" name="logica" value ="AlterarConcorrenteLogica"/>
	<input type="submit" value ="Alterar">

</body>
</html>