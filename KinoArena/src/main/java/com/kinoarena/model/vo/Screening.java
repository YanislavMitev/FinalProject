package com.kinoarena.model.vo;

import java.time.LocalDateTime;

public class Screening {
	private int id;
	private LocalDateTime startTime;
	private Movie movie;
	private Hall hall;


	public Screening(int id, LocalDateTime startTime, Movie movie, Hall hall) {
		this.setId(id);
		this.setStartTime(startTime);
		this.setMovie(movie);
		this.setHall(hall);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

}
