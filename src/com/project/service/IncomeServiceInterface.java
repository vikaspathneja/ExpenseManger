package com.project.service;

import java.util.ArrayList;

import com.project.reversepojos.*;

public interface IncomeServiceInterface {

	public void addIncome(TableIncome incometableref);
	public ArrayList<TableIncomeCategory> showIncomeCategory(long userid);
	public ArrayList<TableIncome> getIncomeofParticularUser(long userid);
	public void deleteIncomeebyId(int id);
	public void updateIncome(TableIncome tableincomeobject);
	public ArrayList<TableIncome> getIncomeByUserAndCategoryId(int catid,long userid);
	public ArrayList<TableIncomeCategory> getdefaultIncomeCategory();
	public ArrayList<TableUser> getallincomes();

}
