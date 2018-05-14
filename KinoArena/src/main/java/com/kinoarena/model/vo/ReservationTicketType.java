package com.kinoarena.model.vo;

public class ReservationTicketType {
	private int id;
	private String type;
	private float price;

	public ReservationTicketType(int id, String type, float price) {
		this.setId(id);
		this.setType(type);
		this.setPrice(price);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type != null) {
			this.type = type;
		}
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		if (price >= 0) {
			this.price = price;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ReservationTicketType [id=" + id + ", type=" + type + ", price=" + price + "]";
	};

}
