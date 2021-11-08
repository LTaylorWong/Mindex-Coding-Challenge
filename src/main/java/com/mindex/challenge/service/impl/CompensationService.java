package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationService {
	private static final Logger LOG = LoggerFactory.getLogger(CompensationService.class);

	@Autowired
	private CompensationRepository compensationRepository;

	public Compensation create(Compensation compensation){
		LOG.debug("Creating compensation [{}]", compensation);
		compensationRepository.insert(compensation);
		return compensation;
	}

	public Compensation read(Employee employee){
		LOG.debug("Reading compensation of employee [{}]", employee);
		Compensation compensation = compensationRepository.findByEmployee(employee);
		if (compensation == null){
			throw new RuntimeException("No Compensation for: " + employee.getEmployeeId());
		}
	 	return compensation;
	}
}
