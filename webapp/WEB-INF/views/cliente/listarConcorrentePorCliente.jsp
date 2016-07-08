<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Lista de Concorrentes do Cliente</title>
<c:import url="/resources/jspImport/head.jsp"></c:import>

	<script type="text/javascript">
	
		function add() {
			var concorrente = $("#concorrente option:selected").text();
			var idConcorrente = $("#concorrente option:selected").val();
			var valorConcorrente = $("#valorConcorrente").val();
			var count = $('#tbConcorrente tbody tr').length;
			if (valorConcorrente != "" && valorConcorrente != "0") {
				addConcorrente(concorrente, valorConcorrente, count);
			}
		}
	
		function addConcorrente(nome, valor, count) {
			var row = $("<tr />")
			$("#tbConcorrente tbody").append(row);
			row.append("<td>" + nome + "</td>");
			row.append("<td>" + valor + "</td>");
			row.append("<td><button class='delete' type='button'>-</button></td>");
			row.append("<input type='hidden' name='listaConcorrentes[" + count + "].concorrente.id' id='listaConcorrentes[" + count + "].concorrente.id' value='" + idConcorrente + "'  />");
			row.append("<input type='hidden' name='listaConcorrentes[" + count + "].valorHora' id='listaConcorrentes[" + count + "].valorHora' value='" + valor + "' /></td>");
			$("#countConcorrente").val(count);
		}

		$(document).ready(function() {
			$("#tbConcorrente").on('click', '.delete', function() {
				$(this).closest('tr').remove();
			});
		});

	</script>
</head>

<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp" />

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>

				<li><a href="<c:url value='/cliente/listarCliente'/>">Cliente</a></li>
				<li class="active">Lista de Concorrentes do Cliente</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive">
							<div class="panel-heading">
								<h3>Cliente ${clienteBean.nome}</h3>
								
								<!-- Message Erro-->
								<c:import url="/resources/jspImport/msgErro.jsp"/>
								
								<span class="label label-info pull-right">${fn:length(listaConcorrenteCliente)}
									registros</span>
							</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Nome do Concorrente</th>
											<th>Descri��o</th>
											<th>Valor/hora</th>
											<th style="width: 20px;">Excluir</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="concorrenteCliente" items="${listaConcorrenteCliente}">
											<tr>
												<td>${concorrenteCliente.concorrente.nome}</td>
												<td>${concorrenteCliente.concorrente.descricao}</td>
												<td>${concorrenteCliente.valorHora}</td>
												<td style="text-align: center;">
												
												<a href="<c:url value='removerConcorrenteDoCliente'> <c:param name='idCliente' value='${concorrenteCliente.cliente.id}'/> <c:param name='idConcorrenteCliente' value='${concorrenteCliente.id}'/></c:url>"><i class="fa fa-times fa-lg"></i></a>
												
												</td> 
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<form class="no-margin" id="formAdd"  method="POST" action="/catalogoconhecimento/cliente/adicionarConcorrenteNoCliente">
								<div class="panel-heading">
									<h3>Adicionar Concorrente</h3>
								</div>
								<div class="panel-body">
									<div class="row">
										
										<!-- Messagem Erro-->
										<c:import url="/resources/jspImport/msgErro.jsp"/>
										<div class="col-md-8">
											<h4>Adicionar Concorrentes</h4>
											<select name = "concorrente" id="concorrente">
												<c:forEach var="concorrente" items="${listaConcorrente}">
													<option>${concorrente.nome}</option>
												</c:forEach>
											</select> 
											<input name = "valorConcorrente" id="valorConcorrente" type="text">
											<button type="button" onclick="add()">+</button>
											<br> <br>
											<table class="table table-striped" id="tbConcorrente">
												<thead>
													<tr>
														<th>Concorrente</th>
														<th>Valor</th>
														<th>Remover</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
											<input type="hidden" name = "idCliente" value = "${clienteBean.id }"/>
											<input type="hidden" id="countConcorrente" name="countConcorrente" value="0">
											
										</div><!-- /.col -->
									</div><!-- /.row -->
								</div>
								<div class="panel-footer text-left">
									<button class="btn btn-success" type="submit">Adicionar</button>
								</div>
							</form>
							<!-- /.padding-md -->
						</div>
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
	<!-- main-container -->
	
		<!-- Import Logout Action -->
		<c:import url="/resources/jspImport/logout.jsp" />

		<c:import url="/resources/jspImport/footer.jsp"></c:import>

</html>
