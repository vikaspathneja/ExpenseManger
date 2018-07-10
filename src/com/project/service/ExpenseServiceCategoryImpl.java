package com.project.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.ExpenseDaoCategoryInterface;
import com.project.reversepojos.TableExpenseCategory;

@Service
@Transactional
public class ExpenseServiceCategoryImpl implements ExpenseServiceCategoryInterface {
	@Autowired
	ExpenseDaoCategoryInterface daointerfaceref;

	@Override
	public void addExpenseCategory(TableExpenseCategory expensecategoryref) {
		daointerfaceref.addExpenseCategory(expensecategoryref);
	}

	@Override
	public ArrayList<TableExpenseCategory> getAllExpenseCategoryForAdmin() {
		return daointerfaceref.getAllExpenseCategoryForAdmin();
	}

	@Override
	public void editExpenseCategory(TableExpenseCategory tbexpcatref) {
		daointerfaceref.editExpenseCategory(tbexpcatref);
		
	}

	@Override
	public void deleteExpenseCategory(int id) {
		daointerfaceref.deleteExpenseCategory(id);
	}

	

	
}
