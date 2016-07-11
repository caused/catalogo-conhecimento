<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<title>Alterar funcion�rio</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>
<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp"/>
	
	
	
		<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>

				<li>Tecnologias</li>
				<li class="active">Alterar Funcion�rio</li>
			</ul>
		</div>
		<!--breadcrumb-->
	<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAlt"  method="POST" action="alterarFuncionario">
										<div class="panel-heading">
											<h3>Alterar Funcion�rio</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Message Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<input type="hidden" name="id" value="${funcionario.id}">
												
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Nome:
															<input type="text" class="form-control"  maxlength="80" name="nome" value="${funcionario.nome}">
														</label>
													</div>
												</div><!-- /.col -->
								
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Telefone/Celular:
															<input type="text" class="form-control" maxlength="11" placeholder="(99)9999-9999" name="telefone" value="${funcionario.telefone}">
														</label>
													</div>
												</div><!-- /.col -->
											
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Cargo:
																<select class="form-control"  name="cargo.id">
																	<c:forEach items="${cargos}" var="cargos">
																		<option value="${cargos.id}">${cargos.nome}</option>
																	</c:forEach>
																</select>
														</label>
													</div>
												</div><!-- /.col -->

												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Tecnologia:
														</label>
														<div class="checkbox">
															<c:forEach items="${tecnologias}" var="tecnologia" varStatus="count">
																<label class="control-label">		
																	<input type="checkbox" name="listaTecnologia[${count.index}].id" value="${tecnologia.id}" />
																	<span class="custom-checkbox"></span>
																	${tecnologia.nome}
																</label>
																<br>
															</c:forEach>
														</div>	
													</div>
												</div><!-- /.col --> 
												
												
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Neg�cios:
														</label>
														<div class="checkbox">
															<c:forEach items="${negocios}" var="negocio" varStatus="count">
																<label class="control-label">		
																	<input type="checkbox" name="listaNegocio[${count.index}].id" value="${negocio.id}" />
																	<span class="custom-checkbox"></span>
																	${negocio.areaAtuacao}
																</label>
																<br>
															</c:forEach>
														</div>	
													</div>
												</div><!-- /.col -->  
											</div><!-- /.row -->

										</div>
										<div class="panel-footer text-left">
											<button class="btn btn-success" type="submit">Alterar</button>
										</div>
									</form>
								</div><!-- /panel -->
							</div>
						</div><!-- /tab-content -->
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.padding-md -->
		</div><!-- /main-container -->
	</div><!-- /wrapper -->
	
	<!-- Import Logout Action -->
	<c:import url="/resources/jspImport/logout.jsp" />
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	
	
<script type="text/javascript">
$(document).ready(function() {
    $('#formAlt').formValidation({
        err: {
            container: 'tooltip'
        },
//        trigger: 'blur',
        icon: {
            valid: 'fa fa-check',
            invalid: 'fa fa-times',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	nome: {
                validators: {
                    stringLength: {
                        enabled: true,
                        min:4,
                        max:80,
                        message: 'M�nimo de 4 e m�ximo de 80 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigat�rio.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '[A-Za-z�-�\s]+$',
                        message:'Informe apenas letras.'
                    }
                }
            },
            telefone: {
	            validators: {
	                stringLength: {
	                    enabled: true,
	                    min:10,
	                    max:11,
	                    message: 'Digitar n�mero de telefone com DDD.'
	                },
	                notEmpty: {
	                    message: '* Campo Obrigat�rio.'
	                },
	                regexp: {
	                    enabled: true,
	                    regexp: '^[0-9]+$',
	                    message: 'Informe apenas n�meros.'
	                }
	            }
	        },
            cargo: {
	            validators: {
	                 
	                notEmpty: {
	                    message: '* Campo Obrigat�rio.'
	                }
	            }
	        },
	        'tecnologiasArray': {
	            validators: {
	            	choice: {
                        min: 1,
                        message: 'Escolha no m�nimo %s Tecnologia.'
	                }
	            }
	        },  
	        'negociosArray': {
	            validators: {
	            	choice: {
                        min: 1,
                        message: 'Escolha no m�nimo %s �rea de Neg�cio.'
	                }
	            }
	        }            
        }
    });
});	
</script>
</body>
</html>