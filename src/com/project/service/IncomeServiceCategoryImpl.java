package com.project.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IncomeDaoCategoryInterface;
import com.project.reversepojos.TableIncomeCategory;

@Service
@Transactional
public class IncomeServiceCategoryImpl implements IncomeServiceCategoryInterface {
	@Autowired
	IncomeDaoCategoryInterface daointerfaceref;

	@Override
	public void addIncomeCategory(TableIncomeCategory incomecategoryref) {
		daointerfaceref.addIncomeCategory(incomecategoryref);
	}

	@Override
	public ArrayList<TableIncomeCategory> getAllIncomeCategoryForAdmin() {
		return daointerfaceref.getAllIncomeCategoryForAdmin();
		
	}

	@Override
	public void editIncomeCategory(TableIncomeCategory tbexpcatref) {
		 daointerfaceref.editIncomeCategory(tbexpcatref);
		
	}

	@Override
	public void deleteIncomeCategory(int id) {
		daointerfaceref.deleteIncomeCategory(id);
	}

}
