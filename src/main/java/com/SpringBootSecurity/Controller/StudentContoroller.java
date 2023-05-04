package com.SpringBootSecurity.Controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootSecurity.Entity.Student;
import com.SpringBootSecurity.Entity.User;

import com.SpringBootSecurity.Service.StudentService;
import com.SpringBootSecurity.Service.UserService;
import com.SpringBootSecurity.securty.UserDetailsServiceImp;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class StudentContoroller {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImp.class);

	@Autowired
	StudentService stuService;
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("/user")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<?> saveuser(@RequestBody User user1) {
		LOGGER.trace("Entering methood saveuser" );
		LOGGER.debug("Authenticating user with user name");
		return new ResponseEntity<>(userService.saveuser(user1), HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/student")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<?> saveStudent(@RequestBody @Valid Student student1) {

		return new ResponseEntity<>(stuService.saveStudent(student1), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/student")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getAllStudent(){
		LOGGER.info("Succes full facth all Student");
		return new ResponseEntity<>(stuService.getAllStudent(), HttpStatus.OK);
	}
	
	@GetMapping("/student/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getStudentbyId(@PathVariable int id){
		
		
		if(stuService.getStudentbyId(id)==null) {
			LOGGER.error("Student id is not found");
			return new ResponseEntity<>("student id is not found  ", HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Succes full facth by id");
		return new ResponseEntity<>(stuService.getStudentbyId(id),HttpStatus.FOUND);
		
	}
	
	@PutMapping("/student/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student stu){
		
		
		if(stuService.updateStudent(id, stu)==null) {
			LOGGER.error("Student is not update");
			return new ResponseEntity<>("Student is not update", HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Succes Student data is updated");
		return new ResponseEntity<>(stuService.updateStudent(id, stu), HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/studentDep/{dep}")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<?> getStudentbyDep(@PathVariable String dep){
		LOGGER.info("Succes full facth by dep");
			return new ResponseEntity<>(stuService.getStudentbyDep(dep),HttpStatus.OK);

	}
	
	@DeleteMapping("/student/{id}")
	@PreAuthorize("hasAuthority( 'ADMIN')")
	public ResponseEntity<?> deleteStudentbyId(@PathVariable int id){
		
		if(stuService.deleteStudentbyId(id)==0) {
			LOGGER.error("Student id is not found");
			return new ResponseEntity<>("not found the student id", HttpStatus.NOT_ACCEPTABLE);
			
		}
		LOGGER.info("Student data is deleted");
		return new ResponseEntity<>("Id "+ id + " is deleted",HttpStatus.ACCEPTED);
		
		
	}
	
}
