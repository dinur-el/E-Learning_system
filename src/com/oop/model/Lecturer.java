package com.oop.model;

public class Lecturer {

	private int id;
	
	private String name;

	private String email;
	
	private String specialization; 
	
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setTutorID(int newId) {
		this.id = newId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return this.specialization;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialzation(String newSpecialization) {
		this.specialization = newSpecialization;
	}

	@Override
	public String toString() {
		
		return "Lecturer ID = " + this.id + "\n" 
			   +"Lecturer Name = " + this.name + "\n" 
			   +"Lecturer Email = " + this.email + "\n"
			   +"Lecturer Specialization = " + this.specialization + "\n";
	}

}