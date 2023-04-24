package com.magic;



import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.magic.controllers.StudentController;
import com.magic.entities.Student;
import com.magic.repositories.BranchRepo;
import com.magic.repositories.StudentRepo;
import com.magic.repositories.SubjectRepo;


@WebMvcTest(StudentController.class)
public class ControllerTest {
	@MockBean
	StudentRepo studentRepo;
	@MockBean
	BranchRepo branchrepo;
	@MockBean
	SubjectRepo subjectRepo;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void createDoctor() throws Exception
	{
		Student student = new Student("Ritish");
		
		when(studentRepo.save(student)).thenReturn(student);
		
		mockMvc.perform(post("/Student/create").contentType(MediaType.APPLICATION_JSON)
				.content("{ \" name\" : \" Ritish\"}"))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.name", is("Ritish")))
		;
			 
	}
		

}
