package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportingStructureService {
	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	private Set<String> reports = new HashSet<String>();

	public ReportingStructure compute(Employee employee){
		reports.clear();
		ReportingStructure reportingStructure = new ReportingStructure();
		List<Employee> directReports = employee.getDirectReports();
		reportingStructure.setEmployee(employee);
		computeReports(directReports);
		reportingStructure.setNumberOfReports(reports.size());
		return reportingStructure;
	}

	private void computeReports(List<Employee> directReports){
		for (Employee directReport : directReports) {
			reports.add(directReport.getEmployeeId());
			Employee employee = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
			if(employee.getDirectReports() != null){
				computeReports(employee.getDirectReports());
			}
		}
	}
}
