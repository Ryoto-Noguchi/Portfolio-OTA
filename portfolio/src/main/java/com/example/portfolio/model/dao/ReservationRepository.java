package com.example.portfolio.model.dao;

import com.example.portfolio.model.entity.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	@Modifying
	@Query(value = "DELETE FROM reservations WHERE id = :reservationId", nativeQuery = true)
	int deleteByReservationId(@Param("reservationId") Integer reservationId);

}
