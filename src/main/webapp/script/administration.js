/**
 * Fonctions javascript d'administration
 */

function saveOrupdateUser(){
	var valide = true;
	var messError = "";
	if (document.getElementById("nom").value=="" || 
			document.getElementById("pseudo").value=="" ||
			document.getElementById("role").value=="VIDE" ||
			document.getElementById("password").value=="" ||
			document.getElementById("rePassword").value=="" ){
		valide = false;
		messError = "Un ou plusieurs des champs sont vides!";
	} ;

	if(valide){
		if ( document.getElementById("rePassword").value != document.getElementById("password").value ){
			valide = false;
			messError = "Les mots de passes sont différents!";
		} ;
	}

	if(valide && document.getElementById("id").value>0){
		document.getElementById("userForm").submit();
		return;
	}

	if(valide){
		var pseudo = document.getElementById("pseudo").value;
		var xhttp;
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) { 
				var selectFktContent;
				var responseHttp = xhttp.responseText;
				var jsonObj = JSON.parse(responseHttp);
				var error = jsonObj.Error;
				if(error=="OUI"){
					var message = jsonObj.Message;
					alert(message);
				}else{
					document.getElementById("userForm").submit();
				}
			}
		};
		xhttp.open("GET", "checkUser.do?pseudo="+pseudo, true);
		xhttp.responseType = "text";
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send();
	}else{
		alert(messError);
	}	
}

function onDeleteUser(){
	var idUser = document.getElementById("id").value;
	if(idUser==""){
		alert('Selectionez un utilisateur!');
		return;
	}

	var r = confirm("Voulez vous vraiment supprimer cet utilisateur?");
	if(r==false) return;

	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) { 
			document.location.href="gestion_acces.do";
		}
	};
	xhttp.open("GET", "deleteUser.do?id="+idUser, true);
	xhttp.responseType = "text";
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send();
}

function onselectUser(){
	var pseudo = document.getElementById("selectUser").value;
	if(pseudo=="VIDE"){
		document.getElementById("userForm").reset();
		return;
	}
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) { 
			var selectFktContent;
			var responseHttp = xhttp.responseText;
			var jsonObj = JSON.parse(responseHttp);
			console.log(jsonObj.utilisateur);
			var user = jsonObj.utilisateur;
			if(user){
				document.getElementById("nom").value = user.nom;
				document.getElementById("pseudo").value = user.pseudo;
				document.getElementById("password").value = user.password;
				document.getElementById("role").value = user.role;
				document.getElementById("id").value = user.id;
				document.getElementById("msgbox").innerHTML = "Edition de l'utilisateur "+user.nom;
			}else{
				document.getElementById("userForm").reset();
				alert('Utilisateur non identifié!');
			}
		}
	};
	xhttp.open("GET", "getUserDetail.do?pseudo="+pseudo, true);
	xhttp.responseType = "text";
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send();
}

function nouvelUtilisateur(){
	document.getElementById("userForm").reset();
	document.getElementById("id").value = "";
	document.getElementById("selectUser").value = "VIDE";
	document.getElementById("msgbox").innerHTML = "Ajout d'un nouvel utilisateur";
}


$('.map-print').on('click',

		/*
		 * Print dat maps!
		 */
		function printMaps() {

	var legende = getCritereLegende();
	console.log(legende.pays+" - "+legende.region+" - "+legende.commune+" - "+legende.fokontany+" - "+legende.annee);
	var legendeContent = makeLegende(legende);
	console.log(legendeContent);

	var body               = $('body');
	var legende               = $(''+legendeContent);
	var mapContainer       = $('.map-container');
	var mapContainerParent = mapContainer.parent();
	var printContainer     = $('<div>');
	var styltyle = $('<style>')
	.text('a[href$="google.com/maps"], .gmnoprint:not(.gm-bundled-control) {display: none !important}');

	printContainer
	.addClass('print-container')
	.css('position', 'relative')
	.height(mapContainer.height())
	.append(styltyle)
	.append(mapContainer)
	.append(legende)
	.prependTo(body);

	var content = body
	.children()
	.not('script')
	.not(printContainer)
	.detach();

	// Patch for some Bootstrap 3.3.x `@media print` styles. :|
	var patchedStyle = $('<style>')
	.attr('media', 'print')
	.text('img { max-width: none !important; }' +
	'a[href]:after { content: ""; }')
	.appendTo('head');



	window.print();

	body.prepend(content);
	mapContainerParent.prepend(mapContainer);

	printContainer.remove();
	patchedStyle.remove();
});


