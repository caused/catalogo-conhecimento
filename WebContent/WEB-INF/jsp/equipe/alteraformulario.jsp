<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<title>Alterar Equipe</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>
<body class="overflow-hidden">
<c:import url="/resources/jspImport/header.jsp"/>
	
	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.jsp">
						Principal</a></li>
				<li>Equipes</li>
				<li class="active">Alterar Equipe</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAlt" method="POST" action="mvc">
										<div class="panel-heading">
											<h3>Alterar Equipe</h3>
										</div>
										<div class="panel-body">
											<div class="row">
											
												<!-- Message Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
											 
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Nome da Equipe
															<input type="hidden" class="form-control input-sm" name="idEquipe" value="${equipes.id}">
															<input type="text" class="form-control" maxlength="50" name="nome" value="${equipes.nome}">
														</label>
													</div>
													<div class="form-group">
														<textarea class="form-control" rows="8" minlength="20" maxlength="250" name="observacao">${equipes.observacao}</textarea>	
													</div>
												</div><!-- /.col --> 		
											</div><!-- /.row -->
											<input type="hidden" name="logica" value="equipe.AtualizarEquipeLogica">
											<input type="hidden" name="logicaAtual" value="equipe.FormularioAtualizarEquipeLogica">
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
	
	<!-- Import Logout Action -->
	<c:import url="/resources/jspImport/logout.jsp" />
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>

<script>
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
                        min:1,
                        max:50,
                        message: 'M�nimo de 1 e m�ximo de 50 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigat�rio.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '^[A-Za-z�-�0-9\s\@\#\$\%\&\*]',
                        message: 'Nome inv�lido.'
                    }
                }
            },
	        observacao: {
	            validators: {
	                stringLength: {
	                    enabled: true,
	                    min:20,
	                    max:250,
	                    message: 'M�nimo de 20 e m�ximo de 250 caracteres.'
	                },
	                notEmpty: {
	                    message: '* Campo Obrigat�rio.'
	                },
	                regexp: {
	                    enabled: true,
	                    regexp: '^[A-Za-z�-�0-9\s\@\#\$\%\&\*]',
	                    message: 'Observa��o inv�lida.'
	                }
	            }
	        }
        }
    });
});

</script>
</body>
</html>