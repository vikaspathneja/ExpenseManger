package com.project.reversepojos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "table_expense")
public class TableExpense implements Serializable {
	private static final long serialVersionUID = 1L;


	public TableExpense() {
		System.out.println("0 arg ctor called");
	}
	
	@Id
	@GeneratedValue
	private int expenseId;
	
	@Column(name = "user_id")
	private long userId;
	
	
	
	public TableExpense(long userid) {
		this.userId = userid;
		
	}

	@Column(name = "expense_category_id")
	//@OneToOne(mappedBy="TableExpenseCategory" , cascade =CascadeType.ALL)
    //@JoinColumn(name = "expenseCategoryId")
	private int expenseCategoryId;
	
	@Column(name = "payment_mode_id")
	private int paymentModeId;
	
	@Column(name = "expense_description")
	private String expenseDescription;
	
	@Column(name = "expense_amount")
	private int expenseAmount;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "expense_date")
	private Date expenseDate;


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userid) {
		this.userId = userid;
	}

	public int getExpenseCategoryId() {
		return expenseCategoryId;
	}


	public void setExpenseCategoryId(int expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}


	public int getPaymentModeId() {
		return paymentModeId;
	}


	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}


	public String getExpenseDescription() {
		return expenseDescription;
	}


	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}


	public int getExpenseAmount() {
		return expenseAmount;
	}


	public void setExpenseAmount(int expenseAmount) {
		this.expenseAmount = expenseAmount;
	}


	public Date getExpenseDate() {
		return expenseDate;
	}


	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}


	public int getExpenseId() {
		return expenseId;
	}


	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}


	@Override
	public String toString() {
		return "\nTableExpense [expenseId=" + expenseId + ", userId=" + userId + ", expenseCategoryId="
				+ expenseCategoryId + ", paymentModeId=" + paymentModeId + ", expenseDescription=" + expenseDescription
				+ ", expenseAmount=" + expenseAmount + ", expenseDate=" + expenseDate + "]";
	}



}
