package com.kinoarena.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kinoarena.model.dao.MovieDao;
import com.kinoarena.model.vo.Movie;

@Controller
public class HomeController {
	@Autowired
	private MovieDao movieDao;

	private Gson gson = new GsonBuilder().create();

	@RequestMapping(method = RequestMethod.GET, value = { "/", "/index" })
	public String home(Model model) {
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/error")
	public String errorPage(Model model) {
		return "error";
	}
	//returns active movies, used in home page for 'all movies' filter
	@RequestMapping(method = RequestMethod.GET, value = "/movies")
	public void getActiveMovies(Model model, HttpServletResponse response) {
		try {
			List<Movie> movies = movieDao.getActiveMovies();
			// TODO FSON global
			String json = gson.toJson(movies);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().println("error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
	//returns active movies by genre , used in home page for 'animation' filter
	@RequestMapping(method = RequestMethod.GET, value = "/movies", params = "genre")
	public void getActiveMoviesByGenre(Model model, @RequestParam("genre") String genre, HttpServletResponse response) {
		try {
			System.out.println(genre);
			List<Movie> movies = movieDao.getActiveMoviesByGenre(genre);
			String json = gson.toJson(movies);
			System.out.println(json);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//returns active movies by genre , used in home page for `VIP/LUXE/IMAX/PREMIUM` filter
	@RequestMapping(method = RequestMethod.GET, value = "/movies", params = "hall")
	public void getActiveMoviesByHallType(Model model, @RequestParam("hall") String hall,
			HttpServletResponse response) {
		try {
			List<Movie> movies = movieDao.getActiveMoviesByHallType(hall.toUpperCase());
			String json = gson.toJson(movies);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//returns active movies by title , used in home page for searchbar
	@RequestMapping(method = RequestMethod.GET, value = "/movies", params = "title")
	public void getActiveMoviesByTitle(Model model, @RequestParam("title") String title, HttpServletResponse response) {
		try {
			List<Movie> movies = movieDao.getActiveMoviesByTitle(title);
			String json = gson.toJson(movies);
			System.out.println(json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
