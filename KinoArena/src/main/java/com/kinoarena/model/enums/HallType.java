package com.kinoarena.model.enums;

public enum HallType {
	VIP("VIP"),LUXE("LUXE"),PREMIUM("PREMIUM"),IMAX("IMAX");
	
	private String type;
	private HallType(String type) {
		this.type = type;
	}
	
	public String getHallType() {
		return this.type;
	}
}
