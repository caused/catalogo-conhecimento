<!-- Overlay Div -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="overlay" class="transparent"></div>

<div id="wrapper" class="preload">
	<div id="top-nav" class="skin-1 fixed">
		<div class="brand">
			<a href="<c:url value='/home/'/>"><img
				src="<c:url value='/resources/img/logo-verity-pequeno.png'/>" class="pull-center"
				style="height: 45px; width: 135px; padding-left: 10px; padding-right: 10px;"></a>
		</div>
		<!-- /brand -->

		<!-- Button menu layout mobile -->
		<button type="button" class="navbar-toggle pull-left"
			id="sidebarToggle">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>

		<!-- Button menu layout full -->
		<button type="button" class="navbar-toggle pull-left hide-menu"
			id="menuToggle">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>

		<ul class="nav-notification clearfix">
			<li class="profile dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <strong>${usuario.nome}</strong> <span><i
						class="fa fa-chevron-down"></i></span>
			</a>
				<ul class="dropdown-menu">
					<li><a class="clearfix" href="#">
							<div class="detail">
								<strong>${usuario.nome}</strong>
								<p class="grey">${usuario.login}</p>
							</div>
					</a></li>
					<li><a tabindex="-1" class="main-link logoutConfirm_open"
						href="<c:url value ='/usuario/logoff'/>"><i class="fa fa-lock fa-lg"></i> Sair</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- /top-nav-->

	<aside class="fixed skin-1">
		<div class="sidebar-inner ">
			<div class="main-menu">
				<ul>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-code fa-lg"></i>
						</span> <span class="text"> Tecnologias </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='/tecnologia/listar'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Tecnologias
								</span>
							</a></li>
							<li><a
								href="<c:url value='/tecnologia/adicionar'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Tecnologia
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-cogs fa-lg"></i>
						</span> <span class="text"> Negócios </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='/negocio/listar'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Negócios
								</span>
							</a></li>
							<li><a href="<c:url value='/negocio/formularioAdicionar'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Negócio
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-sitemap fa-lg"></i>
						</span> <span class="text"> Projetos </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li> <a href="<c:url value='/projeto/listar'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Projetos
								</span>
							</a></li>
							<li><a href="<c:url value='/projeto/adicionar'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Projetos
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-group fa-lg"></i>
						</span> <span class="text"> Equipes </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='/equipe/listar'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Equipes
								</span>
							</a></li>
							<li><a
								href="<c:url value='/equipe/adicionar'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Equipe
								</span>
							</a></li>
							<li><a href="<c:url value='/funcionario/listar'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Funcionários
								</span>
							</a></li>
							<li><a href="<c:url value='/funcionario/adicionar'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Funcionário
								</span>
							</a></li>
							<li><a href="<c:url value='/cargo/listar'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Cargos
								</span>
							</a></li>
							<li><a href="<c:url value='/cargo/adicionar'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Cargo
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-flag fa-lg"></i>
						</span> <span class="text"> Clientes </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='/cliente/listar'/>"> 
							<span class="submenu-label"> <span class="menu-icon"> <i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Clientes
								</span>
							</a></li>
							<li><a href="<c:url value='/cliente/adicionar'/>"> 
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Cliente
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-globe fa-lg"></i>
						</span> <span class="text"> Concorrentes </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li> <a href="<c:url value='/concorrente/listar'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Concorrentes
								</span>
							</a></li>
							<li><a href="<c:url value='/concorrente/adicionar'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Concorrente
								</span>
							</a></li>
						</ul></li>
						<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-filter fa-lg"></i>
						</span> <span class="text"> Perfis </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='/perfil/listar'/>"> 
							<span class="submenu-label"> <span class="menu-icon"> <i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Perfis
								</span>
							</a></li>
							<li><a href="<c:url value='/perfil/adicionar'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Perfil
								</span>
							</a></li>
						</ul></li>
						<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-check fa-lg"></i>
						</span> <span class="text"> Usuários </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='/usuario/listar'/>"> 
							<span class="submenu-label"> <span class="menu-icon"> <i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Usuários
								</span>
							</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /main-menu -->
		</div>
		<!-- /sidebar-inner scrollable-sidebar -->
	</aside>