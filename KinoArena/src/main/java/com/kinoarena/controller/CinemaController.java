package com.kinoarena.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kinoarena.model.dao.CinemaDAO;
import com.kinoarena.model.vo.Cinema;

@Controller
public class CinemaController {

	@Autowired
	private CinemaDAO cinemaDao;

	private Gson gson = new GsonBuilder().create();

	@RequestMapping(method = RequestMethod.GET, value = "/cinemas")
	public String cinemasPage(Model model, HttpServletResponse response) {
		try {
			List<Cinema> cinemas = cinemaDao.getAllCinemas();
			model.addAttribute("cinemas", cinemas);
			
			return "cinemas";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
			
		}
	}
	//returns cinema, used in cinemaDetail page
	@RequestMapping(method = RequestMethod.GET, value = "/cinema/{id}")
	public String cinemaDetailPage(Model model, @PathVariable int id) {
		try {
			Cinema cinema = cinemaDao.getCinemaById(id);
			model.addAttribute("cinema", cinema);
			return "cinemaDetail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
