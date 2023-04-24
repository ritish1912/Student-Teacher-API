package com.magic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.entities.Branch;

public interface BranchRepo extends JpaRepository<Branch, Integer> {

	Branch findByName(String name);

}
