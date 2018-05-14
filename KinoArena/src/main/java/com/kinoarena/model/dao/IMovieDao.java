package com.kinoarena.model.dao;


import java.util.List;

import com.kinoarena.dto.UserDTO;
import com.kinoarena.model.vo.Genre;
import com.kinoarena.model.vo.Movie;

public interface IMovieDao {
	
	public void addMovie(Movie movie) throws Exception;

	public void editMovie(Movie movie) throws Exception;

	public void deleteMovie(Movie movie) throws Exception;

	public List<Movie> getAllMovies() throws Exception;

	public Movie getMovieById(int id) throws Exception;
	
	public Genre getGenre(String genre);
	
	public Movie getMovieByName(String name);
	
}
