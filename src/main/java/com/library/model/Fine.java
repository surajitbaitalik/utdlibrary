/**
 * 
 */
package com.library.model;

/**
 * @author Surajit
 *
 */
public class Fine {
	private String loan_id="";
	private String isbn="";
	private String order_Date="";
	private String submission_Date="";
	private String fine="";
	public String getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getOrder_Date() {
		return order_Date;
	}
	public void setOrder_Date(String order_Date) {
		this.order_Date = order_Date;
	}
	public String getSubmission_Date() {
		return submission_Date;
	}
	public void setSubmission_Date(String submission_Date) {
		this.submission_Date = submission_Date;
	}
	public String getFine() {
		return fine;
	}
	public void setFine(String fine) {
		this.fine = fine;
	}
	@Override
	public String toString() {
		return "Fine [loan_id=" + loan_id + ", isbn=" + isbn + ", order_Date=" + order_Date + ", submission_Date="
				+ submission_Date + ", fine=" + fine + "]";
	}
	

}
