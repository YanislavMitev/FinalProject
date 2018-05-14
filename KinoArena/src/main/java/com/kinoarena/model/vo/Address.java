package com.kinoarena.model.vo;

import com.kinoarena.exceptions.ModelException;
import com.kinoarena.utils.Utils;

public class Address {
	private static final String INVALID_ADDRESS_ID = "Invalid address id.";
	private static final String INVALID_ADDRESS = "Invalid address entered.";
	private static final String INVALID_POSTCODE = "Invalid postcode entered.";
	private static final String INVALID_POSTCODE_LENGTH = "Invalid postcode length";
	private static final int MAX_POSTCODE_LENGTH = 4;
	private int id;
	private String address;
	private String postcode;
	private String city;

	public Address(int id, String address, String postcode, String city) throws ModelException {
		setId(id);
		setAddress(address);
		setPostcode(postcode);
		setCity(city);
	}

	public void setId(int id) throws ModelException {
		if(id >= 0) {
			this.id = id;
		}else throw new ModelException(INVALID_ADDRESS_ID);
	}
	
	public void setCity(String city) throws ModelException {
		if (city != null) {
			this.city = city;
		} else
			throw new ModelException("Invalid city");
	}

	public void setPostcode(String postcode) throws ModelException {
		if (Utils.checkString(postcode)) {
			if (postcode.length() == MAX_POSTCODE_LENGTH) {
				this.postcode = postcode;
			} else
				throw new ModelException(INVALID_POSTCODE_LENGTH);
		} else
			throw new ModelException(INVALID_POSTCODE);
	}

	public void setAddress(String address) throws ModelException {
		if (Utils.checkString(address)) {
			this.address = address;
		} else
			throw new ModelException(INVALID_ADDRESS);
	}

	public String getAddress() {
		return this.address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public String getCity() {
		return this.city;
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Address [address=" + address + ", postcode=" + postcode + ", city=" + city + "]";
	}
	

}
