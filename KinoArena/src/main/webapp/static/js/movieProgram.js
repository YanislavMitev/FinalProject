function getAllCinemaProgram(id,date){
var link = "/KinoArena/screenings?movieId="+id + "&date=" + date;
    console.log(link);
	var xhr = new XMLHttpRequest();
	xhr.open("GET", link, true);
	xhr.send(null);
	var response = "";
	xhr.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
			 var screenings = JSON.parse(this.responseText);
			 
				var html = "" ;
				var firsttime = true;
				for (var cinema in screenings){
					cinemaCheker = "";
				for (var hall in screenings[cinema]){
					var objCinema = screenings[cinema][hall][0].hall.cinema;
					if(cinemaCheker != objCinema.name){
						html += `<div class="scheduleRow">
							<aside class="sideInfo">
								<article class="cinemaContacts">
									<h6 class="title">`+objCinema.name+`</h6>
									<p>`+objCinema.address.city+","+objCinema.address.address+`</p>
									<p>`+objCinema.gsm+`</p>
								</article>
							</aside>
							
									<div class="contentWrapper">
					<div class="timeTable">`
					cinemaCheker = objCinema.name;
					}
							html += `<div class="row">
						<div class="attr secondary">
							<div class="item"><h3>`+screenings[cinema][hall][0].hall.hallType+` `+screenings[cinema][hall][0].movie.movieType+`			
							</div>
						</div>
						
						<div class="timelineSet">
							<div class="scrollWrapper owl-carousel owl-theme owl-loaded first-slide hide-nav">
							<div class="owl-stage-outer">
								<div class="owl-stage"
									style="transform: translate3d(0px, 0px, 0px); transition: 0s; width: 340px;">`
						

						for (var screeningIndex in screenings[cinema][hall]){
							var screeningObj= screenings[cinema][hall][screeningIndex];
							html += ` <div class="owl-item active"
											style="width: auto; margin-right: 0px;">
											<a href="/KinoArena/reserve/`+screeningObj.id+`" class="item booking tooltip_1"
												data-tooltip="11 лв. / 13 лв." data-tooltip-class="wide"> <span
												class="time">`+screeningObj.startTime.time.hour+":"+screeningObj.startTime.time.minute+`</span>
											</a>
										</div>`						
						}
						html += `</div>
							</div>

						</div>
					</div>
					</div>
					`
				}
				html += `</div>
				
				</div>
					</div>`
				}
				
				document.getElementById("block").innerHTML = html;
}}
}