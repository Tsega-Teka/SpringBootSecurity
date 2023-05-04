package com.SpringBootSecurity.JPARepo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootSecurity.Entity.Student;



public interface jpaRepository extends JpaRepository<Student, Integer> {

	public Student findByDep(String dep) ;
	
}
