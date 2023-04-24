package com.magic.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magic.entities.Subject;
import com.magic.entities.Teacher;

import com.magic.handler.TeacherNotFound;
import com.magic.repositories.SubjectRepo;
import com.magic.repositories.TeacherRepo;

@RestController
@RequestMapping("Teacher")
public class TeacherController {
	
	@Autowired
	TeacherRepo teacherRepo;
	@Autowired
	SubjectRepo subjectRepo;
	
	@PostMapping("create")
	ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher)
	{
		Teacher teach  = teacherRepo.save(teacher );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(teach);
		
		
	}
	
	@GetMapping("getById")
	ResponseEntity<Teacher>getTeacher(@RequestParam int id)
	{
		Teacher teach = teacherRepo.findById(id).orElse(null);
		if(teach == null)
			 throw new TeacherNotFound("Student of id = " + id + " doesn't exist");
		return ResponseEntity.status(HttpStatus.OK).body(teach);
		
	}
	@GetMapping("getList")
	ResponseEntity<List<Teacher>> getList( )
	{
		List<Teacher>teacher = teacherRepo.findAll();
		if(teacher.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(teacher);
		
		
	}
	 @DeleteMapping("deleteById")
	 ResponseEntity<String> deleteTeacher(@RequestParam int id )
		{
		   Teacher teacher = teacherRepo.findById(id).orElse(null);
		   if(teacher==null)
			   throw new TeacherNotFound("Student of id = " + id + " doesn't exist");
		   
		   teacherRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Teacher with id ="+ id + " deleted");	
		}
     @GetMapping("getBySubject")
     ResponseEntity<List<Teacher>>getTeacherBySubject(String name)
     {
    	 Subject sub = subjectRepo.findByName(name);
    	 List<Teacher>teacher = teacherRepo.findBySubject(sub);
    	 if(teacher.isEmpty())
    	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	 
    	 return ResponseEntity.status(HttpStatus.OK).body(teacher);
     }
     
}
