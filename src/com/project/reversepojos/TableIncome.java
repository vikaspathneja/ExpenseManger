package com.project.reversepojos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="table_income")
@NamedQuery(name="TableIncome.findAll", query="SELECT t FROM TableIncome t")
public class TableIncome implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "income_id")
	private int incomeId;
	
	@Column(name="income_amount")
	private int incomeAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="income_date")
	private Date incomeDate;

	@Column(name="income_description")
	private String incomeDescription;

	@Column(name="user_id")
	private long userId;

	//bi-directional many-to-one association to TableIncomeCategory
	/*@ManyToOne
	@JoinColumn(name="income_category_id")*/
	/*private TableIncomeCategory tableIncomeCategory;*/
	@Column(name="income_category_id")
	private int incomeCategoryId;
	
	//bi-directional many-to-one association to TablePaymentMode
	/*@ManyToOne
	@JoinColumn(name="payment_mode_id")
	private TablePaymentMode tablePaymentMode;*/

	public TableIncome() {
	}

	
	public TableIncome(long userId) {
		this.userId = userId;
	}


	public int getIncomeAmount() {
		return this.incomeAmount;
	}

	public void setIncomeAmount(int incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public Date getIncomeDate() {
		return this.incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	public String getIncomeDescription() {
		return this.incomeDescription;
	}

	public void setIncomeDescription(String incomeDescription) {
		this.incomeDescription = incomeDescription;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}



	public int getIncomeCategoryId() {
		return incomeCategoryId;
	}


	public void setIncomeCategoryId(int incomeCategoryId) {
		this.incomeCategoryId = incomeCategoryId;
	}


	@Override
	public String toString() {
		return "\nTableIncome [incomeId=" + incomeId + ", incomeAmount=" + incomeAmount + ", incomeDate=" + incomeDate
				+ ", incomeDescription=" + incomeDescription + ", userId=" + userId + ", incomeCategoryId="
				+ incomeCategoryId + "]";
	}




	/*public TableIncomeCategory getTableIncomeCategory() {
		return this.tableIncomeCategory;
	}

	public void setTableIncomeCategory(TableIncomeCategory tableIncomeCategory) {
		this.tableIncomeCategory = tableIncomeCategory;
	}*/

	

	/*public TablePaymentMode getTablePaymentMode() {
		return this.tablePaymentMode;
	}*/

	/*public void setTablePaymentMode(TablePaymentMode tablePaymentMode) {
		this.tablePaymentMode = tablePaymentMode;
	}
*/
	
}