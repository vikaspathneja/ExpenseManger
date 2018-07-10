package com.project.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.ExpenseDaoInterface;
import com.project.reversepojos.TableExpense;
import com.project.reversepojos.TableExpenseCategory;
import com.project.reversepojos.TableUser;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseServiceInterface {
	@Autowired
	ExpenseDaoInterface daointerfaceref;
	
	@Override
	public void addExpense(TableExpense expensetableref) {
		daointerfaceref.addExpense(expensetableref);
		
	}

	@Override
	public ArrayList<TableExpenseCategory> showExpenseCategory(long userid) {
		return daointerfaceref.showExpenseCategory(userid);
		
	}
	
	
	@Override
	public ArrayList<TableExpense> getExpensesofParticularUser(long id) {
		return daointerfaceref.getExpensesofParticularUser(id);
	}

	@Override
	public void deleteExpensebyId(int expenseid) {
		daointerfaceref.deleteExpensebyId(expenseid);
		
	}

	@Override
	public void updateExpense(TableExpense tableexpenseobject) {
		daointerfaceref.updateExpense(tableexpenseobject);
		
	}

	@Override
	public ArrayList<TableExpense> getExpensesByUserAndCategoryId(int catid, long userid) {
		return daointerfaceref.getExpensesByUserAndCategoryId(catid, userid);
		
	}

	@Override
	public ArrayList<TableExpenseCategory> getdefaultExpenseCategory() {
		return daointerfaceref.getdefaultExpenseCategory();
		
	}

	@Override
	public ArrayList<TableUser> getallexpenses() {
		return daointerfaceref.getallexpenses();
	}


}
