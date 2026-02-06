package com.vish.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vish.app.Entity.Reservation;

import jakarta.persistence.EntityManager;

@Repository
public class ReservationDaoImpl implements ReservationDao {

	// send / receive data to / from db
	
	// entity manager to manage reservation
	@Autowired
	private EntityManager entityManager;
	
	// get reservation by id
	@Override
	public Reservation getReservationForLoggedUserById(int resId) {

		Query<Reservation> query = currentSession().createQuery("from Reservation where reservation_id=:resId",
				Reservation.class);
		query.setParameter("resId", resId);

		return query.getSingleResult();
	}

	// list all reservation
	@Override
	public List<Reservation> getReservationsByUserId(int userId) {

		Query<Reservation> query = currentSession().createQuery("from Reservation where reservation_user_id=:userId",
				Reservation.class);
		query.setParameter("userId", userId);

		return query.getResultList();
	}

	// save res
	@Override
	public void saveOrUpdateReservation(Reservation reservation) {
		currentSession().merge(reservation);
	}

	// delete res
	@Override
	public void deleteReservation(Reservation reservation) {
		currentSession().remove(reservation);
	}
	
	
	// get current hibernate session
	private Session currentSession() {
		return entityManager.unwrap(Session.class);
	}

}
