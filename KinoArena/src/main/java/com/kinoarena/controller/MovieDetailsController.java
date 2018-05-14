package com.kinoarena.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kinoarena.dto.ScreeningDTO;
import com.kinoarena.dto.UserDTO;
import com.kinoarena.model.dao.FavoriteMovieDAO;
import com.kinoarena.model.dao.MovieDao;
import com.kinoarena.model.dao.ScreeningDao;
import com.kinoarena.model.vo.Movie;
import com.kinoarena.model.vo.Screening;

@Controller
public class MovieDetailsController {

	@Autowired
	private ScreeningDao screeningDao;
	@Autowired
	private MovieDao movieDao;
	@Autowired
	private FavoriteMovieDAO favoriteMovieDao;

	private Gson gson = new GsonBuilder().create();

	// movieDetails page
	@RequestMapping(method = RequestMethod.GET, value = "/movies/{id}")
	public String getMovie(Model model, @PathVariable int id, HttpSession session) {
		try {
			Movie movie = movieDao.getMovieById(id);
			if (movie == null) {
				return "index";
			}
			model.addAttribute("movie", movie);

			if (session.getAttribute("loggedUser") != null) {
				UserDTO user = (UserDTO) session.getAttribute("loggedUser");
				model.addAttribute("user", user);
				Movie favoriteMovie = favoriteMovieDao.getFavoriteMovie(user.getId(), id);
				if (favoriteMovie != null) {
					model.addAttribute("inFavorites", true);
				}
			}
			List<ScreeningDTO> screeningsDates = screeningDao.getScreeningsDtoByMovieId(id);
			if (screeningsDates.size() > 0) {
				model.addAttribute("dates", screeningsDates);
			}

			return "movieDetails";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	// Returns screeningDTO used in movieDetails page for the screening dates
	@RequestMapping(method = RequestMethod.GET, value = "/screenings", params = "movieId")
	public void getScreeningsDtoByMovieId(@RequestParam("movieId") int movieId, HttpServletResponse response) {
		try {
			List<ScreeningDTO> screenings = screeningDao.getScreeningsDtoByMovieId(movieId);
			String json = gson.toJson(screenings);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Returns hashmap of Cinema:{hall{[screenings]}}, used in movieDetail page
	@RequestMapping(method = RequestMethod.GET, value = "/screenings", params = { "movieId", "date" })
	public void getScreeningsByMovieIdAndDate(@RequestParam("movieId") int movieId, @RequestParam("date") String date,
			HttpServletResponse response) {
		try {
			Map<String, Map<Integer, List<Screening>>> screenings = screeningDao.getScreeningsByMovieIdAndDate(movieId,
					date);
			String json = gson.toJson(screenings);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
