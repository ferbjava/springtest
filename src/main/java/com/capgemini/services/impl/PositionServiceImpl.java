package com.capgemini.services.impl;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.PositionDao;
import com.capgemini.entities.PositionEntity;
import com.capgemini.mappers.PositionMapper;
import com.capgemini.services.PositionService;
import com.capgemini.tos.PositionTO;

@Service
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionDao positionDao;

	@Override
	public Long findPositionNo() {
		return positionDao.count();
	}

	@Override
	@Transactional(readOnly = false)
	public PositionTO savePosition(PositionTO positionTO) {
		PositionEntity entity = PositionMapper.toPositionEntity(positionTO);
		return PositionMapper.toPositionTO(positionDao.save(entity));
	}

	@Override
	public PositionTO findPosition(Long id) {
		return PositionMapper.toPositionTO(positionDao.findById(id).get());
	}

	@Override
	@Transactional(readOnly = false)
	public PositionTO updatePosition(PositionTO positionTO) {
		PositionEntity entity = PositionMapper.toPositionEntity(positionTO);
		return PositionMapper.toPositionTO(positionDao.save(entity));
	}

	@Override
	public List<PositionTO> findAllCPositions() {
		return PositionMapper.map2TO(Lists.newArrayList(positionDao.findAll()));
	}

	@Override
	@Transactional(readOnly = false)
	public void removePosition(Long id) {
		positionDao.deleteById(id);
	}

}
