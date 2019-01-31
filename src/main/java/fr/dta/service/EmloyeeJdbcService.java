package fr.dta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dta.repository.EmployeeJdbcRepository;

@Service
@Transactional
public class EmloyeeJdbcService {

	@Autowired
	private EmployeeJdbcRepository employeeJdbcRepository;

}
