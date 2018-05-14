package com.kinoarena.model.vo;

import com.kinoarena.exceptions.ModelException;
import com.kinoarena.utils.Utils;

public class Cinema {
	private static final String INVALID_CINEMA_NAME = "Invalid cinema name.";
	private static final String ILLEGAL_EMAIL = "Illegal email entered.";
	private static final String INVALID_EMAIL_PATTERN = "Invalid email pattern.";
	private static final String INVALID_GSM = "Invalid gsm entered.";
	private static final String INVALID_ADDRESS = "Invalid address.";

	private int id;
	private String name;
	private String email;
	private String gsm;
	private String cinemaCoverURL;

	private Address address;

	public Cinema() {
	};

	public Cinema(int id, String name, String email, String gsm, Address address) throws ModelException {
		setId(id);
		setName(name);
		setEmail(email);
		setGsm(gsm);
		setAddress(address);
	}

	public void setAddress(Address address) throws ModelException {
		if (address != null) {

			this.address = address;
		} else
			throw new ModelException(INVALID_ADDRESS);
	}

	public void setEmail(String email) throws ModelException {
		if (Utils.checkString(email)) {
				this.email = email;
		} else
			throw new ModelException(ILLEGAL_EMAIL);
	}

	public void setName(String name) throws ModelException {
		if (Utils.checkString(name)) {
			this.name = name;
		} else
			throw new ModelException(INVALID_CINEMA_NAME);
	}

	public String getCinemaCoverURL() {
		return cinemaCoverURL;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setCinemaCoverURL(String cinemaCoverURL) {
		this.cinemaCoverURL = cinemaCoverURL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGsm() {
		return gsm;
	}

	public void setGsm(String gsm) throws ModelException {
		if (Utils.gsmValidator(gsm)) {
			this.gsm = gsm;
		} else
			throw new ModelException(INVALID_GSM);
	}

}
