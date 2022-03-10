package com.account.statement.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Waris
 *
 */
@Entity
@Table(name = "check_paids")
public class CheckPaidsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private Integer Id;
	private String dataPaid;
	private String checkNumber;
	private String amount;
	private String referenceNumber;
	private int uniqueReferenceNumber;
	private LocalDateTime createdAt;
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public int getUniqueReferenceNumber() {
		return uniqueReferenceNumber;
	}
	public void setUniqueReferenceNumber(int uniqueReferenceNumber) {
		this.uniqueReferenceNumber = uniqueReferenceNumber;
	}
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
