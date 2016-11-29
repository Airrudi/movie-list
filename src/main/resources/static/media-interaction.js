function getUrlStart(){
	url = "/media/";	
	return url;
}


function removeFromJudged(uuid){
	$("#item-" + uuid).hide();
	url = getUrlStart() + uuid + "/delete";
	
	postData(url);
}

function toggleTodo(uuid){
	var clickedItem = $("#item-" + uuid + " .toggleTodo");				
	url = getUrlStart() + uuid + "/toggleTodo";	
	var data = {};
	var todo = clickedItem.hasClass("active");				
	
	// Check if already active
	if(todo){
		clickedItem.removeClass("active");	
		data.todo = false;					
	} else {
		clickedItem.addClass("active");	
		data.todo = true;								
	}				
	
	postData(url, data);
}

function toggleOwned(uuid){
	var clickedItem = $("#item-" + uuid + " .toggleOwned");				
	url = getUrlStart() + uuid + "/toggleOwned";	
	var data = {};
	var owned = clickedItem.hasClass("active");				
	
	// Check if already active
	if(owned){
		clickedItem.removeClass("active");	
		data.owned = false;					
	} else {
		clickedItem.addClass("active");	
		data.owned = true;								
	}				
	
	postData(url, data);
}

function toggleSeen(uuid){
	var clickedItem = $("#item-" + uuid);				
	url = getUrlStart() + uuid + "/toggleSeen";	
	var data = {};
	var seenCounter = $("#seenCounter");
	var notSeenCounter = $("#notSeenCounter");
	
	// Check if seen or not seen
	if( clickedItem.parents('#seen').length ){
		clickedItem.prependTo("#notSeen");					
		data.seen = false;
		seenCounter.html(parseInt(seenCounter.html()) - 1);
		notSeenCounter.html(parseInt(notSeenCounter.html()) + 1);
	} else {
		clickedItem.prependTo("#seen");
		data.seen = true;
		seenCounter.html(parseInt(seenCounter.html()) + 1);
		notSeenCounter.html(parseInt(notSeenCounter.html()) - 1);
		
	}				
	
	postData(url, data);				
}

function addToTodo(uuid){
	alert("add to todo");
}



function postData(url, data = {}){
	data._csrf = $("input[name='_csrf']").val();
	
	$.post( url, data).done(function( responseData ) {
		console.log( "Data Loaded: " + responseData );
	});
}