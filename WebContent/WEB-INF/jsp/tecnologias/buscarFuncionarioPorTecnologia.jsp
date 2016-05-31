<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Listar Funcion�rios</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import> 
</head>
	<head>
		<title>Lista de Funcion�rios por Tecnologia</title>
		<c:import url="/resources/jspImport/head.jsp"></c:import>
	</head>
	<body class="overflow-hidden">
	
		<c:import url="/resources/jspImport/header.jsp" />
	
		<div id="main-container" style="width: auto">
			<div id="breadcrumb">
				<ul class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.html">
							Principal</a></li>
					<li>Tecnologias</li>
					<li class="active">Lista de Funcion�rios por Tecnologia</li>
				</ul>
			</div>
			<!--breadcrumb-->
		<div class="padding-sm">
				<div class="col-md-12 col-sm-12">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="research">
							<div class="panel panel-default table-responsive">
								<div class="panel-heading">
									<h3>>Lista de Funcion�rios encontrados que dominam: ${tecnologia.nome}</h3>
									
									<!-- Message Erro-->
									<c:import url="/resources/jspImport/msgErro.jsp"></c:import>
									<span class="label label-info pull-right">${fn:length(funcionarios)}
										registros</span>
								</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Nome</th>
											<th>Telefone</th>
											<th>Tecnologia(s)</th>
											<th>Usu�rio</th>
											<th>Email</th>
											<th>Cargo</th>
											<th>CPF</th>
											<th>RG</th>
											<th>Data de Nascimento</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="funcionario" items="${funcionarios}">
											<tr>
												<td>${funcionario.nome}</td>							
												<td>${funcionario.telefone}</td>
													
												<td>
													<select>
													<c:forEach var="tecnologia" items="${funcionario.tecnologias}">
														<option>${tecnologia.nome}</option>
													</c:forEach>
													</select>										
												</td>							
												<td>${funcionario.nomeUser}</td>							
												<td>${funcionario.email}</td>											
												<td>${funcionario.cargo.nome}</td>
												<td>${funcionario.cpf}</td>
												<td>${funcionario.rg}</td>
												<td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.dataNascimento}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>
								<!-- /.padding-md -->
							</div>
							<!-- /panel -->
						</div>
						<!-- /tab2 -->
					</div>
					<!-- /tab-content -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.padding-md -->
		</div>
		<!-- /main-container -->
		<c:import url="/resources/jspImport/logout.jsp"></c:import>
		<c:import url="/resources/jspImport/footer.jsp"></c:import>
	</body>
</html>