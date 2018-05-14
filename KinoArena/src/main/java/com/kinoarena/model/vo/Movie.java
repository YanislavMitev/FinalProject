package com.kinoarena.model.vo;

import java.sql.Date;
import java.time.LocalDate;

import com.kinoarena.exceptions.MovieException;

public class Movie {
	private int id;
	private String title;
	private String coverURL;
	private String description;
	private String director;
	private int duration;
	private LocalDate primiere;
	private int ageLimitation;
	private String movieType;
	private Genre genre;

	public Movie() {
	}

	public Movie(int id, String title, int duration) throws MovieException {
		this.setId(id);
		this.setTitle(title);
		this.setDirector(director);
		this.setDuration(duration);
	}

	public Movie(int id, String title, String coverURL, int duration) throws MovieException {
		this(id, title, duration);
		this.setCoverURL(coverURL);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws MovieException {
		if (title == null) {
			throw new MovieException("Invalid title");
		}
		this.title = title;
	}

	public String getCoverURL() {
		return coverURL;
	}

	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		if (director != null) {
			this.director = director;
		}

	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) throws MovieException {
		if (duration <= 0) {
			throw new MovieException("Invalid duration");
		}
		this.duration = duration;
	}

	public int getAgeLimitation() {
		return ageLimitation;
	}

	public void setAgeLimitation(int pgAge) {
		this.ageLimitation = pgAge;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) throws MovieException {
		if (genre != null) {
			this.genre = genre;
		}

	}

	public LocalDate getPrimiere() {
		return primiere;
	}

	public void setPrimiere(Date primiere) throws MovieException {
		if (primiere != null) {
			this.primiere = primiere.toLocalDate();
		}
	}

	public void setPrimiere(LocalDate primiere) throws MovieException {
		if (primiere != null) {
			this.primiere = primiere;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) throws MovieException {
		if (movieType == null) {
			throw new MovieException("Invalid movieType");
		}
		this.movieType = movieType;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", coverURL=" + coverURL + ", description=" + description
				+ ", director=" + director + ", duration=" + duration + ", primiere=" + primiere + ", ageLimitation="
				+ ageLimitation + ", movieType=" + movieType + ", genre=" + genre + "]";
	}

}
