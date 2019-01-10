package com.capgemini.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.ClientDao;
import com.capgemini.dao.RentalDao;
import com.capgemini.entities.ClientEntity;
import com.capgemini.entities.RentalEntity;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.mappers.RentalMapper;
import com.capgemini.services.ClientService;
import com.capgemini.tos.ClientTO;
import com.capgemini.tos.RentalTO;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private RentalDao rentalDao;

	@Override
	@Transactional(readOnly = false)
	public ClientTO saveClient(ClientTO carTO) {
		ClientEntity entity = ClientMapper.toClientEntity(carTO);
		return ClientMapper.toClientTO(clientDao.save(entity));
	}

	@Override
	public ClientTO findClient(Long id) {
		return ClientMapper.toClientTO(clientDao.findOne(id));
	}

	@Override
	@Transactional(readOnly = false)
	public ClientTO updateClient(ClientTO clientTO) {
		ClientEntity entity = ClientMapper.toClientEntity(clientTO);
		clientTO.getRentalsIds().forEach(rentalId -> {
			entity.addRental(rentalDao.findOne(rentalId));
		});
		return ClientMapper.toClientTO(clientDao.save(entity));
	}

	@Override
	public List<ClientTO> findAllClients() {
		List<ClientEntity> clientList = new ArrayList<>();
		clientDao.findAll().forEach(clientList::add);
		return ClientMapper.map2TO(clientList);
	}

	@Override
	public Long findClientsNo() {
		return clientDao.count();
	}

	@Override
	@Transactional(readOnly = false)
	public void removeClient(Long id) {
		clientDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false)
	public ClientTO addRentalToClient(RentalTO rentalTO) {
		RentalEntity rentalEntity = RentalMapper.toRentalEntity(rentalTO);
		ClientEntity clientEntity = clientDao.findOne(rentalTO.getClientId());
		rentalEntity.setClient(clientEntity);
		clientEntity.addRental(rentalEntity);
		return ClientMapper.toClientTO(clientDao.save(clientEntity));
	}

}
