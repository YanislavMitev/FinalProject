package com.kinoarena.model.vo;

import java.time.LocalDate;

import com.kinoarena.exceptions.ModelException;
import com.kinoarena.utils.Utils;

public class User {
	private static final String NO_ADMIN_RIGHTS = "You don't have permission to make other user admin!";
	private static final String INVALID_USER = "Invalid user!";
	public static final String INVALID_USER_ID = "Invalid user id.";
	public static final String INVALID_JOB = "Invalid job entered";
	public static final String INVALID_EDUCATION = "Invalid education entered.";
	public static final String INVALID_GSM = "Invalid GSM entered.";
	public static final String INVALID_ADDRESS = "Invalid address.";
	public static final String INVALID_INPUT = "Invalid input.";
	public static final String INVALID_EMAIL_PATTERN = "Invalid email pattern.";
	public static final String INVALID_PASSWORD = "Invalid password.";
	public static final String INVALID_FIRST_NAME = "Invalid first name of user.";
	public static final String INVALID_SECOND_NAME = "Invalid second name of user.";
	public static final String INVALID_LAST_NAME = "Invalid last name of user.";

	private int id;
	private String email;
	private String password;
	private String firstName;
	private String secondName;
	private String lastName;
	private boolean isMale;
	private LocalDate birthday;
	private String gsm;
	private String education;
	private String job;
	private Address address;
	private boolean isAdmin;

	public User() {
		this.isAdmin = false;
	}

	public User(int id, String email, String password, String firstName, String secondName, String lastName,
			boolean isMale, LocalDate birthday) throws ModelException {
		this();
		setId(id);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setSecondName(secondName);
		setLastName(lastName);
		setSex(isMale);

	}

	public User(String firstName, String secondName, String lastName, String email, String password, String sex,
			LocalDate birthday) throws ModelException {
		this();
		setFirstName(firstName);
		setSecondName(secondName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setSex(isMale);
		setBirthday(birthday);
	}

	public void setId(int id) throws ModelException {
		if (id > 0) {
			this.id = id;
		} else
			throw new ModelException(INVALID_USER_ID);
	}

	public void setGSM(String gsm) throws ModelException {
		if (Utils.gsmValidator(gsm)) {
			this.gsm = gsm;
		} else
			throw new ModelException(INVALID_GSM);
	}

	public void setAddress(Address address) throws ModelException {
		if (address != null) {
			this.address = address;
		} else
			throw new ModelException(INVALID_ADDRESS);
	}

	public Address getAddress() {
		return this.address;
	}

	public void setSex(boolean isMale) {
		this.isMale = isMale;
	}

	public void setLastName(String lastName) throws ModelException {
		if (Utils.checkString(lastName)) {
			this.lastName = lastName;
		} else
			throw new ModelException(INVALID_LAST_NAME);
	}

	public void setSecondName(String secondName) throws ModelException {
		if (Utils.checkString(secondName)) {
			this.secondName = secondName;
		} else
			throw new ModelException(INVALID_SECOND_NAME);
	}

	public void setFirstName(String firstName) throws ModelException {
		if (Utils.checkString(firstName)) {
			this.firstName = firstName;
		} else
			throw new ModelException(INVALID_FIRST_NAME);
	}

	public void setPassword(String password) throws ModelException {
		if (Utils.validatePassword(password)) {
			this.password = password;
		} else
			throw new ModelException(INVALID_PASSWORD);
	}

	public void setEmail(String email) throws ModelException {
		if (Utils.checkString(email)) {
			if (Utils.emailValidator(email)) {
				this.email = email;
			} else
				throw new ModelException(INVALID_EMAIL_PATTERN);
		} else
			throw new ModelException(INVALID_INPUT);
	}

	public void setJob(String job) throws ModelException {
		if (Utils.checkString(job)) {
			this.job = job;
		} else
			throw new ModelException(INVALID_JOB);
	}

	public void setEducation(String education) throws ModelException {
		if (Utils.checkString(education)) {
			this.education = education;
		} else
			throw new ModelException(INVALID_EDUCATION);
	}

	public void setAdminRights(boolean rights) throws ModelException {
		this.isAdmin = rights;
	}

	public int getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public boolean isMale() {
		return this.isMale;
	}

	public String getBirthday() {
		return birthday.toString();
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getGsm() {
		return gsm;
	}

	public String getEducation() {
		return education;
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	public String getJob() {
		return job;
	}

}
