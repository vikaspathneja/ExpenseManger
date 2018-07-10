package com.project.dao;


import java.util.ArrayList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.reversepojos.TableExpense;
import com.project.reversepojos.TableExpenseCategory;
import com.project.reversepojos.TableUser;

@Repository
public class ExpenseDaoImpl implements ExpenseDaoInterface{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public void addExpense(TableExpense expensetableref) {
		sf.getCurrentSession().save(expensetableref);
		
	}

	@Override
	public ArrayList<TableExpenseCategory> showExpenseCategory(long userid) {
		System.out.println("id in expense dao imp : "+userid);
		String hql="from TableExpenseCategory where user_id=:uid";
		ArrayList<TableExpenseCategory>expensecat=(ArrayList<TableExpenseCategory>)sf.getCurrentSession().createQuery(hql).setParameter("uid",userid).list();
		ArrayList<TableExpenseCategory>expensecatdefault=(ArrayList<TableExpenseCategory>)sf.getCurrentSession().createQuery(hql).setParameter("uid",0).list();
		System.out.println(expensecat.size()+"-------------cat size---------------"+expensecatdefault.size());
		expensecatdefault.addAll(expensecat);
		System.out.println("category having all list"+expensecatdefault.toString());
		return expensecatdefault;
		
	}

	@Override
	public ArrayList<TableExpense> getExpensesofParticularUser(long id) {
		System.out.println("id getExpensesofParticularUser: "+id);
		String hql="from TableExpense where user_id=:uid";
		@SuppressWarnings("unchecked")
		ArrayList<TableExpense>listofexpenses=(ArrayList<TableExpense>)sf.getCurrentSession().createQuery(hql).setParameter("uid",id).list();
		System.out.println(listofexpenses.toString());
		return listofexpenses;
	}

	@Override
	public void deleteExpensebyId(int expenseid) {
		System.out.println("reach in deleting expense by id : "+expenseid);
		String sql="delete from TableExpense where expenseid=:eid";
		sf.getCurrentSession().createQuery(sql).setParameter("eid",expenseid).executeUpdate();
		System.out.println("query executed");
	}

	@Override
	public void updateExpense(TableExpense tableexpenseobj) {
		System.out.println("user id:-"+tableexpenseobj.getUserId()+"expense id:-"+tableexpenseobj.getExpenseId()+"date:-"+tableexpenseobj.getExpenseDate()+"changedamount:-"+tableexpenseobj.getExpenseAmount());
		System.out.println("id update of user"+tableexpenseobj);
		sf.getCurrentSession().update(tableexpenseobj);
		
	}
	
	@Override
	public ArrayList<TableExpense> getExpensesByUserAndCategoryId(int catid, long userid) {
		System.out.println("userid: "+userid+"category id:"+catid);
		String hql="from TableExpense where user_id=:uid and expense_category_id=:catid";
		@SuppressWarnings("unchecked")
		ArrayList<TableExpense>listofexpenses=
			(ArrayList<TableExpense>)sf.
			getCurrentSession().createQuery(hql).setParameter("uid", userid).
			setParameter("catid", catid).list();
		System.out.println(listofexpenses.toString());
		return listofexpenses;
	}

	@Override
	public ArrayList<TableExpenseCategory> getdefaultExpenseCategory() {
		String hql="from TableExpenseCategory";
		@SuppressWarnings("unchecked")
		ArrayList<TableExpenseCategory>expensecat=(ArrayList<TableExpenseCategory>)sf.getCurrentSession().createQuery(hql).setMaxResults(4).list();
		System.out.println(expensecat);
		return expensecat;
		
	}

	@Override
	public ArrayList<TableUser> getallexpenses() {
		String allexpenseshql="from TableExpense";
		return (ArrayList<TableUser>)sf.getCurrentSession().createQuery(allexpenseshql).list();
	}
	}
