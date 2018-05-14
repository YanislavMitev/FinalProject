function getActiveByGenre(sufix){
	var link = "./movies?genre=" + sufix;
	homeMovieBoxRequest(link)

}
function getActiveMovies(){
	var link = "./movies";
	var xhr = homeMovieBoxRequest(link);
}
function getActiveMoviesByHallType(sufix){
	var link = "./movies?hall="+sufix;
	var xhr = homeMovieBoxRequest(link)	;

	
}

function homeMovieBoxRequest(link) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", link, true);
	xhr.send(null);
	var response = "";
	xhr.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
			 movieBoxGenerator(this.responseText);
	    }
	}

}

function movieBoxGenerator(response){

	var movies = JSON.parse(response);
	var html = "";
	for (var index=0; index < movies.length; index++){
		html +=`
			<div class="filterItem movieBox filterSelected"
				data-filter="tab-1 tab-6  ">
				 <figure class="featured lazyLoad imgLoaded"
					data-img-width="224" data-img-height="336">
					<a href="./movies/` +movies[index].id+`">
					<img
						src="/KinoArena` + movies[index].coverURL + `"
						alt="`+ movies[index].title+ `" width="224" height="336"
						class="poster">
						</a>
				</figure>
				<h5 class="title">
					<a href="./movies/`+ movies[index].id+ `">` + movies[index].title+ 
					`</a>
				</h5>

				<div class="attr highlight">
					<div class="item tooltip_26">
						<p>forbidden for under:`+ movies[index].ageLimitation +`</p>
					</div>
				</div>
			</div>`
			}
	document.getElementById('filterContent').innerHTML = html;
}
