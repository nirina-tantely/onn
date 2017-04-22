<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content">
	<div class="register-box">
		<div class="register-logo">
			<b>GESTION D'ACCES</b>
		</div>

		<div class="register-box-body">
			<p>
			<div class="form-group has-feedback">
				<select class="form-control" id="selectUser" onchange="onselectUser();">
					<option value="VIDE">Choisir un utilisateur</option>
					<c:forEach var="user" items="${users}">
						<option id="${user.pseudo}" value="${user.pseudo}">${user.nom}</option>
					</c:forEach>
				</select> <span class="glyphicon glyphicon-th-list form-control-feedback"></span>
			</div>


			<p class="login-box-msg" id="msgbox">Ajout d'un nouvel utilisateur</p>
			<form action="addUser.do" method="post" id="userForm">
				<input type="hidden" name="id" id="id" />
				<div class="form-group has-feedback">
					<input type="text" name="nom" id="nom" class="form-control"
						placeholder="Nom"> <span
						class="glyphicon glyphicon-pencil form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="pseudo" id="pseudo"
						placeholder="Pseudo"> <span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<select class="form-control" name="role" id="role">
						<option value="VIDE">Choisir un role</option>
						<c:forEach var="role" items="${roles}">
							<option id="${role.id}" value="${role.id}">${role.nom}</option>
						</c:forEach>
					</select> <span class="glyphicon glyphicon-briefcase form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="password" id="password"
						class="form-control" placeholder="Mot de passe"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="rePassword" id="rePassword"
						class="form-control" placeholder="Retaper le mot de passe">
					<span class="glyphicon glyphicon-log-in form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-6">
						<button type="button" class="btn btn-primary btn-block btn-flat" onclick="onDeleteUser();">Supprimer</button>
					</div>
					<!-- /.col -->
					<div class="col-xs-6">
						<button type="button" onclick="saveOrupdateUser();"
							class="btn btn-primary btn-block btn-flat">Enregistrer</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

			<div class="social-auth-links text-center">
				<button type="button" onclick="nouvelUtilisateur();" class="btn btn-primary btn-block btn-flat">Nouvel
					utilisateur</button>
			</div>
		</div>
		<!-- /.form-box -->
	</div>
</section>