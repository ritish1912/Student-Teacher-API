package com.magic.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


@Entity
public class Student {

	@Override
	public int hashCode() {
		return Objects.hash(branch, name, sid, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(branch, other.branch) && Objects.equals(name, other.name) && sid == other.sid
				&& Objects.equals(subject, other.subject);
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", name=" + name + ", branch=" + branch + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	@Id
	@GeneratedValue
	private int sid;
	
	public Student(
			@Pattern(regexp = "[A-za-z' ']*", message = "Name can only be alphabetic characters") @Length(max = 20, message = "Length of name cannot be more than 20 characters") String name) {
		super();
		this.name = name;
	}
	@Pattern(regexp = "[A-za-z' ']*",message = "Name can only be alphabetic characters")
	@Length(max = 20,message="Length of name cannot be more than 20 characters")
	String name;
	public Student(
			@Pattern(regexp = "[A-za-z' ']*", message = "Name can only be alphabetic characters") @Length(max = 20, message = "Length of name cannot be more than 20 characters") String name,
			Subject subject, Branch branch) {
		super();
		
		this.name = name;
		this.subject = subject;
		this.branch = branch;
	}
	
	public Student() {
		super();
	}
	@OneToOne
	Subject subject;
	

    @ManyToOne
	Branch branch;


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
		this.sid = sid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}
