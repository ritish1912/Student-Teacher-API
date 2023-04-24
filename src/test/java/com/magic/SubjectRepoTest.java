package com.magic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.magic.entities.Subject;
import com.magic.repositories.SubjectRepo;

@SpringBootTest
 class SubjectRepoTest {
	@Autowired
	SubjectRepo subjectRepo;
	
	@Test
	void findByName() {
		
		Subject subject = new Subject(1003,"Math");
		subjectRepo.save(subject);
		Subject  expected = subject;
		Subject actual = subjectRepo.findByName("Math");
		
		assertThat(expected.getSid()).isEqualTo(actual.getSid());
		
	}

}
