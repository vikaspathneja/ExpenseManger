package com.project.dao;

import java.util.ArrayList;
import com.project.reversepojos.TableExpense;
import com.project.reversepojos.TableExpenseCategory;
import com.project.reversepojos.TableUser;

public interface ExpenseDaoInterface {

	public void addExpense(TableExpense expensetableref);
	public void updateExpense(TableExpense  tableexpenseobj);
	public ArrayList<TableExpenseCategory> showExpenseCategory(long userid);
	public ArrayList<TableExpense> getExpensesofParticularUser(long userid);
	public void deleteExpensebyId(int expenseid);
	public ArrayList<TableExpense> getExpensesByUserAndCategoryId(int catid, long userid);
	public ArrayList<TableExpenseCategory> getdefaultExpenseCategory();
	public ArrayList<TableUser> getallexpenses();
	
		

}
	