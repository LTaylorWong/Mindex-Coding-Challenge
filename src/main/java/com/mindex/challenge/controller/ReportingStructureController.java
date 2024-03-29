package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;

import com.mindex.challenge.service.impl.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {
	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ReportingStructureService reportingStructureService;

	@GetMapping("/reportingStructure/{id}")
	public ReportingStructure reportingStructure(@PathVariable String id){
		LOG.debug("Received employee reporting structure request for id [{}]", id);
		Employee e = employeeService.read(id);
		return reportingStructureService.compute(e);
	}
}
