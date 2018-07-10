package com.project.service;

import java.util.ArrayList;

import com.project.reversepojos.TableExpenseCategory;

public interface ExpenseServiceCategoryInterface {
	public void addExpenseCategory(TableExpenseCategory expensecategoryref);
	public ArrayList<TableExpenseCategory> getAllExpenseCategoryForAdmin();
	public void editExpenseCategory(TableExpenseCategory tbexpcatref);
	public void deleteExpenseCategory(int id);
	
}
