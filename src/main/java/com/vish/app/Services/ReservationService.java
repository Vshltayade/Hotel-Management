package com.vish.app.Services;

import java.util.List;

import com.vish.app.Entity.Reservation;
import com.vish.app.temp.CurrentReservation;

public interface ReservationService {

	public Reservation getReservationForLoggedUserById(int reservationId);
	public List<Reservation> getReservationsForLoggedUser();
	public void saveOrUpdateReservation(CurrentReservation currentReservation);
	public void deleteReservation(int reservationId);
	public CurrentReservation reservationToCurrentReservationById(int reservationId);
}
