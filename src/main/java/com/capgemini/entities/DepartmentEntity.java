package com.capgemini.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENT")
public class DepartmentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String depAdrees;
	@Column(nullable = false)
	private Integer depTelephone;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department", orphanRemoval = true)
	private List<EmployeeEntity> employees = new ArrayList<>();

	public DepartmentEntity() {
	}

	public DepartmentEntity(Long id, String depAdrees, Integer depTelephone) {
		this.id = id;
		this.depAdrees = depAdrees;
		this.depTelephone = depTelephone;
	}

	public Long getId() {
		return id;
	}

	public String getDepAdrees() {
		return depAdrees;
	}

	public void setDepAdrees(String depAdrees) {
		this.depAdrees = depAdrees;
	}

	public Integer getDepTelephone() {
		return depTelephone;
	}

	public void setDepTelephone(Integer depTelephone) {
		this.depTelephone = depTelephone;
	}

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees.clear();
		this.employees = employees;
	}
	
	public void addEmployee(EmployeeEntity employee) {
		this.employees.add(employee);
	}

}
