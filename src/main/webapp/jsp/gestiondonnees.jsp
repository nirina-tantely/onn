<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>Gestion des données</h1>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-5">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Importer des données d'intervention</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" action="importIntervention.do" method="post" enctype="multipart/form-data">
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputFile">Choisir un fichier</label> <input
								type="file" id="interventionFile" name="interventionFile" accept=".csv">

							<p class="help-block">Choisir un le fichier CSV contenant les
								données d'intervention.</p>
						</div>
						<!-- 
						<div class="checkbox">
							<label> <input type="checkbox"> Check me out
							</label>
						</div>
						 -->
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Importer</button>
					</div>
				</form>
			</div>

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Importer des données ONG BASE</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" action="importONGBase.do" method="post" enctype="multipart/form-data">
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputFile">Choisir un fichier</label> <input
								type="file" name="ongbaseFile" accept=".csv">

							<p class="help-block">Choisir un le fichier CSV contenant les
								données ONG Base.</p>
						</div>
						<!-- 
						<div class="checkbox">
							<label> <input type="checkbox"> Check me out
							</label>
						</div>
						 -->
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Importer</button>
					</div>
				</form>
			</div>
			
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Importer des données SMS</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<form role="form" action="importSMS.do" method="post" enctype="multipart/form-data">
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputFile">Choisir un fichier</label> <input
								type="file" name="smsFile" accept=".csv">

							<p class="help-block">Choisir un le fichier CSV contenant les
								données SMS.</p>
						</div>
						<!-- 
						<div class="checkbox">
							<label> <input type="checkbox"> Check me out
							</label>
						</div>
						 -->
					</div>
					<!-- /.box-body -->

					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Importer</button>
					</div>
				</form>
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->

		<div class="col-md-7">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">Journal des resultats</h3>
				</div>
				<div class="box-body">
				<textarea rows="15" cols="100" readonly="readonly"><c:if test="${not empty importResult}">${importResult}</c:if></textarea>>
					<!-- Mettre ici les logs -->
				</div>
			</div>
		</div>
</section>
<!-- /.content -->
