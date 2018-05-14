package com.kinoarena.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.kinoarena.dto.ScreeningDTO;
import com.kinoarena.dto.UserDTO;
import com.kinoarena.model.dao.CinemaDAO;
import com.kinoarena.model.dao.ScreeningDao;
import com.kinoarena.model.vo.Cinema;
import com.kinoarena.model.vo.Screening;

@Controller
public class ScreeningProgramController {
	@Autowired
	private ScreeningDao screeningDao;

	@Autowired
	private CinemaDAO cinemaDao;

	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@RequestMapping(method = RequestMethod.GET, value = "/program")
	public String getScreeningProgram(Model model) {
		try {
			model.addAttribute("toProgram", true);
			return "forward:/cinemas";
		} catch (Exception e) {
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/program", params = { "cinemaId" })
	public String getScreeningProgram(Model model, @RequestParam("cinemaId") int cinemaId) {
		try {
			List<Cinema> cinemas = cinemaDao.getAllCinemas();
			if (cinemas.size() > 0) {
				model.addAttribute("cinemas", cinemas);
				List<ScreeningDTO> screeningDates = screeningDao.getScreeningsDtoByCinemaId(cinemaId);
				model.addAttribute("screeningDates", screeningDates);
				System.out.println("date" + screeningDates.toString());
				Cinema currentCinema = cinemaDao.getCinemaById(cinemaId);
				System.out.println("cinema" + currentCinema.getName());
				
				model.addAttribute("currentCinema", currentCinema);
			}
			return "program";
		} catch (Exception e) {
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/screenings", params = "cinemaId")
	public void getScreeningsDtoByCinemaId(@RequestParam("cinemaId") int cinemaId, HttpServletResponse response) {
		try {
			List<ScreeningDTO> screenings = screeningDao.getScreeningsDtoByCinemaId(cinemaId);
			String json = gson.toJson(screenings);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Returns hashmap of Movie:{hall{[screenings]}}, used in movieDetail page
	@RequestMapping(method = RequestMethod.GET, value = "/screenings", params = { "cinemaId", "date" })
	public void getScreeningsByMovieIdAndDate(@RequestParam("cinemaId") int cinemaId, @RequestParam("date") String date,
			HttpServletResponse response) {
		try {
			System.out.println(cinemaId + " " + date);
			Map<String, Map<Integer, List<Screening>>> screenings = screeningDao.getScreeningsByCinemaIdAndDate(cinemaId,date);
			String json = gson.toJson(screenings);
			System.out.println(screenings);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
