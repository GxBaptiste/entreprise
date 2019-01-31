package fr.dta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.dta.modele.Employee;
import fr.dta.repository.EmployeeJdbcRepository;
import fr.dta.repository.EmployeeMockRepository;
import fr.dta.service.EmployeeMockService;
import fr.dta.service.EntrepriseService;

public class EmployeeTest {

	AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void testApp() {

		EmployeeMockRepository test = (EmployeeMockRepository) context.getBean("employeeMockRepository");
		assertNotNull(test);

		EmployeeMockService test2 = (EmployeeMockService) context.getBean("employeeMockService");
		Employee e1 = test2.findLastHired();
		assertEquals(e1.getId(), new Long(1));

		EntrepriseService test3 = (EntrepriseService) context.getBean("entrepriseService");
		assertNotNull(test3);
		test3.findEmployees().stream().forEach(x -> System.out.println(x.getId()));
	}

	@Test
	public void testSave() {
		EmployeeJdbcRepository emp = (EmployeeJdbcRepository) context.getBean("employeeJdbcRepository");
		Employee e3 = new Employee(new Long(1), "Benjamin", "Montet", "0123456987", new BigDecimal(2500.0),
				LocalDate.of(2018, Month.JANUARY, 1));
		Employee e4 = new Employee(new Long(1), "Baptiste", "Carreaux", "0231564789", new BigDecimal(2800.0),
				LocalDate.of(2017, Month.JANUARY, 1));
		Employee e5 = new Employee(new Long(1), "Elie", "Rouer", "0147852369", new BigDecimal(2650.0),
				LocalDate.of(2019, Month.JANUARY, 1));
		emp.saveEmployee(e3);
		emp.saveEmployee(e5);
		emp.saveEmployee(e4);

		for (Employee e : emp.findAllEmployees()) {
			System.out.println(e.toString());
		}

		System.out.println("avec l'ssn : " + emp.findBySsn("0123456987"));
		e3.setNom("GNAR");
		System.out.println("test du set : " + e3);
		emp.updateEmployee(e3);
		System.out.println("e3 modifié : " + emp.findBySsn("0123456987"));

		List<Employee> listEmpl = new ArrayList<>();
		listEmpl.add(e3);
		listEmpl.add(e5);
		listEmpl.add(e4);
		Employee e6 = new Employee(new Long(1), "test", "test", "0125485934", new BigDecimal(2650.0),
				LocalDate.of(2019, Month.JANUARY, 1));
		listEmpl.add(e6);
		
		//emp.updateAllEmployee(listEmpl);

		emp.delete();
	}
}