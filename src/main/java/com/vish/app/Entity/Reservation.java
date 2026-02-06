package com.vish.app.Entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

	// reservation fields
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private int id;
	
	@Column(name = "reservation_room")
	private String room;
	
	@Column(name = "reservation_num_of_rooms")
	private int numOfRooms;

	@Column(name = "reservation_num_of_persons")
	private int numOfPersons;

	@Column(name = "reservation_num_of_children")
	private int numOfChildren;

	@Column(name = "reservation_open_buffet")
	private String openBuffet;

	@Column(name = "reservation_stay_days")
	private int stayDays;

	@Column(name = "reservation_from_date")
	private Date arrivalDate;
	
	@Column(name = "reservation_price")
	private int price;
	
	// join column - foreign key
	@Column(name = "reservation_user_id")
	private int userId;
	
	
	// constructor
	
	public Reservation() {
		
	}
	
	public Reservation(String room, int nRooms, int nPersons, int nChildren, String openBuffet, int stayDays, Date arrivalDate, int price, int userId) {
		this.room = room;
		this.numOfRooms = nRooms;
		this.numOfPersons = nPersons;
		this.numOfChildren = nChildren;
		this.openBuffet = openBuffet;
		this.stayDays = stayDays;
		this.arrivalDate = arrivalDate;
		this.price = price;
		this.userId = userId;			
	}

	// getter setter 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getNumOfRooms() {
		return numOfRooms;
	}

	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}

	public int getNumOfPersons() {
		return numOfPersons;
	}

	public void setNumOfPersons(int numOfPersons) {
		this.numOfPersons = numOfPersons;
	}

	public int getNumOfChildren() {
		return numOfChildren;
	}

	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}

	public String getOpenBuffet() {
		return openBuffet;
	}

	public void setOpenBuffet(String openBuffet) {
		this.openBuffet = openBuffet;
	}

	public int getStayDays() {
		return stayDays;
	}

	public void setStayDays(int stayDays) {
		this.stayDays = stayDays;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	// override toString

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", room=" + room + ", numOfRooms=" + numOfRooms + ", numOfPersons="
				+ numOfPersons + ", numOfChildren=" + numOfChildren + ", openBuffet=" + openBuffet + ", stayDays="
				+ stayDays + ", arrivalDate=" + arrivalDate + ", price=" + price + ", userId=" + userId + "]";
	}
	
	
}
