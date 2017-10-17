
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

function changePlaceHolder(tar, type){
	if (type == 'change'){
	var values = document.getElementById('select');
	var change = document.getElementById('searchInput');
	var selected = values.options[values.selectedIndex].value;
	if (selected == 'Author'){
		change.setAttribute('placeholder','Search book by Author');
	}
	else if(selected == 'Title'){
		change.setAttribute('placeholder','Search book by Title');
	}
	else if(selected == 'iSBN'){
		change.setAttribute('placeholder','Search book by ISBN');
	}
	;
}
}

assign(select,'change',changePlaceHolder);