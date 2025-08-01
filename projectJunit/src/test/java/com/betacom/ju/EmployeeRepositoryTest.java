package com.betacom.ju;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = false)
public class EmployeeRepositoryTest {
	
	@Autowired
	private IEmployeeRepository repoR;
	
	@Test
	@Order(1)
	public void saveEmployee() {
		Employee e = new Employee();
		e.setNome("Marco");
		e.setCognome("Rossi");
		e.setEmail("m.rossi@gmail.com");
		 
		Integer id = repoR.save(e).getId();
		System.out.println("key1: " +id);
		
		Assertions.assertThat(id).isEqualTo(1);
	}
	
	@Test
	@Order(2)
	public void saveEmployee2() {
		Employee e = new Employee();
		e.setNome("Giuseppe");
		e.setCognome("Verdi");
		e.setEmail("g.verdi@gmail.com");
		
		Integer id = repoR.save(e).getId();
		System.out.println("key2: " +id);
		
		Assertions.assertThat(id).isEqualTo(2);
	}
	
	@Test
	@Order(3)
	public void getEmployeeTest() {
		Employee e = repoR.findById(1).get();
		Assertions.assertThat(e.getId()).isEqualTo(1);
	}
}
