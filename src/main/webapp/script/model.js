function onSelectRegion(){
	var codeRegion = document.getElementById("selectRegion").value;	
	if(codeRegion==''){
		location.reload();
		return;
	}
	var nomRegion = document.getElementById(codeRegion).text;	
	//alert('BOUBOU  '+nomRegion);
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) { 
			var selectCommuneContent;
			var responseHttp = xhttp.responseText;
			//console.log('===>'+responseHttp+'<=====');
			if(responseHttp == ''){
				selectCommuneContent = '';
			}else{
				var jsonObj = JSON.parse(responseHttp);
				var listeCommuneJson = JSON.parse(jsonObj.liste);
				var geoJson = JSON.parse(jsonObj.goeJson);
				var chemin = jsonObj.chemin;
				setInfoboxHtml(chemin);
				//console.log('===>'+geoJson);
				//console.log(listeCommuneJson);
				selectCommuneContent = '<a><i class="fa  fa-angle-double-right"></i> <span>Choir une commune</span></a>';
				selectCommuneContent += '<select class="form-control" id="selectCommune" onchange="onSelectCommune();onMapSelect(this.value, \'commune\');">';
				selectCommuneContent += '<option value="">Choisir une commune...</option>';
				for(var key in listeCommuneJson.communes){
					selectCommuneContent += '<option id="'+listeCommuneJson.communes[key].codeCommune+'" value="'+listeCommuneJson.communes[key].codeCommune+'">'+listeCommuneJson.communes[key].nomCommune+'</option>';
				}

				document.getElementById("divSelectCommune").innerHTML = selectCommuneContent;	
				var fktDiv = document.getElementById("divSelectFokontany");
				if(fktDiv != null) fktDiv.innerHTML = '';

				//TODO load geodata on the map
				var divMapContainer = document.getElementById("map-container");
				if(divMapContainer != null){

					//Clean the map
					var_map.data.forEach(function (feature) {
						//if (layer instanceof L.GeoJSON){
						var_map.data.remove(feature);
					});

					//var_map : variable globale dans la page map.jsp
					var_map.data.addGeoJson(geoJson);
					var bounds = new google.maps.LatLngBounds();
					for (var i = 0; i < geoJson.features.length; i++) {
						a = geoJson.features[i].geometry.coordinates[0][0][0][0];
						b = geoJson.features[i].geometry.coordinates[0][0][0][1];
						//console.log('a=>'+a+'b'+b);
						point = new google.maps.LatLng(b, a);
						bounds.extend(point);
					}
					var_map.fitBounds(bounds);
				}
			}
		}
	};
	xhttp.open("GET", "selectRegion.do?codeRegion="+codeRegion+"&nomRegion="+nomRegion, true);
	xhttp.responseType = "text";
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send();
}


function onSelectCommune(){
	var codeCommune = document.getElementById("selectCommune").value;	
	var nomCommune = document.getElementById(codeCommune).text;	
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) { 
			var selectFktContent;
			var responseHttp = xhttp.responseText;
			if(responseHttp == ''){
				selectFktContent = '';
			}else{
				var jsonObj = JSON.parse(responseHttp);
				var listeFktJson = JSON.parse(jsonObj.liste);
				var geoJson = JSON.parse(jsonObj.goeJson);
				var chemin = jsonObj.chemin;
				setInfoboxHtml(chemin);
				//console.log(listeFktJson);
				selectFktContent = '<a><i class="fa  fa-angle-double-right"></i> <span>Choir un fokontany</span></a>';
				selectFktContent += '<select class="form-control" id="selectFokontany" onChange="onSelectFkt();onMapSelect(this.value, \'fokontany\');">';
				selectFktContent += '<option value="">Choisir une fokontany...</option>';
				for(var key in listeFktJson.fokontany){
					selectFktContent += '<option id="'+listeFktJson.fokontany[key].codeFkt+'" value="'+listeFktJson.fokontany[key].codeFkt+'">'+listeFktJson.fokontany[key].nomFkt+'</option>';
				}

				//TODO load geodata on the map
				var divMapContainer = document.getElementById("map-container");
				if(divMapContainer != null){

					//Clean the map
					var_map.data.forEach(function (feature) {
						//if (layer instanceof L.GeoJSON){
						var_map.data.remove(feature);
					});

					var listeGeoFkt = JSON.parse(geoJson.listeFkt);
					var limCommune = JSON.parse(geoJson.limCommune);

					//var_map : variable globale dans la page map.jsp
					var_map.data.addGeoJson(limCommune);
					var_map.data.addGeoJson(listeGeoFkt);
					var bounds = new google.maps.LatLngBounds();
					for (var i = 0; i < limCommune.features.length; i++) {
						for (var j = 0; j < limCommune.features[i].geometry.coordinates[0][0].length; j++) {
							a = limCommune.features[i].geometry.coordinates[0][0][j][0];
							b = limCommune.features[i].geometry.coordinates[0][0][j][1];
							//console.log('a=>'+a+'b'+b);
							point = new google.maps.LatLng(b, a);
							bounds.extend(point);
						}
					}
					var_map.fitBounds(bounds);
				}
			}
			document.getElementById("divSelectFokontany").innerHTML = selectFktContent;	
		}
	};
	xhttp.open("GET", "selectCommune.do?codeCommune="+codeCommune+"&nomCommune="+nomCommune, true);
	xhttp.responseType = "text";
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send();
}



