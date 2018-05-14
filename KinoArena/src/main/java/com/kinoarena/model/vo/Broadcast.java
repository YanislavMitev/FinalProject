package com.kinoarena.model.vo;

import java.time.LocalTime;

import com.kinoarena.exceptions.ModelException;

public class Broadcast {
	private static final String INVALID_CINEMA = "Invalid cinema.";
	private static final String INVALID_MOVIE = "Invalid movie.";
	private static final String INVALID_HALL = "Invalid hall.";
	private static final String INVALID_PROJECTION = "Invalid projection";
	private static final String INVALID_PRICE = "Invalid price.";
	
	private Cinema cinema;
	private Movie movie;
	private Hall hall;
	private LocalTime projection;
	private float price;

	public Broadcast(Cinema cinema, Movie movie, Hall hall, LocalTime projection, float price) throws ModelException {
		setCinema(cinema);
		setMovie(movie);
		setHall(hall);
		setProjection(projection);
		setPrice(price);
	}

	private void setPrice(float price) throws ModelException {
		if (price > 0.0f) {
			this.price = price;
		} else
			throw new ModelException(INVALID_PRICE);
	}

	private void setProjection(LocalTime projection) throws ModelException {
		if (this.projection != null) {
			this.projection = projection;
		} else
			throw new ModelException(INVALID_PROJECTION);
	}

	private void setHall(Hall hall) throws ModelException {
		if (this.hall != null) {
			this.hall = hall;
		} else
			throw new ModelException(INVALID_HALL);
	}

	private void setMovie(Movie movie) throws ModelException {
		if (movie != null) {
			this.movie = movie;
		} else
			throw new ModelException(INVALID_MOVIE);
	}

	private void setCinema(Cinema cinema) throws ModelException {
		if (cinema != null) {
			this.cinema = cinema;
		} else
			throw new ModelException(INVALID_CINEMA);
	}

	public Cinema getCinema() {
		return this.cinema;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public float getPrice() {
		return this.price;
	}
}
