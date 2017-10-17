var xmlHttp1 = createXmlHttpRequestObject();

function createXmlHttpRequestObject(){
	var xmlHttp1;

	if(window.ActiveXObject){
		try{
			xmlHttp1 = new ActiveObject("Microsoft.XMLHTTP");
		}
		catch(e){
			xmlHttp1 = false;
		}
	}

	else{
		try{
			xmlHttp1 = new XMLHttpRequest();
		}
		catch(e){
			xmlHttp1 = false;
		}
	}

	if(!xmlHttp1)
		alert('cant create that Object');

	else
		return xmlHttp1; 
}

function deleteComment(deleteID){
	var commentIDArray = deleteID.split("delete");
	commentID = commentIDArray[0];
	if (xmlHttp1.readyState == 0 || xmlHttp1.readyState== 4){
		xmlHttp1.open("GET","include/deleteComment.php?commentID="+commentID,true);
		xmlHttp1.onreadystatechange = handleServerResponse1;
		xmlHttp1.send(null);
	}
	else{
		setTimeout("deleteComment()",2000);
	}
}

function handleServerResponse1(){
	if(xmlHttp1.readyState==4){
		if(xmlHttp1.status==200){
			ccId = commentID + "CC";
			commentContainer = document.getElementById(ccId);
			commentContainer.style.display = "none";
			
		}
	}
}
