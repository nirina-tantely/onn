<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<c:if test="${empty currentuser}">
	<SCRIPT LANGUAGE="JavaScript">
		document.location.href = "login.do"
	</SCRIPT>
</c:if>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ONN DATA PORTAL</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="dist/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
<link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">

<link rel="stylesheet" href="css/model.css">

<link rel="icon" type="image/png" href="favicon.png">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<style>
#map-container {
	height: 500px
}
</style>

</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<!-- Logo -->
			<a href="" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"> <img src="images/mini_logo_onn.png"
					alt="ONN" style="height: 50px; width: 50px;">
			</span> <!-- logo for regular state and mobile devices --> <span
				class="logo-lg"> <img src="images/mini_logo_onn.png"
					alt="ONN" style="height: 50px; width: 50px;"> <b>PORTAIL
						ONN</b>
			</span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<div class="container-fluid">
					<!-- Sidebar toggle button-->
					<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
						role="button"> <span class="sr-only">Toggle navigation</span>
					</a>

					<!-- Navbar Right Menu -->
					<div class="navbar-custom-menu">

						<ul class="nav navbar-nav">

							<!-- User Account Menu -->
							<li class="dropdown user user-menu">
								<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
								data-toggle="dropdown"> <!-- The user image in the navbar-->
									<img src="images/user_${currentuser.role.id}.png"
									class="user-image" alt="User Image"> <!-- hidden-xs hides the username on small devices so only the image appears. -->
									<span class="hidden-xs">${currentuser.nom}</span>
							</a>
								<ul class="dropdown-menu">
									<!-- The user image in the menu -->
									<li class="user-header"><img
										src="images/user_${currentuser.role.id}.png"
										class="img-circle" alt="User Image">

										<p>${currentuser.nom}</p></li>
									<!-- Menu Footer-->
									<li class="user-footer">
										<div class="pull-right">
											<a href="deconnecter.do" class="btn btn-primary btn-flat">Deconnexion</a>
										</div>
									</li>
								</ul>
							</li>
							<!-- Control Sidebar Toggle Button -->
							<li><a href="#" data-toggle="control-sidebar"><i
									class="fa fa-question-circle"></i></a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<ul class="sidebar-menu">
					<li class="header">MENU DE NAVIGATION</li>

					<!-- Menu de filtrage du la carte -->
					<c:choose>
						<c:when test="${currentView != 'HOME'}">
							<li class="treeview"><a href="map.do"><i
									class="fa fa-file-o"></i> <span>DONNEES PUBLIQUES</span> <span
									class="pull-right-container"> <i
										class="fa fa-angle-left pull-right"></i>
								</span> </a></li>
						</c:when>
						<c:otherwise>
							<li class="active treeview"><a href="#"><i
									class="fa fa-file-o"></i> <span>DONNEES PUBLIQUES</span> <span
									class="pull-right-container"> <i
										class="fa fa-angle-left pull-right"></i>
								</span> </a>
								<ul class="treeview-menu">
									<li>
										<div class="form-group">
											<a><i class="fa  fa-angle-double-right"></i> <span>Choisir
													une région</span></a> <select class="form-control" id="selectRegion"
												onchange="onSelectRegion();onMapSelect(this.value, 'region');onIntervenantSelect();">
												<option value="VIDE" onselect="location.reload();">Choisir...</option>
												<c:forEach var="region" items="${regions}">
													<option id="${region.idRegion}" value="${region.idRegion}">${region.nomRegion}</option>
												</c:forEach>
											</select>
										</div>
									</li>
									<li>
										<div class="form-group" id="divSelectCommune"></div>
									</li>
									<li>
										<div class="form-group" id="divSelectFokontany"></div>
									</li>
									<li>
										<div class="form-group">
											<a><i class="fa  fa-angle-double-right"></i> <span>Choisir
													un intervenant</span></a> <select class="form-control"
												id="selectIntervenant"
												onchange="onMapSelect('VIDE', 'VIDE'); onIntervenantSelect();">
												<option value="VIDE">Tout</option>
												<c:forEach var="intervenant" items="${intervenants}">
													<option id="${intervenant.idIntervenant}"
														value="${intervenant.idIntervenant}">${intervenant.nom}</option>
												</c:forEach>
											</select>
										</div>
									</li>
									<li>
										<div class="form-group">
											<a><i class="fa  fa-angle-double-right"></i> <span>Choisir
													une année</span></a> <select class="form-control" id="selectAnnee"
												onchange="onMapSelect('VIDE', 'VIDE'); onIntervenantSelect();">
												<option value="VIDE">Annee courante</option>
												<c:forEach var="annee" items="${annees}">
													<option id="${annee}" value="${annee}">${annee}</option>
												</c:forEach>
											</select>
										</div>
									</li>
									<!-- 
									<li>
										<div class="form-group" id="divCheckAfficherTout">
											<input type="checkbox" id="cbox" value="AfficherTout"
												onchange="onCheckAfficherTout(this);" /> <a><label
												for="cbox">Afficher tout.</label></a>
										</div>
									</li>
									 -->
								</ul></li>
						</c:otherwise>
					</c:choose>


					<c:if test="${currentuser.role.id == 1 or currentuser.role.id == 2 }">
						<!-- Menu public -->
						<c:choose>
							<c:when test="${currentView != 'SMS'}">
								<li class="treeview"><a href="smsmap.do"><i
										class="fa fa-location-arrow"></i> <span>DONNEES SMS </span> <span
										class="pull-right-container"> <i
											class="fa fa-angle-left pull-right"></i>
									</span> </a></li>
							</c:when>
							<c:otherwise>
								<li class="active treeview"><a href="#"><i
										class="fa fa-location-arrow"></i> <span>DONNEES SMS</span> <span
										class="pull-right-container"> <i
											class="fa fa-angle-left pull-right"></i>
									</span> </a>
									<ul class="treeview-menu">
										<li>
											<div class="form-group">
												<a><i class="fa  fa-angle-double-right"></i> <span>Choisir
														une région</span></a> <select class="form-control" id="selectRegion"
													onchange="onSelectRegion();onSMSMapSelect(this.value, 'region')">
													<option value="VIDE" onselect="location.reload();">Choisir...</option>
													<c:forEach var="region" items="${regions}">
														<option id="${region.idRegion}" value="${region.idRegion}">${region.nomRegion}</option>
													</c:forEach>
												</select>
											</div>
										</li>
										<li>
											<div class="form-group" id="divSelectCommune"></div>
										</li>
										<li>
											<div class="form-group" id="divSelectFokontany"></div>
										</li>
										<li>
											<div class="form-group">
												<a><i class="fa  fa-angle-double-right"></i> <span>Choisir
														une année</span></a> <select class="form-control" id="selectAnnee"
													onchange="onSMSMapSelect('VIDE', 'VIDE');">
													<option value="VIDE">Annee courante</option>
													<c:forEach var="annee" items="${annees}">
														<option id="${annee}" value="${annee}">${annee}</option>
													</c:forEach>
												</select>
											</div>
										</li>
									</ul></li>
							</c:otherwise>
						</c:choose>
					</c:if>


					<c:if test="${currentuser.role.id == 1}">
						<!-- Menu de administration -->
						<li class="treeview"><a href="#"><i
								class="fa fa-link"></i> <span>ADMINISTRATION</span> <span
								class="pull-right-container"> <i
									class="fa fa-angle-left pull-right"></i>
							</span> </a>
							<ul class="treeview-menu">

								<li><a href="gestion_donnes.do"><i
										class="fa  fa-hand-rock-o"></i> <span>Importation
											manuelle</span></a></li>
								<li><a href="gestion_acces.do"><i class="fa  fa-ban"></i>
										<span>Gestion d'accès</span></a></li>
							</ul></li>

					</c:if>
				</ul>

				<div class="user-panel" align="center">
					<p>
						<img src="images/logo_onn.jpg" alt="ONN"
							style="height: 175px; width: 150px;">
					</p>
					<p>
						<img src="images/logo_secaline.png" alt="SECALINE"
							style="height: 175px; width: 150px;">
					</p>
				</div>

			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<tiles:insertAttribute name="principal" />
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- To the right -->
			<div class="pull-right hidden-xs">Ver 1.0</div>
			<!-- Default to the left -->
			<strong>Copyright &copy; 2017 <a href="#">Rd Consulting</a>.
			</strong> Tous droits réservés.
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class="active"><a href="#control-sidebar-home-tab"
					data-toggle="tab"><i class="fa fa-home"></i></a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane active" id="control-sidebar-home-tab"
					align="center">
					<h3 class="control-sidebar-heading">Portail de données ONN</h3>
					<ul class="control-sidebar-menu">
						<li><img alt="ONN" src="images/logo_onn.jpg"
							style="width: 75px; height: 100px;"></li>
					</ul>
					<!-- /.control-sidebar-menu -->
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>

	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 2.2.3 -->
	<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="dist/js/app.min.js"></script>
	<!-- SlimScroll -->
	<script src="../../plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="../../plugins/fastclick/fastclick.js"></script>
	<!-- ONN model script -->
	<script src="script/model.js"></script>
	<script src="script/administration.js"></script>
	<!-- page script -->




	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>
