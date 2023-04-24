package com.magic.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Teacher {

	public Teacher() {
		super();
	}
	@Id
	@GeneratedValue(generator = "t_id")
    @SequenceGenerator(name= "t_id",initialValue = 10001,allocationSize = 1)
	private int tid;
	@NotNull(message = "Name cannot be empty")
	@Pattern(regexp = "[A-Za-z' ']*",message = "Can't be other than alphabets")
	String name;
	public Teacher(
			@NotNull(message = "Name cannot be empty") @Pattern(regexp = "[A-Za-z' ']*", message = "Can't be other than alphabets") String name,
			Subject subject) {
		super();
		this.name = name;
		this.subject = subject;
	}
	@OneToOne
	Subject subject;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
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
	
}
