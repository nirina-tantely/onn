
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>Carte de localisation des sites</h1>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-4">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">
					<b id="annee-box">${anneeCourante}</b>
						<a href="" onclick="location.reload();">:National</a>
						<i><b id="info-box" style="word-wrap: break-word;"></b></i>
					</h3>
					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
					</div>
				</div>
				<!-- /.box-header -->
				<div id="map-container" class="box-body" style="height: 700">
					<!-- Mettre ici la carte -->
				</div>
				<div class="" id="map-loading">
					<i class="fa fa-refresh fa-spin"></i>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->

		<div class="col-md-8">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Synthèse SMS</h3>
					<div style="color: green;font: 'italic bold';">National<span id="titre-ongbase-box" style="word-wrap: break-word;"></span></div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- Mette le tableau de synthèse -->
					<table id="example1" class="table table-bordered table-striped">
						<thead style="font-size: 14px;">
							<tr>
								<th>Indicateurs/Mois</th>
								<th>J</th>
								<th>F</th>
								<th>M</th>
								<th>A</th>
								<th>M</th>
								<th>J</th>
								<th>J</th>
								<th>A</th>
								<th>S</th>
								<th>O</th>
								<th>N</th>
								<th>D</th>
							</tr>
						</thead>
						<tbody id="sms-tab-body" style="font-size: 13px;">
						</tbody>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>



		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<script
	src="http://maps.google.com/maps/api/js?sensor=false&key=AIzaSyCQWgp5OIr_OP2BwoegjmnkhKbG4lKSF70"></script>
<script>
	var var_map;

	function init_map() {
		var var_location = new google.maps.LatLng(-18.837604, 47.559076);

		var var_mapoptions = {
			center : var_location,
			zoom : 6
		//15
		};

		/*
		var var_marker = new google.maps.Marker({
			position: var_location,
			map: var_map,
			title:"Sabotsy Namehana"});
			var_marker.setMap(var_map);
		 */

		var_map = new google.maps.Map(document.getElementById("map-container"),
				var_mapoptions);

		//Load GeoJSON
		var_map.data.loadGeoJson("geojson/regions.json");
		//var_map.data.loadGeoJson("/localite_s.json");

		/*
		var_map.data.addListener('mouseover', function(event) {
		var_map.data.revertStyle();
		  $('#info-box').text(event.feature.getProperty('f1'));
		  var_map.data.overrideStyle(event.feature, { fillColor: 'red' });
		});

		var_map.data.addListener('mouseout', function(event){
			var_map.data.revertStyle();
		});*/
		var_map.data.addListener('click', function(event) {
			var_map.data.revertStyle();
			setInfoboxHtml(event.feature.getProperty('f1'));
			var_map.data.overrideStyle(event.feature, {
				fillColor : 'red'
			});
			onSMSMapSelect(event.feature.getProperty('f2'), event.feature
					.getProperty('f4'));
		});

		var_map.data.addListener('dblclick', function(event) {
			var typeLoc = event.feature.getProperty('f4');
			if (event.feature.getProperty('f3') == "region") {
				document.getElementById("selectRegion").value = event.feature
						.getProperty('f2');
				onSelectRegion();
				onMapSelect(event.feature.getProperty('f2'), 'region');
				onIntervenantSelect();
			} else if (typeLoc == "commune") {
				document.getElementById("selectCommune").value = event.feature
						.getProperty('f2');
				onSelectCommune();
				onMapSelect(event.feature.getProperty('f2'), 'commune');
				onIntervenantSelect();
			}
		});
		

		var_map.data.setStyle(function(feature) {
			return {
				fillColor : 'green',
				strokeWeight : 1.5
			}
		});

	}

	google.maps.event.addDomListener(window, 'load', init_map);
</script>
<script type="text/javascript">
	function onloadPage() {
		onSMSMapSelect("0", "nationale");
	}
	window.onload = onloadPage;
</script>
<!-- DataTables -->
<!-- 
<script src="../../plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../../plugins/datatables/dataTables.bootstrap.min.js"></script>
 -->
<!-- 
<script>
	$(function() {
		$("#example1").DataTable();
	});
</script>
 -->
