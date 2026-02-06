package com.vish.app.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vish.app.Entity.Reservation;
import com.vish.app.Repository.ReservationRepo;
import com.vish.app.temp.CurrentReservation;

import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImpl  implements ReservationService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationRepo reservationRepo;
	
	
	public ReservationServiceImpl(UserService userService, ReservationRepo reservationRepo) {
		this.userService = userService;
		this.reservationRepo = reservationRepo;
	}

	
	// reservation for logged user
	@Override
	@Transactional
	public Reservation getReservationForLoggedUserById(int reservationId) {
		return reservationRepo.findById(reservationId);
	}

	
	// all reservations from logged user
	@Override
	@Transactional
	public List<Reservation> getReservationsForLoggedUser() {
		return reservationRepo.findAllByUserId(userService.getLoggedUserId());
	}

	
	// get reservation from temp to save
	@Override
	@Transactional
	public void saveOrUpdateReservation(CurrentReservation currentReservation) {
		Reservation reservation = new Reservation();
		
		reservation.setUserId(userService.getLoggedUserId());
		reservation.setId(currentReservation.getId());
		reservation.setRoom(currentReservation.getRoom());
		reservation.setNumOfRooms(currentReservation.getNumOfRooms());
		reservation.setNumOfPersons(currentReservation.getNumOfPersons());
		reservation.setNumOfChildren(currentReservation.getNumOfChildren());
		reservation.setOpenBuffet(currentReservation.getOpenBuffet());
		reservation.setArrivalDate(currentReservation.getArrivalDate());
		reservation.setStayDays(currentReservation.getStayDays());
		reservation.setPrice(currentReservation.getPrice());
		
		reservationRepo.save(reservation);
		
	}

	
	// delete reservation
	@Override
	@Transactional
	public void deleteReservation(int reservationId) {
		reservationRepo.deleteById(reservationId);
	}

	
	// update reservation from temp reservation
	@Override
	public CurrentReservation reservationToCurrentReservationById(int reservationId) {
		Reservation reservation = getReservationForLoggedUserById(reservationId);
		CurrentReservation currentReservation = new CurrentReservation();
		
		currentReservation.setUserId(reservation.getId());
		currentReservation.setId(reservation.getId());
		currentReservation.setRoom(reservation.getRoom());
		currentReservation.setNumOfRooms(reservation.getNumOfRooms());
		currentReservation.setNumOfPersons(reservation.getNumOfPersons());
		currentReservation.setNumOfChildren(reservation.getNumOfChildren());
		currentReservation.setOpenBuffet(reservation.getOpenBuffet());
		currentReservation.setArrivalDate(reservation.getArrivalDate());
		currentReservation.setStayDays(reservation.getStayDays());
		currentReservation.setPrice(reservation.getPrice());
		
		return currentReservation;
	}

}
