package com.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IncomeDaoInterface;
import com.project.reversepojos.TableIncome;
import com.project.reversepojos.TableIncomeCategory;
import com.project.reversepojos.TableUser;


@Service
@Transactional
public class IncomeServiceImpl implements IncomeServiceInterface {
	@Autowired
	IncomeDaoInterface daointerfaceref;

	@Override
	public void addIncome(TableIncome incometableref) {
		daointerfaceref.addIncome(incometableref);
		
	}

	@Override
	public ArrayList<TableIncomeCategory> showIncomeCategory(long userid) {
		return daointerfaceref.showIncomeCategory(userid);
		
	}

	@Override
	public ArrayList<TableIncome> getIncomeofParticularUser(long userid) {
		return daointerfaceref.getIncomeofParticularUser(userid);
	}

	@Override
	public void deleteIncomeebyId(int id) {
		daointerfaceref.deleteIncomebyId(id);
		
	}

	@Override
	public void updateIncome(TableIncome tableincomeobject) {
		daointerfaceref.updateIncome(tableincomeobject);
		
	}

	@Override
	public ArrayList<TableIncome> getIncomeByUserAndCategoryId(int catid, long userid) {
		return daointerfaceref.getIncomeByUserAndCategoryId(catid, userid);
		
	}

	@Override
	public ArrayList<TableIncomeCategory> getdefaultIncomeCategory() {
		return daointerfaceref.getdefaultIncomeCategory();
	}

	@Override
	public ArrayList<TableUser> getallincomes() {
		return daointerfaceref.getallincomes();
	}
}
