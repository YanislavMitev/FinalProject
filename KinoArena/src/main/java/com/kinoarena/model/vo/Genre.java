package com.kinoarena.model.vo;

public class Genre {
	private int id;
	private String genre;

	public Genre() {

	}

	public Genre(int id, String genre) {
		this.setId(id);
		this.setGenre(genre);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
