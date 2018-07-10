package com.project.dao;
import java.util.ArrayList;

import com.project.reversepojos.TableIncome;
import com.project.reversepojos.TableIncomeCategory;
import com.project.reversepojos.TableUser;

public interface IncomeDaoInterface {

	public void addIncome(TableIncome incometableref);
	/*public void deleteExpense(int id,String category,Date expensedate);
	public void updateExpense(int id,String category,Date expensedate);*/
	public ArrayList<TableIncomeCategory> showIncomeCategory(long userid);
	public ArrayList<TableIncome> getIncomeofParticularUser(long userid);
	public void deleteIncomebyId(int id);
	public void updateIncome(TableIncome  tableincomeobj);
	public ArrayList<TableIncome> getIncomeByUserAndCategoryId(int catid, long userid);
	public ArrayList<TableIncomeCategory> getdefaultIncomeCategory();
	public ArrayList<TableUser> getallincomes();	

}
	