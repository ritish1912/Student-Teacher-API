package com.magic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.magic.entities.Subject;
import com.magic.entities.Teacher;
import com.magic.repositories.TeacherRepo;

@SpringBootTest
 class TeacherRepoTest {
	
	@Autowired
	TeacherRepo teacherRepo;
	@Test
	void findBySubject() {
		
		Subject subject = new Subject(1001,"Physics");
		Teacher teacher1 = new Teacher("Rajiv",subject);
		Teacher teacher2 = new Teacher("Rajiv",subject);
		
		teacherRepo.save(teacher1);
		teacherRepo.save(teacher2);
		
		List<Teacher>expected = new ArrayList<>();
		expected.add(teacher2);
		expected.add(teacher1);
		
		List<Teacher>actual = teacherRepo.findBySubject(subject);
		
		for(int i=0;i<actual.size();i++)
		{
			assertThat(	actual.get(i).getSubject().getSid()).isEqualTo(1001);
		}
		

		
		teacherRepo.delete(teacher1);
        teacherRepo.delete(teacher2);		
		
		
		
	}
	

}
