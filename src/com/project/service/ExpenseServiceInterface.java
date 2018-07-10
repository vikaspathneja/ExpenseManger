package com.project.service;

import java.util.ArrayList;

import com.project.reversepojos.TableExpense;
import com.project.reversepojos.TableExpenseCategory;
import com.project.reversepojos.TableUser;

public interface ExpenseServiceInterface {
	
	public void addExpense(TableExpense expensetableref);
	public void updateExpense(TableExpense tableexpenseobject);
	public ArrayList<TableExpenseCategory> showExpenseCategory(long userid);
	public ArrayList<TableExpense> getExpensesofParticularUser(long userid);
	public void deleteExpensebyId(int id);
	public ArrayList<TableExpense> getExpensesByUserAndCategoryId(int catid,long userid);
	public ArrayList<TableExpenseCategory> getdefaultExpenseCategory();
	public ArrayList<TableUser> getallexpenses();
	
	

}
