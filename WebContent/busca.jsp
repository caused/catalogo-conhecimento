
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head> 
    <meta charset="utf-8">
    <title>Cat�logo de Conhecimentos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

	<!-- Jquery -->
	<script src="resources/js/jquery-1.10.2.min.js"></script>

	<!-- Bootstrap -->
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>

	<!-- Datatable -->
	<script src='resources/js/jquery.dataTables.min.js'></script>

	<!-- Modernizr -->
	<script src='resources/js/modernizr.min.js'></script>

	<!-- Pace -->
	<script src='resources/js/pace.min.js'></script>

	<!-- Popup Overlay -->
	<script src='resources/js/jquery.popupoverlay.min.js'></script>

	<!-- Slimscroll -->
	<script src='resources/js/jquery.slimscroll.min.js'></script>

	<!-- Cookie -->
	<script src='resources/js/jquery.cookie.min.js'></script>

	<!-- Perfect -->
	<script src="resources/js/app/app.js"></script>



	<!-- JQuery Tag it plugin -->
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="resources/js/tag-it.js" type="text/javascript" charset="utf-8"></script>
	
    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css">
	<link href="resources/bootstrap/css/jquery.tagit.css" rel="stylesheet" type="text/css">
	
	<!-- Font Awesome -->
	<link href="resources/css/font-awesome.min.css" rel="stylesheet">

	<!-- Datatable -->
	<link href="resources/css/jquery.dataTables_themeroller.css" rel="stylesheet">

	<!-- Perfect -->
	<link href="resources/css/app.min.css" rel="stylesheet">
	<link href="resources/css/app-skin.css" rel="stylesheet">
	
	<script type="text/javascript">
	    $(document).ready(function() {
	        $("#txtTeste").tagit();
	    });
	</script>


<body class="overflow-hidden">
	<!-- Overlay Div -->
	<div id="overlay" class="transparent"></div>

	<div id="wrapper" class="preload">
		<div id="top-nav" class="skin-1 fixed">
			<div class="brand" >
				<a href="index.html"><img src="resources/img/logo_resource.png" style="height:40px; padding-left:10px;padding-right:10px;" ></a>
			</div><!-- /brand -->

      <!-- Button menu layout mobile -->
			<button type="button" class="navbar-toggle pull-left" id="sidebarToggle">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>

      <!-- Button menu layout full -->
			<button type="button" class="navbar-toggle pull-left hide-menu" id="menuToggle">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>

			<ul class="nav-notification clearfix">
				<li class="profile dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						<strong>Usu�rio</strong>
						<span><i class="fa fa-chevron-down"></i></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a class="clearfix" href="#">
								<div class="detail">
									<strong>Usu�rio</strong>
									<p class="grey">usu�rio@resource.com</p>
								</div>
							</a>
						</li>
						<li><a tabindex="-1" class="main-link logoutConfirm_open" href="#logoutConfirm"><i class="fa fa-lock fa-lg"></i> Sair</a></li>
					</ul>
				</li>
			</ul>
		</div><!-- /top-nav-->

		<aside class="fixed skin-1">
			<div class="sidebar-inner ">
				<div class="main-menu">
					<ul>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Tecnologias
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=tecnologia.ListarTecnologiaLogica"><span class="submenu-label">Listar Tecnologias</span></a></li>
								<li><a href="mvc?logica=tecnologia.FormularioAdicionarTecnologiaLogica"><span class="submenu-label">Cadastrar Tecnologia</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Busca
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="busca.jsp"><span class="submenu-label">Pesquisar</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Neg�cios
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=negocio.ListarNegocioLogica"><span class="submenu-label">Listar Neg�cios</span></a></li>
								<li><a href="mvc?logica=negocio.FormularioAdicionarNegocioLogica"><span class="submenu-label">Cadastrar Neg�cio</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Projetos
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=projeto.ListarProjetoLogica"><span class="submenu-label">Listar Projetos</span></a></li>
								<li><a href="mvc?logica=projeto.FormularioInserirProjetoLogica"><span class="submenu-label">Cadastrar Projetos</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Equipes
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=equipe.ListarEquipeLogica"><span class="submenu-label">Listar Equipes</span></a></li>
								<li><a href="mvc?logica=equipe.FormularioInserirEquipeLogica"><span class="submenu-label">Cadastrar Equipe</span></a></li>
								<li><a href="mvc?logica=funcionario.ListarFuncionarioLogica"><span class="submenu-label">Listar Funcion�rios</span></a></li>
								<li><a href="mvc?logica=funcionario.FormularioInserirFuncionarioLogica"><span class="submenu-label">Cadastrar Funcion�rio</span></a></li>
								<li><a href="mvc?logica=cargo.ListarCargoLogica"><span class="submenu-label">Listar Cargos</span></a></li>
								<li><a href="mvc?logica=cargo.FormularioAdicionarCargoLogica"><span class="submenu-label">Cadastrar Cargo</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Clientes
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=cliente.ListarClienteLogica"><span class="submenu-label">Listar Clientes</span></a></li>
								<li><a href="mvc?logica=cliente.FormularioAdicionarClienteLogica"><span class="submenu-label">Cadastrar Cliente</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Concorrentes
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=concorrente.ListarConcorrenteLogica"><span class="submenu-label">Listar Concorrentes</span></a></li>
								<li><a href="mvc?logica=concorrente.FormularioAdicionarConcorrenteLogica"><span class="submenu-label">Cadastrar Concorrentes</span></a></li>
							</ul>
						</li>
					</ul>
				</div><!-- /main-menu -->
			</div><!-- /sidebar-inner scrollable-sidebar -->
		</aside>

		<!-- Conte�do-->
		<div id="main-container" style="width:auto">
			<div id="breadcrumb">
				<ul class="breadcrumb">
					 <li><i class="fa fa-home"></i><a href="index.html"> Principal</a></li> 
				</ul>
				
				 
				
				<form class="navbar-form" role="search" method="post" action="mvc?logica=projeto.ListarProjetoPorBuscaLogica">
					<div class="input-group">
						<input type="text" name="tags" id="txtTeste">
						<input type="submit" value="Buscar">		
					</div>
				</form>
			</div><!--breadcrumb-->
		</div><!-- /main-container -->
	</div><!-- /wrapper -->

	<a href="" id="scroll-to-top" class="hidden-print"><i class="fa fa-chevron-up"></i></a>

	<!-- Logout confirmation -->
	<div class="custom-popup width-100" id="logoutConfirm">
		<div class="padding-md">
			<h4 class="m-top-none"> Do you want to logout?</h4>
		</div>

		<div class="text-center">
			<a class="btn btn-success m-right-sm" href="login.html">Logout</a>
			<a class="btn btn-danger logoutConfirm_close">Cancel</a>
		</div>
	</div>


</body>
</html>