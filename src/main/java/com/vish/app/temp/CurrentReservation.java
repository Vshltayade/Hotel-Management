package com.vish.app.temp;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

public class CurrentReservation {

		// temp class to filter data and get it from controller to database using services
		//  current reservation fields and annotate to get the required data
		
	 @NotNull(message = "is required")
	    @Min(value = 1, message = "must be at least 1")
	    private int id;

	    @NotNull(message = "is required")
	    @Min(value = 1, message = "must be at least 1")
	    private int stayDays;

	    @NotNull(message = "is required")
	    private String room;

	    @NotNull(message = "is required")
	    @Min(value = 1, message = "must be at least 1")
	    private int price;

	    @NotNull(message = "is required")
	    @Min(value = 1, message = "must be at least 1")
	    private int numOfRooms;

	    @NotNull(message = "is required")
	    @Min(value = 1, message = "must be at least 1")
	    private int numOfPersons;

	    @NotNull(message = "is required")
	    @Min(value = 0, message = "must be 0 or more")
	    private int numOfChildren;

	    @NotNull(message = "is required")
	    private String openBuffet;

	    @NotNull(message = "is required")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date arrivalDate;

	    @NotNull(message = "is required")
	    @Min(value = 1, message = "must be at least 1")
	    private int userId;
	    
		
	    public CurrentReservation() {}

	    public CurrentReservation(int id, int stayDays, String room, int price, int numOfRooms, int numOfPersons,
	                              int numOfChildren, String openBuffet, Date arrivalDate, int userId) {
	        this.id = id;
	        this.stayDays = stayDays;
	        this.room = room;
	        this.price = price;
	        this.numOfRooms = numOfRooms;
	        this.numOfPersons = numOfPersons;
	        this.numOfChildren = numOfChildren;
	        this.openBuffet = openBuffet;
	        this.arrivalDate = arrivalDate;
	        this.userId = userId;
	    }
	    

		// getter setter
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getStayDays() {
			return stayDays;
		}

		public void setStayDays(int stayDays) {
			this.stayDays = stayDays;
		}

		public String getRoom() {
			return room;
		}

		public void setRoom(String room) {
			this.room = room;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
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

		public Date getArrivalDate() {
			return arrivalDate;
		}

		public void setArrivalDate(Date arrivalDate) {
			this.arrivalDate = arrivalDate;
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
			return "CurrentReservation [id=" + id + ", stayPeriod=" + stayDays + ", room=" + room + ", price=" + price
					+ ", numOfRooms=" + numOfRooms + ", numOfPersons=" + numOfPersons + ", numOfChildren="
					+ numOfChildren + ", openBuffet=" + openBuffet + ", arrivalDate=" + arrivalDate + ", userId="
					+ userId + "]";
		}
		
		
}
