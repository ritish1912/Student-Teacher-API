package com.magic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.magic.entities.Branch;
import com.magic.repositories.BranchRepo;

@SpringBootTest
 class BranchRepoTest {
	@Autowired
	BranchRepo branchRepo;
	@Test
	void findByName()
	{
	  	 Branch branch = new Branch(105,"Science");
	  	 branchRepo.save(branch);
	  	 
	  	 Branch expected = branch;
	  	 Branch actual  = branchRepo.findByName("Science");
	  	 
	  	assertThat(actual.getBid()).isEqualTo(expected.getBid());
		
	}
	

}
