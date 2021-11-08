package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportingStructureService {
	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public ReportingStructure compute(Employee employee){
		ReportingStructure reportingStructure = new ReportingStructure();
		List<Employee> directReports = employee.getDirectReports();
		reportingStructure.setEmployee(employee);
		reportingStructure.setNumberOfReports(computeReports(directReports));
		return reportingStructure;
	}

	private int computeReports(List<Employee> directReports){
		int reports = 0;
		for (Employee directReport : directReports) {
			reports += 1;
			Employee employee = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
			if(employee.getDirectReports() != null){
				reports += computeReports(employee.getDirectReports());
			}
		}
		return reports;
	}
}
