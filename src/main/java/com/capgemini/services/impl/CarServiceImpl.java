package com.capgemini.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CarDao;
import com.capgemini.entities.CarEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.services.CarService;
import com.capgemini.tos.CarTO;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carDao;

	@Override
	@Transactional(readOnly = false)
	public CarTO saveCar(CarTO carTO) {
		CarEntity entity = CarMapper.toCarEntity(carTO);
		return CarMapper.toCarTO(carDao.save(entity));
	}

	@Override
	public CarTO findCar(Long id) {
		return CarMapper.toCarTO(carDao.findById(id).get());
	}

	@Override
	@Transactional(readOnly = false)
	public CarTO updateCar(CarTO carTO) {
		CarEntity entity = CarMapper.toCarEntity(carTO);
		return CarMapper.toCarTO(carDao.save(entity));
	}

	@Override
	public List<CarTO> findAllCars() {
		List<CarEntity> carsList = new ArrayList<>();
		carDao.findAll().forEach(carsList::add);
		return CarMapper.map2TOs(carsList);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeCar(Long id) {
		carDao.deleteById(id);
	}

	@Override
	public Long findCarNo() {
		return carDao.count();
	}

}
