function favoritesControler(movieID) {
	var link = "";
	if(document.getElementById("favoriteButton").firstElementChild.className == "notActive"){
		link = "/KinoArena/removeFavorite?movieID="+movieID;
	}else{
		link = "/KinoArena/addToFavorites?movieID="+movieID;
	}
	sendFavoriteRequest(link);
}
	
function sendFavoriteRequest(link){
    var xhr = new XMLHttpRequest();
	xhr.open("POST", link, true);
	xhr.send(null);
	
	xhr.addEventListener('load', () => {
		var response = xhr.responseText;
		if(xhr.status == 302){
			location.replace(response);
		}
		if(xhr.status == 200){
		location.reload();
		}
	});
}
