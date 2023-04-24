package com.magic.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Subject {
   
	public Subject() {
		super();
	}
	public Subject(int sid,
			@NotNull(message = "Subject can't be NULL") @Pattern(regexp = "Physics|Chemistry|Math|CS|English|PE|Accounts|Business|Economics|History|Civics|Geography", message = "Invalid Subject") String name) {
		super();
		this.sid = sid;
		this.name = name;
	}
	@Id
	 @GeneratedValue(generator = "s_id")
    @SequenceGenerator(name= "s_id",initialValue = 1001,allocationSize = 1)
	private int sid;
	@NotNull(message = "Subject can't be NULL")
	@Pattern(regexp = "Physics|Chemistry|Math|CS|English|PE|Accounts|Business|Economics|History|Civics|Geography",message = "Invalid Subject")
	String name;
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
	
}
