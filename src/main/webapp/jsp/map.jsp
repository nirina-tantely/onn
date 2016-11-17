
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Localisation des sites
      </h1>     
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-9">
          <div class="box box-primary">
            <!-- /.box-header -->
            <div id="map-container" class="box-body">
					<!-- Mettre ici la carte -->
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-3">
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Tableau de synthèse</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<!-- Mette le tableau de synthèse -->
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


<script src="http://maps.google.com/maps/api/js?sensor=false&key=AIzaSyCQWgp5OIr_OP2BwoegjmnkhKbG4lKSF70"></script>
<script>	
 
      function init_map() {
		var var_location = new google.maps.LatLng(-18.837604,47.559076);
 
        var var_mapoptions = {
          center: var_location,
          zoom: 6
        };
 
		var var_marker = new google.maps.Marker({
			position: var_location,
			map: var_map,
			title:"Sabotsy Namehana"});
 
        var var_map = new google.maps.Map(document.getElementById("map-container"),
            var_mapoptions);
 
		var_marker.setMap(var_map);	
 
      }
 
      google.maps.event.addDomListener(window, 'load', init_map);
</script>
 