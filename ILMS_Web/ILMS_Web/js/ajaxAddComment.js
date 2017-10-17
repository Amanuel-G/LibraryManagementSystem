var xmlHttp = createXmlHttpRequestObject();

function createXmlHttpRequestObject(){
	var xmlHttp;
	
	if(window.ActiveXObject){
		try{

			xmlHttp = new ActiveObject("Microsoft.XMLHTTP");
		}
		catch(e){	
			xmlHttp = false;
		}
	}

	else{
		
		try{
			xmlHttp = new XMLHttpRequest();
		}
		catch(e){
			xmlHttp = false;
		}
	}

	if(!xmlHttp)
		alert('cant create that Object');

	else
		return xmlHttp; 
}

function assign(tar, events, fun){  

    if (typeof addEventListener !== "undefined") {
		tar.addEventListener(events, function(){ fun(this, events); });
		
	} else if (typeof attachEvent !== "undefined") {
		tar.attachEvent("on"+events, function() {fun(this, events) });
	} else if (typeof "onclick" !== "undefined"){
		tar["on" +events] = fun;
		
	} else {
		alert("Javascript may have been disabled!");
	}
}	

function processComment(tar, type){
	
	if (xmlHttp.readyState == 0 || xmlHttp.readyState== 4){	
		comment = encodeURIComponent(document.getElementById('myComment').value);
		
		bookID = encodeURIComponent(document.getElementById('hiddenBookID').innerHTML);
		
		patronID = encodeURIComponent(document.getElementById('hiddenPatronID').innerHTML);
		
		xmlHttp.open("GET","include/addComment.php?comment="+comment+"&bookID="+bookID+"&patronID="+patronID,false);
	
		xmlHttp.onreadystatechange = handleServerResponse;
		xmlHttp.send(null);
		 
	}
	else{
		setTimeout("processComment()",2000);
	}
}

function handleServerResponse(){
		
	if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				
			xmlText = xmlHttp.responseText;
			
			var parser = new DOMParser();
			var xmlDoc = parser.parseFromString(xmlText,"text/html");
			
			var nameTag = xmlDoc.getElementsByTagName("b")[0];
	
	
			var name = nameTag.innerHTML;
			
			
						

			var commentArray = xmlDoc.getElementsByTagName("p");
			
			
			var commentTag = commentArray[0];
			
			var comment = commentTag.innerHTML;
				
			var parent = document.getElementById("reviewResult");
			var parentDiv = document.createElement("div");
			var boldName = document.createElement("b");
			boldName.innerHTML = ""+name+"";
			parentDiv.setAttribute("class","comments");
			parentDiv.innerHTML = "<p>"+comment+"</p>";
			parent.appendChild(boldName);
			//parent.appendChild(parentDiv);
			var nameOF = document.createElement("div");
			var cc = document.createElement("div");
			nameOF.appendChild(parentDiv);
			cc.appendChild(nameOF);
			parent.appendChild(cc);

			document.getElementById('myComment').value = "";
			}	
	}
}

assign(commentButton,'click',processComment);