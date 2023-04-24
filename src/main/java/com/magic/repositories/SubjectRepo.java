package com.magic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.entities.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Integer>{
	
	Subject findByName(String name);

}
