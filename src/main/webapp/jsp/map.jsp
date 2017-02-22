
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>Carte de localisation des sites</h1>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-5">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Analamanga > Sabotsy-Namehana > <b id="info-box"></b></h3>
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
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->

		<div class="col-md-3">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Synth�se Intervenants</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- Mette le tableau de synth�se -->
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Indicateurs</th>
								<th>Valeurs</th>
								<th>Taux</th>
							</tr>
						</thead>
						<tbody id="intervenant-tab-body">
							<tr>
								<td>A1IT1A1</td>
								<td>123</td>
								<td>50%</td>
							</tr>
							<tr>
								<td>A1IT2A1</td>
								<td>489</td>
								<td>75%</td>
							</tr>
							<tr>
								<td>A1IT2A2</td>
								<td>700</td>
								<td>98%</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>


		<div class="col-md-4">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Synth�se ONG Base</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- Mette le tableau de synth�se -->
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Indicateurs</th>
								<th>Valeurs</th>
								<th>Taux</th>
							</tr>
						</thead>
						<tbody id="ongbase-tab-body">
							<tr>
								<td>Nb add b�n�ficiaires [0;5 mois[</td>
								<td>123</td>
								<td>3%</td>
							</tr>
							<tr>
								<td>Nb add b�n�ficiaires [6;23 mois[</td>
								<td>340</td>
								<td>5%</td>
							</tr>
							<tr>
								<td>Nb add b�n�ficiaires [24;59 mois[</td>
								<td>340</td>
								<td>5%</td>
							</tr>
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
		var var_location = new google.maps.LatLng(-18.837604,47.559076);
 
        var var_mapoptions = {
          center: var_location,
          zoom: 6
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
		var_map.data.loadGeoJson("geojson/region.json");
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
				  $('#info-box').text(event.feature.getProperty('f1'));
				  var_map.data.overrideStyle(event.feature, { fillColor: 'red' });
				  onMapSelect(event.feature.getProperty('f2'), event.feature.getProperty('f4'));
			});
			
			var_map.data.setStyle(function(feature) {
			  return { fillColor: 'green',
			          strokeWeight: 1.5}
			});
 
      }
 
      google.maps.event.addDomListener(window, 'load', init_map);
</script>
<!-- DataTables -->
<script src="../../plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../../plugins/datatables/dataTables.bootstrap.min.js"></script>
<script>
  $(function () {
    $("#example1").DataTable();
  });
</script>