function getCritereLegende(){

	var legende = {};

	legende.pays = "Madagascar";

	var selectRegion = document.getElementById("selectRegion");
	if(selectRegion!=null){
		var codeRegion = selectRegion.value;	
		if(codeRegion != "VIDE"){
			var nomRegion = document.getElementById(codeRegion).text;
			legende.region = nomRegion;
		}
	}

	var selectCommune = document.getElementById("selectCommune");
	if(selectCommune!=null){
		var codeCommune = selectCommune.value;	
		if(codeCommune != "VIDE"){
			var nomCommune = document.getElementById(codeCommune).text;
			legende.commune = nomCommune;
		}
	}


	var selectFkt = document.getElementById("selectFokontany");
	if(selectFkt!=null){
		var codeFkt = selectFkt.value;	
		if(codeFkt != "VIDE"){
			var nomFkt = document.getElementById(codeFkt).text;
			legende.fokontany = nomFkt;
		}
	}

	if(document.getElementById("selectIntervenant")){
		var codeIntervenant = document.getElementById("selectIntervenant").value;
		if(codeIntervenant!="VIDE"){
			var nomInterv = document.getElementById(codeIntervenant).text;
			legende.intervenant = nomInterv;
		}
	}

	if(document.getElementById("selectAnnee")){
		var annee = document.getElementById("selectAnnee").value;
		if(annee!="VIDE"){
			legende.annee = annee;
		}else{
			var anneeBox = document.getElementById("annee-box").innerHTML;
			if(anneeBox!=null) legende.annee = anneeBox;
		}
	}

	return legende;
}

function makeLegende(legende){
	var interv;
	var autres;
	var contenu = '<div><h3>LEGENDE</h3><ul>';

	if(legende.pays){
		contenu+='<li>Pays: '+legende.pays;
		interv = "Regions d'intervention";
		autre = "Regions communes";
	}

	if(legende.region){
		contenu+='<li>Region: '+legende.region;
		interv = "Communes d'intervention";
		autre = "Autres communes";
	}

	if(legende.commune){
		contenu+='<li>Commune: '+legende.commune;
		interv = "Fokontany d'intervention";
		autre = "Fokontany communes";
	}

	if(legende.fokontany){
		contenu+='<li>Fokontany: '+legende.fokontany;
	}

	if(legende.annee){
		contenu+='<li>Année: '+legende.annee;
	}

	if(legende.intervenant){
		contenu+='<li>Intervenant: '+legende.intervenant;
		contenu+='<li>'+interv+'<div style="width:50px;height:8px;background-color:#AFA8F6;border:1px solid #000;"></div></li>';
		contenu+='<li>'+autre+'<div style="width:50px;height:8px;background-color:#A9CEA3;border:1px solid #000;"></div></li>';
	}

	contenu += '</ul></div>';

	return contenu;
}

function makeLegendeTableau(legende){
	var contenu = '';

	if(legende.annee){
		contenu+='Année: '+legende.annee;
	}

	if(legende.pays){
		contenu+=' - Pays: '+legende.pays;
	}

	if(legende.region){
		contenu+=' - Region: '+legende.region;
	}

	if(legende.commune){
		contenu+=' - Commune: '+legende.commune;
	}

	if(legende.fokontany){
		contenu+=' - Fokontany: '+legende.fokontany;
	}

	if(legende.intervenant){
		contenu+=' - Intervenant: '+legende.intervenant;
	}

	return contenu;
}


function getCritere(){

	var critere = {};

	critere.typeLocalisation = "nationale";
	critere.code = "0";

	var selectRegion = document.getElementById("selectRegion");
	if(selectRegion!=null){
		var codeRegion = selectRegion.value;	
		if(codeRegion != "VIDE"){
			critere.code = codeRegion;
			critere.typeLocalisation = "region";
		}
	}

	var selectCommune = document.getElementById("selectCommune");
	if(selectCommune!=null){
		var codeCommune = selectCommune.value;	
		if(codeCommune != "VIDE"){
			critere.code = codeCommune;
			critere.typeLocalisation = "commune";
		}
	}


	var selectFkt = document.getElementById("selectFokontany");
	if(selectFkt!=null){
		var codeFkt = selectFkt.value;	
		if(codeFkt != "VIDE"){
			critere.code = codeFkt;
			critere.typeLocalisation = "fokontany";
		}
	}

	if(document.getElementById("selectIntervenant")){
		var codeIntervenant = document.getElementById("selectIntervenant").value;
		critere.codeIntervenant = codeIntervenant;
	}

	if(document.getElementById("selectAnnee")){
		var annee = document.getElementById("selectAnnee").value;
		if(annee!="VIDE"){
			critere.annee = annee;
		}else{
			var anneeBox = document.getElementById("annee-box").innerHTML;
			if(anneeBox!=null) critere.annee = anneeBox;
		}
	}

	return critere;
}


