package com.account.statement.dtos;

/**
 * @author Waris
 *
 */
public class CheckPaidsDto {

	private Integer Id;
	private String dataPaid;
	private String checkNumber;
	private String amount;
	private String referenceNumber;
	public Integer getId() {
		return Id;
	}
	public String getDataPaid() {
		return dataPaid;
	}
	public String getCheckNumber() {
		return checkNumber;
	}
	public String getAmount() {
		return amount;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public void setDataPaid(String dataPaid) {
		this.dataPaid = dataPaid;
	}
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
}
