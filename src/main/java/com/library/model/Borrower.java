/**
 * 
 */
package com.library.model;

/**
 * @author Surajit
 *
 */
public class Borrower {
	private  Long id;
	private String ssn;
	private String first_name;
	private String last_name;
	private String email;
	private String address;
	private String city;
	private String state;
	private String contactNumber;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	@Override
	public String toString() {
		return "Borrower [id=" + id + ", ssn=" + ssn + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", contactNumber=" + contactNumber + "]";
	}
	
	
}
