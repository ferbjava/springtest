package com.capgemini.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RENTAL")
public class RentalEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Calendar rentalDate;
	@ManyToOne
	private ClientEntity client;
	@ManyToOne
	private CarEntity car;

	public RentalEntity() {
	}

	public RentalEntity(Long id, Calendar rentalDate) {
		this.id = id;
		this.rentalDate = rentalDate;
	}

	public Long getId() {
		return id;
	}

	public Calendar getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Calendar rentalDate) {
		this.rentalDate = rentalDate;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public CarEntity getCar() {
		return car;
	}

	public void setCar(CarEntity car) {
		this.car = car;
	}

}
