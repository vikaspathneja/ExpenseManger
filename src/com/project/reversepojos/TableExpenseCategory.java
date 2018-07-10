package com.project.reversepojos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="table_expense_category")
public class TableExpenseCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="expense_category_id")
	//@OneToOne
	private int expenseCategoryId;

	@Column(name="user_id")
	private long userId;
	
	@Column(name="expense_category_description")
	private String expenseCategoryDescription;

	@Column(name="expense_type")
	private String expenseType;

	public TableExpenseCategory() {
	}

	public int getExpenseCategoryId() {
		return this.expenseCategoryId;
	}

	public void setExpenseCategoryId(int expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}

	public String getExpenseCategoryDescription() {
		return this.expenseCategoryDescription;
	}

	public void setExpenseCategoryDescription(String expenseCategoryDescription) {
		this.expenseCategoryDescription = expenseCategoryDescription;
	}

	public String getExpenseType() {
		return this.expenseType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	
	public TableExpenseCategory(long userid) {
		this.userId = userid;
	}

	public TableExpenseCategory(int userId, String expenseCategoryDescription, String expenseType) {
		super();
		this.userId = userId;
		this.expenseCategoryDescription = expenseCategoryDescription;
		this.expenseType = expenseType;
	}
	
	public TableExpenseCategory(String expenseType){
		this.expenseType=expenseType;
	}

	
	@Override
	public String toString() {
		return "\nTableExpenseCategory [expenseCategoryId=" + expenseCategoryId + ", userId=" + userId
				+ ", expenseCategoryDescription=" + expenseCategoryDescription + ", expenseType=" + expenseType + "]";
	}

	public void setexpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
}