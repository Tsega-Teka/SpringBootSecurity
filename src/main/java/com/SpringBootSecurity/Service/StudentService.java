package com.SpringBootSecurity.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.SpringBootSecurity.Entity.Student;
import com.SpringBootSecurity.JPARepo.jpaRepository;

@Service
public class StudentService {

	
	@Autowired
	jpaRepository jpaRepositery;
	
	//to post
	public Student saveStudent(Student student1) {
		jpaRepositery.save(student1);
		return student1;
		
	}
	//to get all
	public List<Student> getAllStudent(){
		
		List<Student> list=  jpaRepositery.findAll();
		return list;
		
		
		
	}
	public Student getStudentbyId( int id){
		
		Optional<Student> opt=jpaRepositery.findById(id);
		if(opt.isPresent()) {
			Student st =opt.get();
			return st;
		}
		
		return null;
	}
	
	//to update
	public Student updateStudent( int id,  Student stu){
		Optional<Student> opt=jpaRepositery.findById(id);
		if(opt.isPresent()) {
			Student student = opt.get();
			
			
			student.setFName(stu.getFName());
			student.setLName(stu.getLName());
			student.setDep(stu.getDep());
			student.setGender(stu.getGender());
			
			return jpaRepositery.save(student);
			
		}
		return null;
	}
	
	//to find by deps
	public Student getStudentbyDep(String dep){
		
		return jpaRepositery.findByDep(dep);
			
	}
	
	//delete
	public int deleteStudentbyId( int id){
		
		Optional<Student> opt=jpaRepositery.findById(id);
		
		if((opt.isPresent()) && (opt.get().getId()==id)) {
		
			jpaRepositery.deleteById(id);
			return  1;
		}
		
			return 0;
	}
}
