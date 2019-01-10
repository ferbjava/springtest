package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.entities.ClientEntity;
import com.capgemini.tos.ClientTO;
import com.capgemini.tos.ClientTO.ClientTOBuilder;

public class ClientMapper {

	public static ClientTO toClientTO(ClientEntity entity) {
		if (entity == null) {
			return null;
		}
		return new ClientTOBuilder().withId(entity.getId()).withFirstName(entity.getFirstName())
				.withLastName(entity.getLastName()).withRentalsIds(RentalMapper.matoTOids(entity.getRentals())).build();
	}
	
	public static ClientEntity toClientEntity(ClientTO clientTO) {
		if (clientTO == null) {
			return null;
		}
		return new ClientEntity(clientTO.getId(), clientTO.getFirstName(), clientTO.getLastName());
	}
	
	public static List<ClientTO> map2TO(List<ClientEntity> entityList) {
		if (entityList == null) {
			return null;
		}
		return entityList.stream().map(ClientMapper::toClientTO).collect(Collectors.toList());
	}
	
	public static List<ClientEntity> map2Entities(List<ClientTO> tosList) {
		if (tosList == null) {
			return null;
		}
		return tosList.stream().map(ClientMapper::toClientEntity).collect(Collectors.toList());
	}
}
