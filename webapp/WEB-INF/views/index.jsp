<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<title>Catálogo de Conhecimentos</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<c:import url="/resources/jspImport/head.jsp"></c:import>


<script type="text/javascript">
	$(document).ready(function() {
		$("#filtro").tagit();
		
		$("#selectsearch").change(function() {
			  var action = $(this).val() == "/catalogoconhecimento/busca/tecnologia" ? "/catalogoconhecimento/busca/tecnologia" : "/catalogoconhecimento/busca/negocio";
			  $("#search-form").attr("action", action);
			  console.log(action);
			});
	})


</script>

</head>



<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp"></c:import>

	<!-- Conteúdo-->
	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
				<a href="">Principal</a></li>
			</ul>
		</div>

		<!--breadcrumb-->
		<div class="padding-sm">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive"
							style="height:700px; text-align: center">
							
							<div class="row">
								<header> 
									<h3>Catálogo de Conhecimentos</h3>  
								</header>
								<div class="col-xs-8 col-xs-offset-2">
									<form id = "search-form"name="busca" method="post" action="/catalogoconhecimento/busca/tecnologia">
									<div class="input-group">
										<div class="input-group-btn search-panel ">
												<span class="input-group-btn"><select id="selectsearch" class="btn btn-primary bordaArredondada" class="selectpicker"
													name="logica" style="margin-bottom: 11px; height: 33.5px;">
													<optgroup label="Filtros">
														<option value="/catalogoconhecimento/busca/tecnologia">Tecnologia</option>
														<option value = "/catalogoconhecimento/busca/Negocio">Negócio</option>
													</optgroup>
												</select></span>
										</div>
										<input type="hidden" id="filtro" class="form-control"
											name="filtro" /> <span class="input-group-btn">
											<button type="submit" class="btn btn-primary bordaArredondada"
												value="Submit Button"
												style="margin-bottom: 11px; height: 33.5px;">
												<i class="fa fa-search"></i> Pesquisar</button>
										</span>
									</div>
								</form> 
								</div>
							</div>
							<c:import url="/resources/jspImport/msgErro.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /main-container -->

	<c:import url="/resources/jspImport/footer.jsp"></c:import>

	<!-- /wrapper -->

	<a href="" id="scroll-to-top" class="hidden-print"><i
		class="fa fa-chevron-up"></i></a>
	<c:import url="/resources/jspImport/logout.jsp"></c:import>

</body>
</html>