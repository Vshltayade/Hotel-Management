package com.vish.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vish.app.Entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Integer>{

	Reservation findById(int reservationId);
	List<Reservation> findAllByUserId(int userId);
}
