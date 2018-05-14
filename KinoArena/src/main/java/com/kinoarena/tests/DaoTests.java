package com.kinoarena.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kinoarena.config.SpringWebConfig;
import com.kinoarena.exceptions.WebProfileException;
import com.kinoarena.model.dao.AddressDao;
import com.kinoarena.model.dao.CinemaDAO;
import com.kinoarena.model.dao.HallDAO;
import com.kinoarena.model.dao.MovieDao;
import com.kinoarena.model.dao.ScreeningDao;
import com.kinoarena.model.dao.SeatDAO;
import com.kinoarena.model.dao.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringWebConfig.class)
@WebAppConfiguration
public class DaoTests {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private CinemaDAO cinemaDao;

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private ScreeningDao screeningDao;

	@Autowired
	private HallDAO hallDao;

	@Autowired
	private SeatDAO seatDao;

	@Test
	public void testUserId() {
		int testId = 17;
		int realId = userDao.getUserId("admin@kinoarena.com");
		assertEquals(testId, realId);
	}

	@Test
	public void testCinemaId() {
		int testCinemaId = 1;
		int realCinemaId = cinemaDao.getCinemaByName("Kino Arena Zapad").getId();
		assertEquals(testCinemaId, realCinemaId);
	}

	@Test
	public void testCinemaGSM() {
		String testGsm = "0882748341";
		String realGsm = cinemaDao.getCinemaById(6).getGsm();
		assertEquals(testGsm, realGsm);
	}

	@Test
	public void testMovie() {
		String testMovieName = "AVENGERS: INFINITY WAR";
		String realMovieName = movieDao.getMovieById(12).getTitle();
		assertEquals(testMovieName, realMovieName);
	}

	@Test
	public void testScreening() {
		String testStartTime = "2018-05-09T21:02:11";
		String realStartTime = screeningDao.getScreeningById(20).getStartTime().toString();
		assertEquals(testStartTime, realStartTime);
	}

	@Test
	public void testAddressId() {
		int testAddressId = 8;
		int realAddressId = addressDao.getAddress("Zapaden Park Distr., 64 Todor Aleksandrov Blvd.", "1222", "Sofia")
				.getId();
		assertEquals(testAddressId, realAddressId);
	}

	@Test
	public void testStreetAddress() {
		String testStreetAddress = "admin address";
		String realStreetAddress = addressDao.getAddress("admin address", "1255", "adminCity").getAddress();
		assertEquals(testStreetAddress, realStreetAddress);
	}

	@Test(expected = Test.None.class)
	public void testGetGenre() {
		movieDao.getGenre("Action");
	}

	@Test(expected = Test.None.class)
	public void testGetLastHallId() {
		hallDao.getLastHallId();
	}

	@Test(expected = WebProfileException.class)
	public void testInvalidCredentialsLogin() throws WebProfileException {
		userDao.login("asflkjwehaf", "al;skdgzhbkaf;kg");

	}

	@Test(expected = Exception.class)
	public void testNullValuesForRegister() {
		userDao.register(null, null, null, null, null, false, null);
	}

	@Test(expected = Exception.class)
	public void testNullValuesForChangingPassword() {
		userDao.changePassword(null, null);
	}

	@Test(expected = Exception.class)
	public void testGetSeatsByInvalidHallId() throws Exception {
		seatDao.getAllSeadsByHall(new Integer(null));

	}
}
