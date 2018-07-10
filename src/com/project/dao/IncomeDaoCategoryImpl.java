package com.project.dao;


import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.reversepojos.TableIncomeCategory;

@Repository
public class IncomeDaoCategoryImpl implements IncomeDaoCategoryInterface{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public void addIncomeCategory(TableIncomeCategory incomecategoryeref) {
		sf.getCurrentSession().save(incomecategoryeref);
	}

	@Override
	public ArrayList<TableIncomeCategory> getAllIncomeCategoryForAdmin() {
		String allincomescategorieshql="from TableIncomeCategory";
		return (ArrayList<TableIncomeCategory>)sf.getCurrentSession().createQuery(allincomescategorieshql).list();
		
	}

	@Override
	public void editIncomeCategory(TableIncomeCategory tbexpcatref) {
		sf.getCurrentSession().update(tbexpcatref);
	}

	@Override
	public void deleteIncomeCategory(int id) {
		System.out.println("reached in daooooo");
		String deletehql="delete from TableIncomeCategory where incomeCategoryId=:eid";
		sf.getCurrentSession().createQuery(deletehql).setParameter("eid",id).executeUpdate();
	}

}
