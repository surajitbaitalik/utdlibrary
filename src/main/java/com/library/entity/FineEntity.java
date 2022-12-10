/**
 * 
 */
package com.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Surajit
 *
 */
@Entity
@Table(name="fines")
public class FineEntity {
	@Id
	@Column(name="loan_id")
	private long loan_id;
	@Column(name="fine_amt")
	private double fine_amt;
	@Column(name="paid")
	private String paid;
	
	public long getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(long loan_id) {
		this.loan_id = loan_id;
	}
	public double getFine_amt() {
		return fine_amt;
	}
	public void setFine_amt(double fine_amt) {
		this.fine_amt = fine_amt;
	}
	public String getPaid() {
		return paid;
	}
	public void setPaid(String paid) {
		this.paid = paid;
	}
	

}
