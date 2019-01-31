package fr.dta.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.dta.modele.Employee;

@Repository
public class EmployeeJdbcRepository extends AbstractJdbcRepository implements EmployeeRepository {

	@Override
	public void saveEmployee(Employee employee) {
		this.getJdbcTemplate().update("insert into employee(firstname,hiredate,lastname,salary,ssn) values (?,?,?,?,?)",
				employee.getPrenom(), new Date(), employee.getNom(), employee.getSalaire(), employee.getNumeroSecu());
	}

	public void delete() {
		this.getJdbcTemplate().update("delete from employee;");
	}

	@Override
	public List<Employee> findAllEmployees() {
		return this.getJdbcTemplate().query("select * from employee", new EmployeeMapper());
	}

	@Override
	public Employee findBySsn(String ssn) {
		Employee employee = this.getJdbcTemplate().queryForObject("select * from employee where ssn=?",
				new Object[] { ssn }, new EmployeeMapper());
		return employee;
	}

	@Override
	public void updateEmployee(Employee employee) {
		this.getJdbcTemplate().update("update employee set firstname=?, hiredate=?, lastname=?, salary=? where ssn=?",
				employee.getPrenom(),
				Date.from(employee.getDateEmbauche().atStartOfDay(ZoneId.systemDefault()).toInstant()),
				employee.getNom(), employee.getSalaire(), employee.getNumeroSecu());
	}
	
	public void updateAllEmployee(List<Employee> employees) {
		boolean present=true;
		for(Employee e : findAllEmployees()) {
			if(!employees.contains(e)) {
				present=false;
			}
		}
		if(present) {
			for(Employee e : employees) {
				updateEmployee(e);
			}
			System.out.println("Liste mise à jour");
		}
		else {
			System.out.println("Liste nom mise à jour");
		}
	}

	private static final class EmployeeMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee emp = new Employee(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
					rs.getString("ssn"), rs.getBigDecimal("salary"),
					rs.getTimestamp("hiredate").toLocalDateTime().toLocalDate());
			return emp;
		}
	}

}
