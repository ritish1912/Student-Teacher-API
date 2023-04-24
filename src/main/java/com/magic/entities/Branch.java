package com.magic.entities;


import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Branch {
    public Branch() {
		super();
	}
	public Branch(int bid,
			@NotNull(message = "Branch cannot be null, value required") @Pattern(regexp = "Science|Commerce|Arts", message = "Invalid Branch") @Length(min = 3, message = "Length of branch name must be greater than 3 ") String name) {
		super();
		this.bid = bid;
		this.name = name;
	}
	@Id
    @GeneratedValue(generator = "b_id")
    @SequenceGenerator(name= "b_id",initialValue = 101,allocationSize = 1)
    private int bid;
    @NotNull(message = "Branch cannot be null, value required")
    @Pattern(regexp = "Science|Commerce|Arts",message = "Invalid Branch")
    @Length(min = 3,message = "Length of branch name must be greater than 3 ")
    String name;
    @JsonIgnore
    @OneToMany (mappedBy = "branch")
    List<Student> student;
    
   static HashMap<String,String>mp = new HashMap<>();
	
    
    
    public static String getMappedBranch(String name) {
    	if(mp.isEmpty())
    	{
    		mp.put("Physics", "Science");
    		mp.put("Chemistry", "Science");
    		mp.put("Math", "Science");
    		mp.put("CS", "Science");
    		mp.put("PE", "ALL");
    		mp.put("Accounts", "Commerce");
    		mp.put("Business", "Commerce");
    		mp.put("Economics", "Commerce");
    		mp.put("History", "Arts");
    		mp.put("Civics", "Arts");
    		mp.put("Geography", "Arts");
    		mp.put("English", "ALL"); 
    	}
    	   
	    if(mp.get(name)!=null)
	    {
	    	return mp.get(name);
	    }
    	return "No Such subject exists";
    	
    }
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
}
