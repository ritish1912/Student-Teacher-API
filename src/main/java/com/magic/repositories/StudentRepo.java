package com.magic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.entities.Student;
import com.magic.entities.Subject;

public interface StudentRepo extends JpaRepository<Student, Integer>{
	List<Student>findBySubject(Subject subject);

}
