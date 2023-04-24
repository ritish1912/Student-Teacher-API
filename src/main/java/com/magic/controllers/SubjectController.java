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
import com.magic.handler.SubjectNotFound;

import com.magic.repositories.SubjectRepo;


@RestController
@RequestMapping("subject")
public class SubjectController {

	@Autowired
	SubjectRepo subjectRepo;
	
	@PostMapping("create")
	ResponseEntity<Subject> createSubject(@Valid @RequestBody Subject subject)
	{
		Subject sub  = subjectRepo.save(subject );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(sub);
		
		
	}
	
	@GetMapping("getById")
	ResponseEntity<Subject>getSubject(@RequestParam int id)
	{
		Subject sub = subjectRepo.findById(id).orElse(null);
		if(sub == null)
			throw new SubjectNotFound("Student of id = " + id + " doesn't exist");
		return ResponseEntity.status(HttpStatus.OK).body(sub);
		
	}
	@GetMapping("getList")
	ResponseEntity<List<Subject>> getList( )
	{
		List<Subject>sub = subjectRepo.findAll();
		if(sub.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(sub);
		
		
	}
	 @DeleteMapping("deleteById")
	 ResponseEntity<String> deleteSubject(@RequestParam int id )
		{
		   Subject sub= subjectRepo.findById(id).orElse(null);
		   if(sub==null)
			   throw new SubjectNotFound("Student of id = " + id + " doesn't exist");
		   
		   subjectRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Subject with id = " + " deleted");		
		}
	 
	  
	  
}
