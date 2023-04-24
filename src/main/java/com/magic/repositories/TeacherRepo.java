package com.magic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.entities.Subject;
import com.magic.entities.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
	List<Teacher>findBySubject(Subject subject);

}