function exportSynthese(){
	var critere = getCritere();
	var code = critere.code;
	var typeLocalisation = critere.typeLocalisation;
	var codeIntervenant = critere.codeIntervenant;
	var annee = critere.annee;

	var legende = getCritereLegende();
	console.log(legende.pays+" - "+legende.region+" - "+legende.commune+" - "+legende.fokontany+" - "+legende.annee);
	var legendeContent = makeLegendeTableau(legende);

	console.log('==> code:'+code+' type: '+typeLocalisation);
	if(code!=""){
		var filePath = "exportSynthese.do?code="+code+"&typeLocalisation="+typeLocalisation+"&codeIntervenant="+codeIntervenant+"&annee="+annee+"&legende="+legendeContent;

		var link=document.createElement('a');
		document.body.appendChild(link);
		link.href=filePath ;
		link.target="_blank";
		link.click();

	}else{
		//console.log('==> ny tay!');
	}
}


function exportSyntheseCSV(){
	var critere = getCritere();
	var code = critere.code;
	var typeLocalisation = critere.typeLocalisation;
	var codeIntervenant = critere.codeIntervenant;
	var annee = critere.annee;

	console.log('==> code:'+code+' type: '+typeLocalisation);
	if(code!=""){
		var filePath = "exportSyntheseCSV.do?code="+code+"&typeLocalisation="+typeLocalisation+"&codeIntervenant="+codeIntervenant+"&annee="+annee;

		var link=document.createElement('a');
		document.body.appendChild(link);
		link.href=filePath ;
		link.target="_blank";
		link.click();

	}else{
		//console.log('==> ny tay!');
	}
}


function exportONGBase(){

	var critere = getCritere();
	var code = critere.code;
	var typeLocalisation = critere.typeLocalisation;
	var annee = critere.annee;

	var legende = getCritereLegende();
	legende.intervenant = null;
	console.log(legende.pays+" - "+legende.region+" - "+legende.commune+" - "+legende.fokontany+" - "+legende.annee);
	var legendeContent = makeLegendeTableau(legende);

	var tousIndicateurs = false;
	var btnBody = document.getElementById("bouton-affiche-principale");
	if(btnBody != null){
		tousIndicateurs = true;
	}

	console.log('==> code:'+code+' type: '+typeLocalisation);
	if(code!=""){
		var filePath = "exportONGBase.do?code="+code+"&typeLocalisation="+typeLocalisation+"&annee="+annee+"&legende="+legendeContent+"&tousIndicateurs="+tousIndicateurs;

		var link=document.createElement('a');
		document.body.appendChild(link);
		link.href=filePath ;
		link.target="_blank";
		link.click();

	}else{
		//console.log('==> ny tay!');
	}
}


function exportONGBaseCSV(){

	var critere = getCritere();
	var code = critere.code;
	var typeLocalisation = critere.typeLocalisation;
	var annee = critere.annee;

	var tousIndicateurs = false;
	var btnBody = document.getElementById("bouton-affiche-principale");
	if(btnBody != null){
		tousIndicateurs = true;
	}

	console.log('==> code:'+code+' type: '+typeLocalisation);
	if(code!=""){
		var filePath = "exportONGBaseCSV.do?code="+code+"&typeLocalisation="+typeLocalisation+"&annee="+annee+"&tousIndicateurs="+tousIndicateurs;

		var link=document.createElement('a');
		document.body.appendChild(link);
		link.href=filePath ;
		link.target="_blank";
		link.click();

	}else{
		//console.log('==> ny tay!');
	}
}


function downloadGeoJson(){
	var critere = getCritere();
	var typeLocalisation = critere.typeLocalisation;
	var annee = critere.annee;
	var codeIntervenant = critere.codeIntervenant;
	
	if(codeIntervenant == null || codeIntervenant == "" || codeIntervenant == "VIDE"){
		alert('Choisir un intervenant!');
		return;
	}
	
	var filePath = "downloadGeoJson.do?codeIntervenant="+codeIntervenant+"&typeLocalisation="+typeLocalisation+"&annee="+annee;

	var link=document.createElement('a');
	document.body.appendChild(link);
	link.href=filePath ;
	link.target="_self";
	link.click();

	
}