package com.capgemini.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CAR")
public class CarEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 45)
	private String brand;
	@Column(nullable = false, length = 45)
	private String type;
	@Column(nullable = false)
	private Integer productionYear;
	@Column(nullable = false, length = 20)
	private String color;
	@Column(nullable = false)
	private Integer engineCapacity;
	@Column(nullable = false)
	private Integer enginePower;
	@Column(nullable = false)
	private Integer mileage;
	@ManyToMany
	@JoinTable(name = "EMPLOYEE_CAR", joinColumns = {
			@JoinColumn(name = "CAR_ID", nullable = false)}, inverseJoinColumns = {
			@JoinColumn(name = "EMPLOYEE_ID", nullable = false)})
	private List<EmployeeEntity> employees = new ArrayList<>();
	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE })
	private List<RentalEntity> rentals = new ArrayList<>();

	public CarEntity() {
	}

	public CarEntity(Long id, String brand, String type, Integer productionYear, String color, Integer engineCapacity,
			Integer enginePower, Integer mileage) {
		this.id = id;
		this.brand = brand;
		this.type = type;
		this.productionYear = productionYear;
		this.color = color;
		this.engineCapacity = engineCapacity;
		this.enginePower = enginePower;
		this.mileage = mileage;
	}

	public Long getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(Integer productionYear) {
		this.productionYear = productionYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(Integer engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public Integer getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(Integer enginePower) {
		this.enginePower = enginePower;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public List<EmployeeEntity> getEmoloyees() {
		return employees;
	}

	public void setEmoloyees(List<EmployeeEntity> emoloyees) {
		this.employees.clear();
		this.employees = emoloyees;
	}
	
	public void addEmployee(EmployeeEntity employee) {
		this.employees.add(employee);
	}

	public List<RentalEntity> getRentals() {
		return rentals;
	}

	public void setRentals(List<RentalEntity> rentals) {
		this.rentals.clear();
		this.rentals = rentals;
	}
	
	public void addRental() {
		
	}
	
	
	

}
