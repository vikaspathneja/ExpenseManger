package com.project.dao;


import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.project.reversepojos.TableExpenseCategory;

@Repository
public class ExpenseDaoCategoryImpl implements ExpenseDaoCategoryInterface{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public void addExpenseCategory(TableExpenseCategory expensecategoryeref) {
		sf.getCurrentSession().save(expensecategoryeref);
	}

	@Override
	public ArrayList<TableExpenseCategory> getAllExpenseCategoryForAdmin() {
		String allexpensescategorieshql="from TableExpenseCategory";
		return (ArrayList<TableExpenseCategory>)sf.getCurrentSession().createQuery(allexpensescategorieshql).list();
		
	}

	@Override
	public void editExpenseCategory(TableExpenseCategory tbexpcatref) {
		sf.getCurrentSession().update(tbexpcatref);
		
	}

	@Override
	public void deleteExpenseCategory(int id) {
		System.out.println("reached in daooooo");
		String deletehql="delete from TableExpenseCategory where expenseCategoryId=:eid";
		sf.getCurrentSession().createQuery(deletehql).setParameter("eid",id).executeUpdate();
	}

}
