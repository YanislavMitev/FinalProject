package com.kinoarena.dto;

import java.time.LocalDateTime;

public class ScreeningDTO {
	private int id;
	private LocalDateTime startTime;
	private int movieId;
	private int hallId;

	public ScreeningDTO(int id, LocalDateTime startTime, int movieId, int hallId) {
		this.setId(id);
		this.setStartTime(startTime);
		this.setMovieId(movieId);
		this.setHallId(hallId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

}