function onSelectFkt(){
	var codeFkt = document.getElementById("selectFokontany").value;	
	var nomFkt = document.getElementById(codeFkt).value;	
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) { 

		}
	};
	xhttp.open("GET", "selectFkt.do?codeFkt="+codeFkt+"&nomFkt="+nomFkt, true);
	xhttp.responseType = "text";
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send();
}

function onCheckAfficherTout(checkbox){

	if(checkbox.checked){
		var xhttp;
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) { 
				var selectFktContent;
				var responseHttp = xhttp.responseText;
				if(responseHttp == ''){
					selectFktContent = '';
				}else{
					var jsonObj = JSON.parse(responseHttp);
					var geoJson = JSON.parse(jsonObj.goeJson);
					//console.log(geoJson);

					//TODO load geodata on the map
					var divMapContainer = document.getElementById("map-container");
					if(divMapContainer != null){

						//var_map : variable globale dans la page map.jsp
						var_map.data.addGeoJson(geoJson);
					}
				}
			}
		};
		xhttp.open("GET", "selectAllFktn.do", true);
		xhttp.responseType = "text";
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send();
	}else{
		//Clean the map
		var_map.data.forEach(function (feature) {
			//if (layer instanceof L.GeoJSON){
			var_map.data.remove(feature);
		});
	}
}

function onIntervenantSelect(){
	var codeIntervenant = document.getElementById("selectIntervenant").value;
		var xhttp;
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) { 
				var selectFktContent;
				var responseHttp = xhttp.responseText;
				if(responseHttp != ''){
					var jsonObj = JSON.parse(responseHttp);
					var codes = jsonObj.codes;
					console.log(codes);
					var divMapContainer = document.getElementById("map-container");
					if(divMapContainer != null){
							/*
							var_map.data.forEach(function (feature) {
								console.log('====>'+feature.getProperty('f2'));			
								var code = feature.getProperty('f2');
								if ([21, 31, 41, 51].includes(code)){
									feature.setProperty('isIn',true);
								}else{
									feature.setProperty('isIn',false);
								};
								console.log('IsIn ====>'+feature.getProperty('isIn'));		
							});
						 */
						var_map.data.revertStyle();
						var_map.data.setStyle(function(feature) {
							var code = feature.getProperty('f2');
							var color = 'green';
							if (codes.includes(code)) {//FIXME Ajouter des conditions pour les points et retourner d'autres styles
								color = 'red';
							}
							return /** @type {google.maps.Data.StyleOptions} */({
								fillColor : color,
								strokeWeight : 1.5
							});
						});
					}
				}
			}
		};
		xhttp.open("GET", "surligner.do?codeIntervenant="+codeIntervenant, true);
		xhttp.responseType = "text";
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send();
}

function onMapSelect(code, typeLocalisation){
	if(typeLocalisation == null) typeLocalisation='region';
	var codeIntervenant = document.getElementById("selectIntervenant").value;
	if(code!=''){
		var xhttp;
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) { 
				var selectFktContent;
				var responseHttp = xhttp.responseText;
				if(responseHttp == ''){
					selectFktContent = '';
				}else{
					//console.log(responseHttp);
					var jsonObj = JSON.parse(responseHttp);
					//TODO load geodata on the map
					var intervenantTbody = document.getElementById("intervenant-tab-body");
					if(intervenantTbody != null){
						var intervenantBody = "";
						var activites = jsonObj.activites;
						for(var i in activites){
							//console.log(activites[i]);
							intervenantBody += "<tr>";
							intervenantBody += "<td>"+activites[i].indicateur+"</td>";
							intervenantBody += "<td>"+activites[i].valeur+"</td>";
						}
						intervenantTbody.innerHTML = intervenantBody;	
					}

					var ongTbody = document.getElementById("ongbase-tab-body");
					if(ongTbody != null){
						var ongbaseBody = "";
						var ongbase = jsonObj.ongbase;
						for(var i in ongbase){
							//console.log(ongbase[i]);
							ongbaseBody += "<tr>";
							ongbaseBody += "<td>"+ongbase[i].indicateur+"</td>";
							ongbaseBody += "<td>"+ongbase[i].T1+"</td>";
							ongbaseBody += "<td>"+ongbase[i].T2+"</td>";
							ongbaseBody += "<td>"+ongbase[i].T3+"</td>";
							ongbaseBody += "<td>"+ongbase[i].T4+"</td>";
						}
						ongTbody.innerHTML = ongbaseBody;	
					}

				}
			}
		};
		xhttp.open("GET", "updateSynthese.do?code="+code+"&typeLocalisation="+typeLocalisation+"&codeIntervenant="+codeIntervenant, true);
		xhttp.responseType = "text";
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send();
	}
}

function setInfoboxHtml(chemin){
	var infoBox = document.getElementById("info-box");
	var cheminBox = document.getElementById("chemin-box");
	cheminBox.innerHTML = chemin;
	infoBox.innerHTML = "";
}