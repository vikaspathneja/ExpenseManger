package com.project.service;

import java.util.ArrayList;

import com.project.reversepojos.TableIncomeCategory;

public interface IncomeServiceCategoryInterface {
	public void addIncomeCategory(TableIncomeCategory incomecategoryref);
	public ArrayList<TableIncomeCategory> getAllIncomeCategoryForAdmin();
	public void editIncomeCategory(TableIncomeCategory tbexpcatref);
	public void deleteIncomeCategory(int id);
	
	
}
