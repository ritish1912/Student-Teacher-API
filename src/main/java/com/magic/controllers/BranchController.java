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

import com.magic.entities.Branch;

import com.magic.handler.BranchNotFound;

import com.magic.repositories.BranchRepo;


@RestController
@RequestMapping("branch")
public class BranchController {
	
	@Autowired
	BranchRepo branchRepo;
	
	@PostMapping("create")
	ResponseEntity<Branch> createBranch(@Valid @RequestBody Branch branch)
	{
		Branch br  = branchRepo.save(branch );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(br);
		
		
	}
	
	@GetMapping("getById")
	ResponseEntity<Branch>getBranch(@RequestParam int id)
	{
	Branch br = branchRepo.findById(id).orElse(null);
		if(br == null)
			throw new BranchNotFound("Student of id = " + id + " doesn't exist");
		return ResponseEntity.status(HttpStatus.OK).body(br);
		
	}
	@GetMapping("getList")
	ResponseEntity<List<Branch>> getList( )
	{
		List<Branch>br = branchRepo.findAll();
		if(br.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(br);
		
		
	}
	 @DeleteMapping("deleteById")
	 ResponseEntity<String> deleteBranch(@RequestParam int id )
		{
		   Branch br= branchRepo.findById(id).orElse(null);
		   if(br==null)
			   throw new BranchNotFound("Student of id = " + id + " doesn't exist");
		   
		   branchRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Branch with id = " + id + " successfully deleted");		
		}
	 @GetMapping("getBranchBySubject")
	  ResponseEntity<String>getBranchBySubjects(String name)
	  {
		 String branch = Branch.getMappedBranch(name);
		 return ResponseEntity.status(HttpStatus.OK).body(branch);
		 
	  }
	 

}
