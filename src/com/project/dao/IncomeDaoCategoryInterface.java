package com.project.dao;

import java.util.ArrayList;

import com.project.reversepojos.TableIncomeCategory;

public interface IncomeDaoCategoryInterface {
	public void addIncomeCategory(TableIncomeCategory incomecategoryeref);
	public ArrayList<TableIncomeCategory> getAllIncomeCategoryForAdmin();
	public void editIncomeCategory(TableIncomeCategory tbexpcatref);
	public void deleteIncomeCategory(int id);
	
}
	