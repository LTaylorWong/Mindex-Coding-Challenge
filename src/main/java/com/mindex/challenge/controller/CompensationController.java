package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.impl.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CompensationController {
	private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CompensationService compensationService;

	@PostMapping("/compensation")
	public Compensation create(@RequestBody Compensation compensation){
		LOG.debug("Received employee create request for [{}]", compensation);
		return compensationService.create(compensation);
	}
	@GetMapping("compensation/{id}")
	public Compensation read(@PathVariable String id){
		LOG.debug("Received compensation read request for id [{}]", id);
		Employee e = employeeService.read(id);
		return compensationService.read(e);
	}
}
