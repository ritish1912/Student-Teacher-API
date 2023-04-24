package com.magic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.magic.entities.Branch;
import com.magic.entities.Student;
import com.magic.entities.Subject;
import com.magic.repositories.StudentRepo;

@SpringBootTest
class StudentRepoTest {
	
	@Autowired
	StudentRepo studentRepo;
	
	@Test
	void findBySubject()
	{
		Branch branch = new Branch(105,"Science");
		Subject subject = new Subject(1004,"CS");
		Student student1 = new Student("Bhaukal",subject,branch);
		Student student2 = new Student("Bhaukal",subject,branch);
		studentRepo.save(student1);
		studentRepo.save(student2);
		
		List<Student>expected =new ArrayList<>();
		expected.add(student1);
		expected.add(student2);
		List<Student>actual = studentRepo.findBySubject(subject);
		
		assertThat(actual).hasSize(2);
		studentRepo.delete(student2);
		studentRepo.delete(student1);
		
		
	}
	
	
}
