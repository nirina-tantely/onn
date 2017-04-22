/**
 * 
 */

function connecter(){
	var valide = true;
	var messError = "";
	if (	document.getElementById("pseudo").value=="" ||
			document.getElementById("password").value=="" ){
		valide = false;
		messError = "Un ou plusieurs des champs sont vides!";
	} ;
	
	if(valide){
		var pseudo = document.getElementById("pseudo").value;
		var password = document.getElementById("password").value;
		var xhttp;
		xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) { 
				var selectFktContent;
				var responseHttp = xhttp.responseText;
				var jsonObj = JSON.parse(responseHttp);
				var result = jsonObj.Result;
				var message = jsonObj.Message;
				document.getElementById("messageBox").innerHTML = message;
				if(result=="SUCCES"){
					//TODO redirection
					document.location.href="map.do";
				}else{
					//TODO en cas d'echec de connexion
				}
			}
		};
		xhttp.open("POST", "authentifier.do?pseudo="+pseudo+"&password="+password, true);
		xhttp.responseType = "text";
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send();
	}else{
		alert(messError);
	}	
}