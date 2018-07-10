package com.project.dao;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.reversepojos.TableExpense;
import com.project.reversepojos.TableExpenseCategory;
import com.project.reversepojos.TableIncome;
import com.project.reversepojos.TableIncomeCategory;
import com.project.reversepojos.TableUser;

@Repository
public class MappingDaoImpl implements MappingDaoInterface{
	
	@Autowired
	private SessionFactory sf;
	@Autowired
	private ExpenseDaoInterface expenseref; 
	@Autowired
	private IncomeDaoInterface incomeref;
	
	@Override
	public HashMap finalgainloss(HttpSession hs) {
		System.out.println("reached in dao finalgain loss method:----------");
		HashMap hm=new HashMap<>();
		
		Double totalexpense=0.0;
		Double totalincome=0.0;
		try{
			TableUser user =(TableUser)hs.getAttribute("valid_user");
			System.out.println(user.toString());
			System.out.println(expenseref.getExpensesofParticularUser(user.getUserId()));
			//for sum of all expenses
			ArrayList<TableExpense> tbexplist=expenseref.getExpensesofParticularUser(user.getUserId());
			for(TableExpense tbexp: tbexplist){
			totalexpense+=tbexp.getExpenseAmount();	
			}
			hm.put("totalexpense",totalexpense);
			
			//for sum of all incomess
			ArrayList<TableIncome> tbincomelist=incomeref.getIncomeofParticularUser(user.getUserId());
			for(TableIncome tbincome: tbincomelist){
			totalincome+=tbincome.getIncomeAmount();	
			}
			hm.put("totalincome",totalincome);
			
			System.out.println("total from expense income table------");
//			return totalincome-totalexpense;
			return hm;
		}
		catch(NullPointerException e){
			System.out.println("null received while calling finalgainloss method");
			return null;
		}
	}

	@Override
	public void checkIncomeExpenseCategory() {
		System.out.println("reached in checkincomeexpensecategory for default");
		String querytocheckcategory1="select income_type from table_income_category where income_type=:incometype";
		String querytocheckcategory2="select expense_type from table_expense_category where expense_type=:expensetype";
		
		String name=(String)sf.getCurrentSession().createSQLQuery(querytocheckcategory1).setParameter("incometype", "Salary").uniqueResult();
		if(name==null|| name.equals("null")||name==""){
			TableIncomeCategory incomecat=new TableIncomeCategory();
			incomecat.setIncomeType("Salary");
			sf.getCurrentSession().save(incomecat);
		}
		
		String name2=(String)sf.getCurrentSession().createSQLQuery(querytocheckcategory1).setParameter("incometype", "Cash").uniqueResult();
		if(name2==null|| name2.equals("null")||name2==""){
			TableIncomeCategory incomecat=new TableIncomeCategory();
			incomecat.setIncomeType("Cash");
			sf.getCurrentSession().save(incomecat);
		}
		
		String name3=(String)sf.getCurrentSession().createSQLQuery(querytocheckcategory2).setParameter("expensetype", "Food").uniqueResult();
		if(name3==null|| name3.equals("null")||name3==""){
			TableExpenseCategory expesecat=new TableExpenseCategory();
			expesecat.setExpenseType("Food");
			sf.getCurrentSession().save(expesecat);
		}
		
		String name4=(String)sf.getCurrentSession().createSQLQuery(querytocheckcategory2).setParameter("expensetype", "Rent").uniqueResult();
		if(name4==null|| name3.equals("null")||name4==""){
			TableExpenseCategory expesecat=new TableExpenseCategory();
			expesecat.setExpenseType("Rent");
			sf.getCurrentSession().save(expesecat);
		}
		
		String name5=(String)sf.getCurrentSession().createSQLQuery(querytocheckcategory2).setParameter("expensetype", "Cash").uniqueResult();
		if(name5==null|| name5.equals("null")||name5==""){
			TableExpenseCategory expesecat=new TableExpenseCategory();
			expesecat.setExpenseType("Cash");
			sf.getCurrentSession().save(expesecat);
		}
		
		String name6=(String)sf.getCurrentSession().createSQLQuery(querytocheckcategory2).setParameter("expensetype", "Emi").uniqueResult();
		if(name6==null|| name6.equals("null")||name6==""){
			TableExpenseCategory expesecat=new TableExpenseCategory();
			expesecat.setExpenseType("Emi");
			sf.getCurrentSession().save(expesecat);
		}
	}
}