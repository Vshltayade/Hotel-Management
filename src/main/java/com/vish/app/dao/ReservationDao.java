package com.vish.app.dao;

import java.util.List;

import com.vish.app.Entity.Reservation;

public interface ReservationDao {

	public Reservation getReservationForLoggedUserById(int resId);

	public List<Reservation> getReservationsByUserId(int userId);
	
	public void saveOrUpdateReservation(Reservation reservation);
	
	public void deleteReservation(Reservation reservation);
	
}
