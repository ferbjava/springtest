package com.capgemini.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.ClientDao;
import com.capgemini.dao.RentalDao;
import com.capgemini.entities.RentalEntity;
import com.capgemini.mappers.RentalMapper;
import com.capgemini.services.RentalService;
import com.capgemini.tos.RentalTO;

@Service
@Transactional(readOnly = true)
public class RentalServiceImpl implements RentalService {
	
	@Autowired
	private RentalDao rentalDao;
	
	@Autowired
	private ClientDao clientDao;

	@Override
	@Transactional(readOnly = false)
	public RentalTO saveRental(RentalTO rentalTO) {
		RentalEntity entity = RentalMapper.toRentalEntity(rentalTO);
		return RentalMapper.toRentalTO(rentalDao.save(entity));
	}

	@Override
	public RentalTO findRental(Long id) {
		return RentalMapper.toRentalTO(rentalDao.findOne(id));
	}

	@Override
	@Transactional(readOnly = false)
	public RentalTO updateRental(RentalTO rentalTO) {
		RentalEntity entity = RentalMapper.toRentalEntity(rentalTO);
		entity.setClient(clientDao.findOne(rentalTO.getClientId()));
		return RentalMapper.toRentalTO(rentalDao.save(entity));
	}

	@Override
	public List<RentalTO> findAllRentals() {
		List<RentalEntity> rentalList = new ArrayList<>();
		rentalDao.findAll().forEach(rentalList::add);
		return RentalMapper.map2TO(rentalList);
	}

	@Override
	public Long findRentalsNo() {
		return rentalDao.count();
	}

	@Override
	@Transactional(readOnly = false)
	public void removeRental(Long id) {
		rentalDao.delete(id);
	}

}
