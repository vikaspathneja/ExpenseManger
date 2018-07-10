package com.project.dao;


import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.reversepojos.TableIncome;
import com.project.reversepojos.TableIncomeCategory;
import com.project.reversepojos.TableUser;

@Repository
public class IncomeDaoImpl implements IncomeDaoInterface{
	@Autowired
	private SessionFactory sf;
	
	@Override
	public void addIncome(TableIncome incometableref) {
		sf.getCurrentSession().save(incometableref);
		
	}

	@Override
	public ArrayList<TableIncomeCategory> showIncomeCategory(long userid) {
		String hql="from TableIncomeCategory where user_id=:uid";
		try{
			ArrayList<TableIncomeCategory>incomecat=(ArrayList<TableIncomeCategory>)sf.getCurrentSession().createQuery(hql).setParameter("uid",userid).list();
			ArrayList<TableIncomeCategory>defaultincomecat=(ArrayList<TableIncomeCategory>)sf.getCurrentSession().createQuery(hql).setParameter("uid",0).list();
			System.out.println(incomecat.size()+"-------------cat size---------------"+defaultincomecat.size());
			defaultincomecat.addAll(incomecat);
			return defaultincomecat;
		}catch(Exception e){
			return null;
		}
		
	}
	
	@Override
	public ArrayList<TableIncome> getIncomeofParticularUser(long userid) {
		System.out.println("id getIncomeofParticularUser: "+userid);
		String hql="from TableIncome where user_id=:uid";
		@SuppressWarnings("unchecked")
		ArrayList<TableIncome>listofincome=(ArrayList<TableIncome>)sf.getCurrentSession().createQuery(hql).setParameter("uid",userid).list();
		System.out.println(listofincome.toString());
		return listofincome;
	}

	@Override
	public void deleteIncomebyId(int id) {
		System.out.println("id getIncomeofParticularIncome: "+id);
		String sql="delete from TableIncome where incomeId=:eid";
		sf.getCurrentSession().createQuery(sql).setParameter("eid",id).executeUpdate();
		System.out.println("query executed");
		
	}

	@Override
	public void updateIncome(TableIncome tableincomeobj) {
		System.out.println("id update of user"+tableincomeobj);
		sf.getCurrentSession().update(tableincomeobj);
		
	}
	
	@Override
	public ArrayList<TableIncome> getIncomeByUserAndCategoryId(int catid, long userid) {
		String hql="from TableIncome where user_id=:uid and income_category_id=:catid";
		@SuppressWarnings("unchecked")
		ArrayList<TableIncome>listofincomes=
			(ArrayList<TableIncome>)sf.
			getCurrentSession().createQuery(hql).setParameter("uid", userid).
			setParameter("catid", catid).list();
		System.out.println(listofincomes.toString());
		return listofincomes;
	}

	@Override
	public ArrayList<TableIncomeCategory> getdefaultIncomeCategory() {
		System.out.println("----------reach in default income category-------------");
		String hql="from TableIncomeCategory";
		@SuppressWarnings("unchecked")
		ArrayList<TableIncomeCategory>defaultincomecategory=(ArrayList<TableIncomeCategory>)sf.getCurrentSession().createQuery(hql).setMaxResults(2).list();
		System.out.println("incomecat"+defaultincomecategory.toString());
		return defaultincomecategory;
	}

	@Override
	public ArrayList<TableUser> getallincomes() {
		String allincomeshql="from TableIncome";
		return (ArrayList<TableUser>)sf.getCurrentSession().createQuery(allincomeshql).list();
	}
}
