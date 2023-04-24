package com.magic.controllers;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magic.entities.Branch;
import com.magic.entities.Student;
import com.magic.entities.Subject;
import com.magic.handler.StudentNotFound;
import com.magic.handler.SubjectNotFound;
import com.magic.repositories.BranchRepo;
import com.magic.repositories.StudentRepo;
import com.magic.repositories.SubjectRepo;



@RestController
@RequestMapping("Student")
public class StudentController {
	
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	BranchRepo branchRepo;
	@Autowired 
	SubjectRepo subjectRepo;
	 
	
	@PostMapping("create")
	ResponseEntity<Student> createStudent(@Valid @RequestBody Student student)
	{
		Student stu  = studentRepo.save(student);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(stu);
		
		
	}
	
	@GetMapping("getById")
	ResponseEntity<Student>getStudent(@RequestParam int id)
	{
		Student stu = studentRepo.findById(id).orElse(null);
		if(stu == null)
			throw new StudentNotFound("Student of id = " + id + " doesn't exist");
		return ResponseEntity.status(HttpStatus.OK).body(stu);
		
	}
	@GetMapping("getList")
	ResponseEntity<List<Student>> getList( )
	{
		List<Student>student = studentRepo.findAll();
		if(student.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(student);
		
		
	}
	 @DeleteMapping("deleteById")
	 ResponseEntity<Student> deleteStudent(@RequestParam int id )
		{
		   Student student = studentRepo.findById(id).orElse(null);
		   if(student==null) {
			   throw new StudentNotFound("Student of id = " + id + " doesn't exist");
			  
		   }
		   studentRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(student);		
		}
	//List of students for a particular branch
	@GetMapping("getByBranch")
	ResponseEntity<List<Student>> getStudentByBranch(@RequestParam String name)
	{
		Branch br = branchRepo.findByName(name);
		List<Student>student = br.getStudent();
		if(student.isEmpty())
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}
	//List of all students of a particular subject
	@GetMapping("getBySubject")
	ResponseEntity<List<Student>> getStudentBySubject(@RequestParam String name)
	{
		Subject sub = subjectRepo.findByName(name);
		if(sub==null)
			throw new SubjectNotFound("No such subject exists");
		List<Student>student = studentRepo.findBySubject(sub);
		if(student.isEmpty())
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		return ResponseEntity.status(HttpStatus.OK).body(student);
		
	}
	
	
	@PatchMapping("updateSubject")
	ResponseEntity<Student>getUpdatedStudent(@RequestParam int sid,@Valid @RequestBody Student student)
	{
		Student stu = studentRepo.findById(sid).orElse(null);
		if(stu == null)
			 throw new StudentNotFound("Student of id = " + sid + " doesn't exist");
		
		stu.setSubject(student.getSubject());
		studentRepo.save(stu);
		Student newStu = studentRepo.findById(sid).orElse(null);
		return ResponseEntity.status(HttpStatus.OK).body(newStu);
	}
	
	
}
