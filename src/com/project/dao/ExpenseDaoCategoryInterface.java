package com.project.dao;

import java.util.ArrayList;

import com.project.reversepojos.TableExpenseCategory;

public interface ExpenseDaoCategoryInterface {
	public void addExpenseCategory(TableExpenseCategory expensecategoryeref);
	public ArrayList<TableExpenseCategory> getAllExpenseCategoryForAdmin();
	public void editExpenseCategory(TableExpenseCategory tbexpcatref);
	public void deleteExpenseCategory(int id);
		
}
	