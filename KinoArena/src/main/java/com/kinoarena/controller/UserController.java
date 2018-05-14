package com.kinoarena.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kinoarena.dto.UserDTO;
import com.kinoarena.model.dao.FavoriteMovieDAO;
import com.kinoarena.model.vo.Movie;

@Controller
public class UserController {

	@Autowired
	private FavoriteMovieDAO favoriteMovieDao;

	private Gson gson = new GsonBuilder().create();

	@RequestMapping(method = RequestMethod.POST, value = "/addToFavorites", params = "movieID")
	public void addFavoriteMovie(Model model, @RequestParam("movieID") int movieID, HttpServletResponse response,
			HttpSession session) {
		try {
			if (session.getAttribute("loggedUser") == null) {
				response.setStatus(302);
				response.getWriter().println("/KinoArena/login");
				return;
			}
			UserDTO user = (UserDTO) session.getAttribute("loggedUser");
			favoriteMovieDao.addFavoriteMovie(user.getId(), movieID);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().println("error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/favoriteMovies")
	public void getFavoriteMovies(Model model, HttpServletResponse response, HttpSession session) {
		try {
			if (session.getAttribute("loggedUser") == null) {
				response.setStatus(302);
				response.getWriter().println("/KinoArena/login");
			}
			UserDTO user = (UserDTO) session.getAttribute("loggedUser");
			List<Movie> movies = favoriteMovieDao.getFavoriteMovies(user.getId());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(gson.toJson(movies));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeFavorite", params = "movieID")
	public void removeFavoriteMovie(Model model, @RequestParam("movieID") int movieID, HttpServletResponse response,
			HttpSession session) {
		try {
			if (session.getAttribute("loggedUser") == null) {
				response.setStatus(302);
				response.getWriter().println("/KinoArena/login");
				return;
			}
			UserDTO user = (UserDTO) session.getAttribute("loggedUser");
			favoriteMovieDao.removeFavoriteMovie(user.getId(), movieID);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().println("error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
	}
}
